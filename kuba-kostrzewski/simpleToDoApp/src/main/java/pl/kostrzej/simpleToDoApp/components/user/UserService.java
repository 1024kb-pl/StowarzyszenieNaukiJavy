package pl.kostrzej.simpleToDoApp.components.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.validator.UserValidator;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

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
