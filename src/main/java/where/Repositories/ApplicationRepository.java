package where.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import where.Entities.Application;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
