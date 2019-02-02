package pl.kostrzej.simpleToDoApp.components.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

@Component
@Slf4j
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(User user){
        log.info("User validation process initialized.");
        if (user.getLogin().isEmpty()) {
            log.info("Login is empty.");
            throw new NullPointerException("Login nie może być pusty!");
        }
        if (user.getPassword().isEmpty()) {
            log.info("Password is empty.");
            throw new NullPointerException("Hasło nie może być puste!");
        }
        if (user.getQuestion().isEmpty()) {
            log.info("Question is empty.");
            throw new NullPointerException("Pytanie pomocnicze nie może być puste!");
        }
        if (user.getAnswer().isEmpty()) {
            log.info("Answer is empty.");
            throw new NullPointerException("Odpowiedź nie może być pusta!");
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            log.info("User \"" + user.getLogin() + "\" already exists in database");
            throw new UserAlreadyExistsException();
        }
    }
}
