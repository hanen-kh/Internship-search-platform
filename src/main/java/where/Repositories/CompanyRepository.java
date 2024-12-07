package where.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import where.Entities.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByName(String name);
}
