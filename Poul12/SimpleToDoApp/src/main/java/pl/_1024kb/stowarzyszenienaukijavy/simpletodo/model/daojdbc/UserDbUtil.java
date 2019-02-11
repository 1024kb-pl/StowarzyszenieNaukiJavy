package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.MD5Hash;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDbUtil implements UserDao
{
    public final static User DEFAULT_USER = new User("annon", "pass");
    private static UserDbUtil instance;

    private UserDbUtil()
    {
        if(instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }

    public static UserDbUtil getInstance()
    {
        if(instance == null)
            instance = new UserDbUtil();

        return instance;
    }

    @Override
    public void saveUser(User user) throws SQLException
    {
        String sqlQuery = "INSERT INTO users(username, password, email) VALUES(?, ?, ?)";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            setUser(statement, user);

            statement.execute();
        }
    }

    @Override
    public void updateUser(User user, String username) throws SQLException
    {
        String sqlQuery = "UPDATE users SET username=?, password=?, email=? WHERE username=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))

        {
            setUser(statement, user);
            statement.setString(4, username);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteUser(String username) throws SQLException
    {
        String sqlQuery = "DELETE FROM users WHERE username=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            statement.setString(1, username);

            statement.executeUpdate();
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) throws SQLException
    {
        String selectQuery = String.format("SELECT * FROM users WHERE username='%s'", username);
        try(Connection connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery))
        {

            while (resultSet.next())
                return Optional.of(getUser(resultSet));
        }

       return Optional.empty();
    }
    
    
    @Override
    public List<User> getAllUsers()
    {
        List<User> usersList = new LinkedList<>();
        String selectQuery = "SELECT * FROM users";
        try(Connection connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery))
        {
            while(resultSet.next())
            {
                User user = getUser(resultSet);
                usersList.add(user);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return usersList;
    }

    private User getUser(ResultSet resultSet) throws SQLException
    {
        Long userId = resultSet.getLong("user_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");

        return new User(userId, username, password, email);
    }

    private void setUser(PreparedStatement statement, User user) throws SQLException
    {
        String hashPassword = MD5Hash.encode(user.getPassword());

        statement.setString(1, user.getUsername());
        statement.setString(2, hashPassword);
        statement.setString(3, user.getEmail());
    }

}
