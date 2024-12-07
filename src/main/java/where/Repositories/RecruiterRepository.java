package where.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import where.Entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {


}
