package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

public class EntityCreator
{
    private String[] keys;

    public EntityCreator()
    {
    }

    public Task createTask(HttpServletRequest request)
    {
        keys = new String[4];
        setKeys(request);
        return Task.builder()
                .title(keys[0])
                .date(LocalDate.parse(keys[1]))
                .description(keys[2])
                .taskDone(Boolean.valueOf(keys[3]))
                .build();
    }

    public Task updateTask(HttpServletRequest request)
    {
        keys = new String[5];
        setKeys(request);
        return Task.builder()
                .taskId(Long.parseLong(keys[0]))
                .title(keys[1])
                .date(LocalDate.parse(keys[2]))
                .description(keys[3])
                .taskDone(Boolean.valueOf(keys[4]))
                .build();
    }

    public User createUser(HttpServletRequest request)
    {
        keys = new String[4];
        setKeys(request);
        return User.builder()
                   .username(keys[0])
                   .email(keys[1])
                   .password(keys[2])
                   .repeatedPassword(keys[3])
                   .build();
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
