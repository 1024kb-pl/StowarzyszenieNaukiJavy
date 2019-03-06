package pl.kostrzej.simpleToDoApp.components.validator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserAlreadyExistsException;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

@Component
@Slf4j
@AllArgsConstructor
public class UserValidator {

    private UserRepository userRepository;

    public void validate(User user){
        log.info("User validation process initialized. " + user);
        if (user.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Login nie może być pusty!");
        }
        if (user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Hasło nie może być puste!");
        }
        if (user.getQuestion().isEmpty()) {
            throw new IllegalArgumentException("Pytanie pomocnicze nie może być puste!");
        }
        if (user.getAnswer().isEmpty()) {
            throw new IllegalArgumentException("Odpowiedź nie może być pusta!");
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new UserAlreadyExistsException("Użytkownik o podanym lognie istnieje!");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("Użytkownik o podanym adresie email istnieje!");
        }
    }
}
