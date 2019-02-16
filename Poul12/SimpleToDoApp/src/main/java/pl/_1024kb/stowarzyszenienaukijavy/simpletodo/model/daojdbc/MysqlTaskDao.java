package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.util.Collections;
import java.util.List;

public class MysqlTaskDao implements TaskDao
{
    private final static String CREATE = "INSERT INTO tasks(date, title, description, user_id) VALUES(:date, :title, :description, :user_id);";
    private final static String READ = "SELECT * FROM tasks WHERE user_id = :user_id;";
    private final static String UPDATE = "UPDATE tasks SET date=:date, title=:title, description=:description, task_done=:task_done WHERE task_id = :task_id;";
    private final static String DELETE = "DELETE FROM tasks WHERE task_id=:task_id;";
    private final static String DELETE_ALL = "DELETE FROM tasks WHERE user_id=:user_id;";

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
    public List<Task> read(Long user_id)
    {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", user_id);
        List<Task> tasksList = template.query(READ, namedParameters, BeanPropertyRowMapper.newInstance(Task.class));

        if(!tasksList.isEmpty())
            return tasksList;

        return Collections.emptyList();
    }

    @Override
    public void update(Task task)
    {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(task);
        template.update(UPDATE, beanParamSource);
    }

    @Override
    public void delete(Long task_id)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("task_id", task_id);
        template.update(DELETE, namedParameter);
    }

    @Override
    public void deleteAllTasks(Long user_id)
    {
        SqlParameterSource namedParameter = new MapSqlParameterSource("user_id", user_id);
        template.update(DELETE_ALL, namedParameter);
    }
}
