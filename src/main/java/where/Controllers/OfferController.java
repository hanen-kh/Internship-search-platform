package where.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import where.Entities.Application;
import where.Entities.Offer;
import where.Services.OfferService;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @PostMapping("/createOffer")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
    }

    @GetMapping("/allOffers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    @PutMapping("/updateOffer/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer updatedOffer) {
        try {
            Offer offer = offerService.updateOffer(id, updatedOffer);
            return ResponseEntity.ok(offer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteOffer/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<List<Application>> getApplicationsByOfferId(@PathVariable Long id) {
        try {
            List<Application> applications = offerService.getApplicationsByOfferId(id);
            return ResponseEntity.ok(applications);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
