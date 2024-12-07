package where.ServicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import where.Entities.Application;
import where.Entities.Offer;
import where.Repositories.OfferRepository;
import where.Services.OfferService;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    @InjectMocks
    private OfferService offerService;
    @Mock
    private OfferRepository offerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOffer_ShouldSaveOfferSuccessfully() {
        // Arrange
        Offer offer = new Offer();
        offer.setTitle("Software Internship");
        offer.setDescription("Exciting internship opportunity.");
        offer.setLocation("Paris");

        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        // Act
        Offer result = offerService.createOffer(offer);

        // Assert
        assertNotNull(result);
        assertEquals("Software Internship", result.getTitle());
        assertEquals("Exciting internship opportunity.", result.getDescription());
        assertEquals("Paris", result.getLocation());

        verify(offerRepository, times(1)).save(any(Offer.class));
    }

    @Test
    void getAllOffers_ShouldReturnAllOffers() {
        // Arrange
        Offer offer1 = new Offer();
        offer1.setTitle("Offer 1");
        Offer offer2 = new Offer();
        offer2.setTitle("Offer 2");

        when(offerRepository.findAll()).thenReturn(Arrays.asList(offer1, offer2));

        // Act
        List<Offer> result = offerService.getAllOffers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(offerRepository, times(1)).findAll();
    }

    @Test
    void updateOffer_ShouldUpdateSuccessfully_WhenOfferExists() {
        // Arrange
        Long offerId = 1L;

        Offer existingOffer = new Offer();
        existingOffer.setId(offerId);
        existingOffer.setTitle("Old Title");

        Offer updatedOffer = new Offer();
        updatedOffer.setTitle("New Title");
        updatedOffer.setDescription("Updated description");
        updatedOffer.setLocation("New York");

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(existingOffer));
        when(offerRepository.save(existingOffer)).thenReturn(existingOffer);

        // Act
        Offer result = offerService.updateOffer(offerId, updatedOffer);

        // Assert
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        assertEquals("New York", result.getLocation());

        verify(offerRepository, times(1)).findById(offerId);
        verify(offerRepository, times(1)).save(existingOffer);
    }

    @Test
    void updateOffer_ShouldThrowException_WhenOfferDoesNotExist() {
        // Arrange
        Long offerId = 99L;
        Offer updatedOffer = new Offer();

        when(offerRepository.findById(offerId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            offerService.updateOffer(offerId, updatedOffer);
        });

        assertEquals("Offer not found with ID: " + offerId, exception.getMessage());
        verify(offerRepository, times(1)).findById(offerId);
        verify(offerRepository, times(0)).save(any(Offer.class));
    }

    @Test
    void deleteOffer_ShouldDeleteSuccessfully() {
        // Arrange
        Long offerId = 1L;

        // Act
        offerService.deleteOffer(offerId);

        // Assert
        verify(offerRepository, times(1)).deleteById(offerId);
    }

    @Test
    void getApplicationsByOfferId_ShouldReturnApplications_WhenOfferExists() {
        // Arrange
        Long offerId = 1L;
        Application application1 = new Application();
        Application application2 = new Application();

        Offer offer = new Offer();
        offer.setApplications(new ArrayList<>(Arrays.asList(application1, application2)));

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offer));

        // Act
        List<Application> result = offerService.getApplicationsByOfferId(offerId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(offerRepository, times(1)).findById(offerId);
    }

    @Test
    void getApplicationsByOfferId_ShouldThrowException_WhenOfferDoesNotExist() {
        // Arrange
        Long offerId = 99L;

        when(offerRepository.findById(offerId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            offerService.getApplicationsByOfferId(offerId);
        });

        assertEquals("Offer not found with ID: " + offerId, exception.getMessage());
        verify(offerRepository, times(1)).findById(offerId);
    }
}
