package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MysqlUserDao implements UserDao
{
    public final static User DEFAULT_USER = new User("annon", "pass");

    private final static String CREATE = "INSERT INTO users(username, password, email) VALUES(:username, :password, :email);";
    private final static String READ = "SELECT * FROM users WHERE username = :username;";
    private final static String UPDATE = "UPDATE users SET username=:username, password=:password, email=:email WHERE user_id = :user_id;";
    private final static String DELETE = "DELETE FROM users WHERE user_id=:user_id;";
    private final static String READ_ALL = "SELECT * FROM users";


    private NamedParameterJdbcTemplate template;

    public MysqlUserDao()
    {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

    @Override
    public void create(User user)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
        template.update(CREATE, beanParamSource);
    }

    @Override
    public Optional<User> read(String username)
    {
        User user = DEFAULT_USER;

        SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
        List<User> userList = template.query(READ, namedParameters, BeanPropertyRowMapper.newInstance(User.class));

        if(!userList.isEmpty())
            user = userList.get(0);

        return Optional.of(user);
    }


    @Override
    public void update(User user)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
        template.update(UPDATE, beanParamSource);
    }

    @Override
    public void delete(Long user_id)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("user_id", user_id);
        template.update(DELETE, namedParameter);
    }
    
    @Override
    public List<User> getAllUsers()
    {
        List<User> userList = template.query(READ_ALL, BeanPropertyRowMapper.newInstance(User.class));

        if(!userList.isEmpty())
            return userList;

        return Collections.emptyList();
    }

}
