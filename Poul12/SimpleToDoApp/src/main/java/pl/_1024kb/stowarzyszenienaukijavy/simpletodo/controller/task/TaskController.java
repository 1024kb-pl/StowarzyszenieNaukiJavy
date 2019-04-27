package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.FilterOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.OrderOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.EntityCreator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TaskController
{
    private TaskServiceImpl taskService;
    private List<Task> taskList = new LinkedList<>();

    @Autowired
    public TaskController(TaskServiceImpl taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/addTask")
    public String redirectToAddTask(Model model)
    {
        model.addAttribute("task", new Task());
        return "addtask";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task, Model model, @SessionAttribute String username)
    {
        //request.setCharacterEncoding("UTF-8");

        //Task task = new EntityCreator().createTask(model);
        System.out.println("task: " + task);

        String message = "Pomyślnie zapisano zadanie ;)";
        try
        {
            taskService.createTask(task, username);

        } catch (SQLException e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id)
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
    public String redirectToEditTask(Model model, @RequestParam String id, @RequestParam String title,
                           @RequestParam String date, @RequestParam String description, @RequestParam String done)
    {
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("date", date);
        model.addAttribute("description", description);
        model.addAttribute("done", done);

        return "edittask";
    }

    @PostMapping("/editTask")
    public String editTask(Model model, @SessionAttribute String username)
    {
        //request.setCharacterEncoding("UTF-8");

        Task task = new EntityCreator().updateTask(model);

        String message = "Pomyślnie zaktualizowano zadanie :)";
        try
        {
            taskService.changeTask(task, username);

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/tasks")
    public String redirectToTasks(Model model, @SessionAttribute String username)
    {
        taskList = taskService.getAllTasksByUsername(username);

        model.addAttribute("tasksList", taskList);

        return "taskslist";
    }

    @PostMapping("/tasks")
    public String orderTask(Model model, @SessionAttribute String username)
    {
        filterTask(username, model);
        sortTaskList(username, model);

        return "taskslist";
    }

    private void sortTaskList(String username, Model model)
    {
        String sortList = model.asMap().get("sort").toString();
        System.out.println("sortList from model " + sortList);
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

    private void filterTask(String username, Model model)
    {
        String filterList = model.asMap().get("filter").toString();
        System.out.println("filterList from model " + filterList);
        if(filterList != null && !filterList.isEmpty())
        {
            FilterOption filterOption = FilterOption.valueOf(filterList.toUpperCase());
            switch (filterOption)
            {
                case DATE:
                    LocalDate date = LocalDate.parse(model.asMap().get("dateFilter").toString());
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
