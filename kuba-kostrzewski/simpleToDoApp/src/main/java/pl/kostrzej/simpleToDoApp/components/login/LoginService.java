package pl.kostrzej.simpleToDoApp.components.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User logIn(String login, String password){
        log.info("Checking if user exists in database");
        User user = userRepository.findByLogin(login).orElseThrow(() -> {
            log.info("User \"{}\" not exists. Authorization failed.", login);
            return new InvalidLoginDataException();
        });
        log.info("User: " + user);
        if (user.getPassword().equals(password)){
            log.info("Authorization finished successfully.");
            return user;
        }
        else {
            log.info("Password incorrect. Authorization failed.");
            throw new InvalidLoginDataException();
        }
    }
}
