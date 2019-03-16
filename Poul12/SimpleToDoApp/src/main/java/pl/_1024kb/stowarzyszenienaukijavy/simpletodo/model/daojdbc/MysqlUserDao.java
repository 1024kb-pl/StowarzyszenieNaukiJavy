package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.util.List;

public class MysqlUserDao implements UserDao
{
    private final static String CREATE = "INSERT INTO users(username, password, email) VALUES(:username, :password, :email);";
    private final static String READ = "SELECT * FROM users WHERE username = :username;";
    private final static String UPDATE = "UPDATE users SET username=:username, password=:password, email=:email WHERE userId = :userId;";
    private final static String DELETE = "DELETE FROM users WHERE userId=:userId;";
    private final static String READ_ALL = "SELECT * FROM users";
    private final static String READ_CRED = "SELECT pass FROM mail";

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
    public User read(String username)
    {
        User user = null;

        SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
        List<User> userList = template.query(READ, namedParameters, BeanPropertyRowMapper.newInstance(User.class));

        if(!userList.isEmpty())
            user = userList.get(0);

        return user;
    }


    @Override
    public void update(User user)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
        template.update(UPDATE, beanParamSource);
    }

    @Override
    public void delete(Long userId)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("userId", userId);
        template.update(DELETE, namedParameter);
    }
    
    @Override
    public List<User> getAllUsers()
    {
        return template.query(READ_ALL, BeanPropertyRowMapper.newInstance(User.class));
    }


}
