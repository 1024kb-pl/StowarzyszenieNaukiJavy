package pl.kostrzej.simpleToDoApp.components.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User logIn(String login, String password){
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) return user;
        else throw new InvalidLoginDataException();
    }
}
