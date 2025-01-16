package where.Repositories;

import org.springframework.data.repository.CrudRepository;
import where.Entities.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation,Long> {
    Validation findByCode(String code);

    Optional<Validation> findByUtilisateurEmail(String email);

}
