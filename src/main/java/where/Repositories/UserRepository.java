package where.Repositories;

import org.springframework.data.repository.CrudRepository;
import where.Entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
