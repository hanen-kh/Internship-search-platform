package where.Services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import where.Entities.Company;
import where.Entities.Recruiter;
import where.Entities.Role;
import where.Entities.TypeRole;
import where.Repositories.CompanyRepository;
import where.Repositories.RecruiterRepository;
import where.Repositories.RoleRepository;

import java.util.Optional;

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
    public Recruiter createRecruiter(Recruiter recruiter, Company company) {
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
        // Crypter le mot de passe
        recruiter.setPassword(new BCryptPasswordEncoder().encode(recruiter.getPassword()));

        return recruiterRepository.save(recruiter);
    }

}
