package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    String createUser(User user);
    String editUser(User user, String username);
    String removeUser(String username);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    boolean isUsernameAlreadyExist(String username);
    boolean loginVerification(String username, String password);
}
