package where.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import where.Entities.*;
import where.Repositories.CandidateRepository;
import where.Repositories.RoleRepository;
import where.Repositories.ValidationRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class CandidateService extends UserService{
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private  EmailService emailService;
    @Autowired
    private ValidationRepository validationRepository;





    public Candidate saveCandidate(Candidate candidate) {
        // Vérifier si le rôle CANDIDATE existe dans la base de données
        Optional<Role> candidateRole = roleRepository.findByLibelle(TypeRole.CANDIDATE);



        // Associer le rôle au candidat
        candidate.setRole(candidateRole.get());

        // Vérifier si un candidat avec le même email existe déjà
       // if (this.loadUserByUsername(candidate.getEmail())!=null) {
          //  throw new IllegalArgumentException("Candidate already EXISTS");
        //}

        // Hacher le mot de passe
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(hashedPassword);

        // Sauvegarder le candidat
       Candidate savedCandidate = candidateRepository.save(candidate);

        return savedCandidate;
    }


    public Candidate updateCandidate(Long id, Candidate candidateDetails) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
        if (optionalCandidate.isPresent()) {
            Candidate existingCandidate = optionalCandidate.get();
            existingCandidate.setFirstname(candidateDetails.getFirstname());
            existingCandidate.setLastname(candidateDetails.getLastname());
            existingCandidate.setEmail(candidateDetails.getEmail());
            existingCandidate.setPassword(candidateDetails.getPassword());
            existingCandidate.setDateOfBirth(candidateDetails.getDateOfBirth());
            existingCandidate.setUniversity(candidateDetails.getUniversity());
            return candidateRepository.save(existingCandidate);
        } else {
            throw new RuntimeException("Candidate not found with id " + id);
        }
    }



}
