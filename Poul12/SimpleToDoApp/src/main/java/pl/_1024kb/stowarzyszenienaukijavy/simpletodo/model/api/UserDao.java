package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.util.List;

public interface UserDao
{
    String saveUser(User user, String message);
    User getUserByUsername(String username);
    List<User> getAllUsers();

}
