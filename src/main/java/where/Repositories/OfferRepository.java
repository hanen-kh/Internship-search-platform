package where.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import where.Entities.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    
}
