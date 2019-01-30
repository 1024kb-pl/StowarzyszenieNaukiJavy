package pl.kostrzej.simpleToDoApp.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.validator.UserValidator;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void addUser(User user) {
        userValidator.validate(user);
        userRepository.save(user);
    }

}
