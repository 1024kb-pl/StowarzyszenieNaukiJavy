package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.FilterOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.OrderOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.UserServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.EntityCreator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TaskController
{
    private TaskServiceImpl taskService;
    private UserServiceImpl userService;
    private List<Task> taskList = new LinkedList<>();

    //@Autowired
    public TaskController(TaskServiceImpl taskService, UserServiceImpl userService)
    {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/addTask")
    public String redirectToAddTask()
    {
        return "addtask";
    }

    @PostMapping("/addTask")
    public String addTask(HttpServletRequest request, @SessionAttribute String username) throws IOException
    {
        request.setCharacterEncoding("UTF-8");

        Task task = new EntityCreator().createTask(request);

        String message = "Pomyślnie zapisano zadanie ;)";
        try
        {
            taskService.createTask(task, username);

        } catch (SQLException e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(HttpServletRequest request, @RequestParam Long id)
    {
        try
        {
            taskService.deleteTaskById(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "tasks";
    }

    @GetMapping("/editTask")
    public String redirectToEditTask(HttpServletRequest request, @RequestParam String id, @RequestParam String title,
                           @RequestParam String date, @RequestParam String description, @RequestParam String done)
    {
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("date", date);
        request.setAttribute("description", description);
        request.setAttribute("done", done);

        return "edittask";
    }

    @PostMapping("/editTask")
    public String editTask(HttpServletRequest request, @SessionAttribute String username) throws IOException
    {
        request.setCharacterEncoding("UTF-8");

        Task task = new EntityCreator().updateTask(request);

        String message = "Pomyślnie zaktualizowano zadanie :)";
        try
        {
            taskService.changeTask(task, username);

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @RequestMapping(value = "/tasks", method = {RequestMethod.GET, RequestMethod.POST})
    public String redirectToTasks(HttpServletRequest request, @SessionAttribute String username)
    {
        taskList = taskService.getAllTasksByUsername(username);

        filterTask(username, request);
        sortTaskList(username, request);

        request.setAttribute("tasksList", taskList);

        return "taskslist";
    }

    private void sortTaskList(String username, HttpServletRequest request)
    {
        String sortList = request.getParameter("sort");
        if(sortList != null && !sortList.isEmpty())
        {
            OrderOption orderOption = OrderOption.valueOf(sortList.toUpperCase());
            switch (orderOption)
            {
                case TITLE:
                    taskList = taskService.getAllTasksOrderedByTitle(username);
                    break;
                case DATE:
                    taskList = taskService.getAllTasksOrderedByDate(username);
                    break;
                case STATUS:
                    taskList = taskService.getAllTasksOrderedByStatus(username);
                    break;
                default:
                    taskList = taskService.getAllTasksByUsername(username);
                    break;
            }
        }
    }

    private void filterTask(String username, HttpServletRequest request)
    {
        String filterList = request.getParameter("filter");
        if(filterList != null && !filterList.isEmpty())
        {
            FilterOption filterOption = FilterOption.valueOf(filterList.toUpperCase());
            switch (filterOption)
            {
                case DATE:
                    LocalDate date = LocalDate.parse(request.getParameter("dateFilter"));
                    taskList = taskService.getAllTasksByDate(username, date);
                    break;
                case TRUE:
                    taskList = taskService.getAllTasksByTaskDone(username, true);
                    break;
                case FALSE:
                    taskList = taskService.getAllTasksByTaskDone(username, false);
                    break;
                default:
                    taskList = taskService.getAllTasksByUsername(username);
                    break;
            }

        }
    }
}
