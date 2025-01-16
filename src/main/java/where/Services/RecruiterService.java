package where.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import where.Entities.*;
import where.Repositories.CompanyRepository;
import where.Repositories.RecruiterRepository;
import where.Repositories.RoleRepository;
import where.Repositories.ValidationRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;
@Autowired
private RoleRepository roleRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private EmailService emailService;
    public String saveRecruiterTemporary(Recruiter recruiter, Company company) {
        // Vérifier si l'entreprise existe déjà
        Optional<Company> existingCompany = companyRepository.findByName(company.getName());
        if (existingCompany.isPresent()) {
            recruiter.setCompany(existingCompany.get());
        } else {
            recruiter.setCompany(companyRepository.save(company));
        }

        // Assigner le rôle RECRUITER

        Optional<Role> recruiterRole = roleRepository.findByLibelle(TypeRole.RECRUITER);
        if (recruiterRole.isPresent()) {
            recruiter.setRole(recruiterRole.get());
        } else {
            throw new RuntimeException("Role RECRUITER doesn't exist");
        }
        Recruiter recruiterr=new Recruiter();
        recruiterr= (Recruiter) userService.loadUserByUsername(recruiter.getEmail());

        // Créer un nouvel objet Validation
        Validation validation = new Validation();
        validation.setUtilisateur(recruiterr);

        // Définir les instants d'activation et d'expiration
        Instant activation = Instant.now();
        validation.setActivation(activation);
        Instant expiration = activation.plus(3, MINUTES);
        validation.setExpiration(expiration);

        // Générer un code de validation aléatoire à 6 chiffres
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);

        // Enregistrer l'objet Validation
        validationRepository.save(validation);

        // Envoyer la notification à l'utilisateur
        String subject = "Your signup validation code";
        String body = "Validation code  : " + validation.getCode()+".It will be expired in 3 minutes";
        emailService.sendSimpleEmail(recruiter.getEmail(), subject, body);

        recruiterRepository.save(recruiter);
        return "You are temporarily registered. An email has been sent to : " + recruiter.getEmail();

    }


    public String validateRecruiter(String email, String code, String password) {
        // Charger la validation et le candidat
        Validation validation = validationRepository.findByUtilisateurEmail(email)
                .orElseThrow(() -> new RuntimeException("No validation was found."));

        Recruiter recruiter = (Recruiter) validation.getUtilisateur();

        // Vérifier si le code est expiré
        if (validation.getExpiration().isBefore(Instant.now())) {
            validationRepository.delete(validation);
            recruiterRepository.delete(recruiter);
            throw new RuntimeException("The code has expired. Please start again.");
        }

        // Vérifier si le code est correct
        if (!validation.getCode().equals(code)) {
            throw new RuntimeException("Code incorrect !");
        }

        // Sauvegarder définitivement le candidat avec le mot de passe crypté
        recruiter.setPassword(new BCryptPasswordEncoder().encode(password));

        recruiterRepository.save(recruiter);

        // Supprimer l'objet Validation
        validationRepository.delete(validation);

        return "Successfully validated !";
    }


}
