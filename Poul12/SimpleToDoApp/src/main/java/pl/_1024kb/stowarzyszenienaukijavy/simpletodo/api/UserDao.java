package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import org.springframework.data.repository.query.Param;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException;

    User read(String username) throws UserNotFoundException;

    //@Query("SELECT CASE WHEN count(u)> 0 THEN true ELSE false END FROM User u WHERE lower(u.username) LIKE lower(:username)")
    boolean isUserExist(@Param("username")String username);

    void update(User user) throws SQLException;

    void delete(Long user_id) throws SQLException;

    List<User> getAllUsers();
}
