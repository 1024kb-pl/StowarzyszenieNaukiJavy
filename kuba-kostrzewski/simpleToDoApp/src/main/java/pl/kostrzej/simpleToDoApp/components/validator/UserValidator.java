package pl.kostrzej.simpleToDoApp.components.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserAlreadyExistsException;
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
            throw new IllegalArgumentException("Login nie może być pusty!");
        }
        if (user.getPassword().isEmpty()) {
            log.info("Password is empty.");
            throw new IllegalArgumentException("Hasło nie może być puste!");
        }
        if (user.getQuestion().isEmpty()) {
            log.info("Question is empty.");
            throw new IllegalArgumentException("Pytanie pomocnicze nie może być puste!");
        }
        if (user.getAnswer().isEmpty()) {
            log.info("Answer is empty.");
            throw new IllegalArgumentException("Odpowiedź nie może być pusta!");
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            log.info("User \"{}\" already exists in database", user.getLogin());
            throw new UserAlreadyExistsException("Użytkownik o podanym lognie istnieje!");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            log.info("Email \"{}\" already exists in database", user.getEmail());
            throw new UserAlreadyExistsException("Użytkownik o podanym adresie email istnieje!");
        }
    }
}
