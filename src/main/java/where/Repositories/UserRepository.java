package where.Repositories;

import org.springframework.data.repository.CrudRepository;
import where.Entities.User;

public interface UserRepository extends CrudRepository<User,Long> {

}
