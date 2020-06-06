package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import org.springframework.stereotype.Component;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService
{

    void createUser(User user) throws Exception;

    void editUser(User user) throws Exception;

    void removeUser(String username) throws Exception;

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    boolean isUsernameAlreadyExist(String username);

    boolean isEmailAlreadyExist(String email);
}
