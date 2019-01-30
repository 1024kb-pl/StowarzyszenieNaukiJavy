package pl.kostrzej.simpleToDoApp.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(String login, String password, String email, String question, String answer){
        if (!userRepository.existsByLogin(login)){
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setQuestion(question);
            user.setAnswer(answer);
            userRepository.save(user);
        } else throw new UserAlreadyExistsException();
    }

}
