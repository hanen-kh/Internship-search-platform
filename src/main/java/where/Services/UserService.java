package where.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import where.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
