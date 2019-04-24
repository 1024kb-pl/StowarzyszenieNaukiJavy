package pl._1024kb.stowarzyszenienaukijavy.simpletodo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("ALL")
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) throws SQLException {
        entityManager.persist(user);
    }

    @Override
    public User read(String username) throws UserNotFoundException
    {
        TypedQuery<User> query = entityManager.createNamedQuery("User.getUserByUsername", User.class);
        query.setParameter("username", username);
        return query.getResultList()
                    .stream()
                    .findFirst()
                    .orElseThrow(this::newRunTimeException);
    }

    @Override
    public void update(User user) throws SQLException {
        User foundUser = entityManager.find(User.class, user.getUserId());
        if(foundUser != null)
        {
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            foundUser.setEmail(user.getEmail());
        }

        entityManager.merge(user);
    }

    @Override
    public void delete(Long userId) throws SQLException
    {
        User userToRemove = entityManager.find(User.class, userId);
        entityManager.remove(userToRemove);
    }

    @Override
    public List<User> getAllUsers()
    {
        TypedQuery<User> getAllQuery = entityManager.createNamedQuery("User.getAll", User.class);
        return getAllQuery.getResultList();
    }

    private UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired user");
    }
}
