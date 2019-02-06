package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.util.List;

public interface UserService
{
    String createUser(User user);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    boolean isUsernameAlreadyExist(String username);
    boolean loginVerification(String username, String password);
}
