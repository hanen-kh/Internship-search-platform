package where.Repositories;

import org.springframework.data.repository.CrudRepository;
import where.Entities.Role;
import where.Entities.TypeRole;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByLibelle(TypeRole libelle);
}

