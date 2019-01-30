package pl.kostrzej.simpleToDoApp.components.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(User user){
        if (userRepository.existsByLogin(user.getLogin()))
            throw new UserAlreadyExistsException();
    }
}
