package where.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import where.Entities.Application;
import where.Entities.Candidate;
import where.Entities.Offer;
import where.Entities.StatusApplication;
import where.Repositories.ApplicationRepository;
import where.Repositories.CandidateRepository;
import where.Repositories.OfferRepository;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

@Autowired
    CandidateRepository candidateRepository;
@Autowired
    OfferRepository offerRepository;

    public String saveApplication(Long offerId, Long candidateId, Application application,
                                  MultipartFile cvFile) throws IOException {
        // Vérifier si l'offre existe
        Offer jobOffer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offre non trouvée avec l'ID : " + offerId));

        // Vérifier si le candidat existe
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé avec l'ID : " + candidateId));

        // Vérifier le fichier CV
        if (cvFile == null || cvFile.isEmpty()) {
            throw new RuntimeException("Le fichier CV est obligatoire !");
        }

        // Associer les données fournies dans l'application
        application.setCv(cvFile.getBytes());
        application.setJobOffer(jobOffer);
        application.setCandidate(candidate);

        // Définir les valeurs par défaut
        application.setApplicationDate(new Date()); // Date actuelle
        application.setStatus(StatusApplication.PENDING); // Statut initial

        // Sauvegarder dans la base de données
        applicationRepository.save(application);

        return "Candidature enregistrée avec succès !";
    }

}
