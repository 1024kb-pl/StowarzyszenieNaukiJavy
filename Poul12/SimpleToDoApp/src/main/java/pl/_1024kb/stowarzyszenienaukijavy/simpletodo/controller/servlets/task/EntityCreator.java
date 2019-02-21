package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

public class EntityCreator
{
    private String[] keys;

    public EntityCreator(int length)
    {
        keys = new String[length];
    }

    public Task createTask(HttpServletRequest request)
    {
        setKeys(request);
        return new Task(Long.parseLong(keys[0]), keys[1], LocalDate.parse(keys[2]), keys[3], Boolean.valueOf(keys[4]));
    }

    public User createUser(HttpServletRequest request)
    {
        setKeys(request);
        return new User(keys[0], keys[1], keys[2], keys[3]);
    }

    private void setKeys(HttpServletRequest request)
    {
        String[] values;
        int i = 0;
        for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet())
        {
            values = entry.getValue();
            keys[i] = values[0];
            i++;
        }
    }
}
