package where.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import where.Entities.Application;
import where.Entities.Offer;
import where.Repositories.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
@Autowired
    private OfferRepository offerRepository;

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer updateOffer(Long id, Offer updatedOffer) {
        Optional<Offer> existingOffer = offerRepository.findById(id);
        if (existingOffer.isPresent()) {
            Offer offer = existingOffer.get();
            offer.setTitle(updatedOffer.getTitle());
            offer.setDescription(updatedOffer.getDescription());
            offer.setLocation(updatedOffer.getLocation());
            offer.setStartDate(updatedOffer.getStartDate());
            offer.setEndDate(updatedOffer.getEndDate());
            return offerRepository.save(offer);
        } else {
            throw new RuntimeException("Offer not found with ID: " + id);
        }
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    // Récupérer les candidatures associées à une offre
    public List<Application> getApplicationsByOfferId(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            return offer.get().getApplications();
        } else {
            throw new RuntimeException("Offer not found with ID: " + id);
        }
    }

}
