package where.ServicesTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import where.Entities.Application;
import where.Entities.Candidate;
import where.Entities.Offer;
import where.Entities.StatusApplication;
import where.Repositories.ApplicationRepository;
import where.Repositories.CandidateRepository;
import where.Repositories.OfferRepository;
import where.Services.ApplicationService;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private OfferRepository offerRepository;

    @Test
    void saveApplication_ShouldSaveSuccessfully_WhenAllInputsAreValid() throws IOException {
        // Arrange
        Long offerId = 1L;
        Long candidateId = 1L;

        Offer jobOffer = new Offer();
        jobOffer.setId(offerId);

        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        Application application = new Application();

        MockMultipartFile cvFile = new MockMultipartFile(
                "cv",
                "cv.pdf",
                "application/pdf",
                "Mock CV content".getBytes()
        );

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(jobOffer));
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(applicationRepository.save(any(Application.class))).thenReturn(application);

        // Act
        String result = applicationService.saveApplication(offerId, candidateId, application, cvFile);

        // Assert
        assertEquals("Candidature enregistrée avec succès !", result);
        assertNotNull(application.getCv());
        assertEquals(jobOffer, application.getJobOffer());
        assertEquals(candidate, application.getCandidate());
        assertEquals(StatusApplication.PENDING, application.getStatus());

        verify(offerRepository, times(1)).findById(offerId);
        verify(candidateRepository, times(1)).findById(candidateId);
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    void saveApplication_ShouldThrowException_WhenOfferNotFound() {
        // Arrange
        Long offerId = 99L;
        Long candidateId = 1L;
        Application application = new Application();
        MockMultipartFile cvFile = new MockMultipartFile("cv", "cv.pdf", "application/pdf", "Mock CV content".getBytes());

        when(offerRepository.findById(offerId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            applicationService.saveApplication(offerId, candidateId, application, cvFile);
        });

        assertEquals("Offre non trouvée avec l'ID : " + offerId, exception.getMessage());
        verify(offerRepository, times(1)).findById(offerId);
        verify(candidateRepository, times(0)).findById(candidateId);
        verify(applicationRepository, times(0)).save(any(Application.class));
    }

    @Test
    void saveApplication_ShouldThrowException_WhenCandidateNotFound() {
        // Arrange
        Long offerId = 1L;
        Long candidateId = 99L;
        Application application = new Application();
        MockMultipartFile cvFile = new MockMultipartFile("cv", "cv.pdf", "application/pdf", "Mock CV content".getBytes());

        Offer jobOffer = new Offer();
        jobOffer.setId(offerId);

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(jobOffer));
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            applicationService.saveApplication(offerId, candidateId, application, cvFile);
        });

        assertEquals("Candidat non trouvé avec l'ID : " + candidateId, exception.getMessage());
        verify(offerRepository, times(1)).findById(offerId);
        verify(candidateRepository, times(1)).findById(candidateId);
        verify(applicationRepository, times(0)).save(any(Application.class));
    }

    @Test
    void saveApplication_ShouldThrowException_WhenCvFileIsMissing() {
        // Arrange
        Long offerId = 1L;
        Long candidateId = 1L;

        Offer jobOffer = new Offer();
        jobOffer.setId(offerId);

        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        Application application = new Application();

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(jobOffer));
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            applicationService.saveApplication(offerId, candidateId, application, null);
        });

        assertEquals("Le fichier CV est obligatoire !", exception.getMessage());
        verify(offerRepository, times(1)).findById(offerId);
        verify(candidateRepository, times(1)).findById(candidateId);
        verify(applicationRepository, times(0)).save(any(Application.class));
    }
}
