package where.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import where.Entities.Candidate;
import where.Repositories.CandidateRepository;

import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Candidate saveCandidate(Candidate candidate) {
        if (candidate.getPassword() == null || candidate.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        // Crypter le mot de passe
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));

        // Sauvegarder le candidat
        return candidateRepository.save(candidate);
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
