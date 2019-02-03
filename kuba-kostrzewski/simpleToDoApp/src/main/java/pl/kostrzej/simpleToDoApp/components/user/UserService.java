package pl.kostrzej.simpleToDoApp.components.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.validator.UserValidator;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;

    public void addUser(User user) {
        log.info("Add new user to database process initialized.");
        userValidator.validate(user);
        userRepository.save(user);
        log.info("User saved in database successfully.");
    }
    public User getUser(long id){
        log.info("Get user by id process initialized.");
        return userRepository.findById(id).get();
    }

}
