package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.util.List;

public class MysqlTaskDao implements TaskDao
{
    private final static String CREATE = "INSERT INTO tasks(date, title, description, userId) VALUES(:date, :title, :description, :userId);";
    private final static String READ = "SELECT * FROM tasks WHERE userId = :userId;";
    private final static String UPDATE = "UPDATE tasks SET date=:date, title=:title, description=:description, taskDone=:taskDone WHERE taskId = :taskId;";
    private final static String DELETE = "DELETE FROM tasks WHERE taskId=:taskId;";
    private final static String DELETE_ALL = "DELETE FROM tasks WHERE userId=:userId;";

    private NamedParameterJdbcTemplate template;

    public MysqlTaskDao()
    {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

    @Override
    public void create(Task task)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(task);
        template.update(CREATE, beanParamSource);
    }

    @Override
    public List<Task> read(Long userId)
    {
        SqlParameterSource namedParameters = new MapSqlParameterSource("userId", userId);
        return template.query(READ, namedParameters, BeanPropertyRowMapper.newInstance(Task.class));
    }

    @Override
    public void update(Task task)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(task);
        template.update(UPDATE, beanParamSource);
    }

    @Override
    public void delete(Long taskId)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("taskId", taskId);
        template.update(DELETE, namedParameter);
    }

    @Override
    public void deleteAllTasks(Long userId)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("userId", userId);
        template.update(DELETE_ALL, namedParameter);
    }
}
