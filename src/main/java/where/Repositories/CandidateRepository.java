package where.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import where.Entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    Candidate findByEmail(String mail);
}
