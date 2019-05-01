package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.FilterOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.OrderOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TaskController
{
    private TaskServiceImpl taskService;
    private List<Task> taskList;

    @Autowired
    public TaskController(TaskServiceImpl taskService)
    {
        this.taskService = taskService;
        this.taskList = Collections.synchronizedList(new LinkedList<>());
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
    public String deleteTask(@RequestParam Long taskId)
    {
        try
        {
            taskService.deleteTaskById(taskId);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "redirect:/tasks";
    }

    @GetMapping("/editTask")
    public String redirectToEditTask(Model model, @RequestParam String taskId, @RequestParam String title,
                           @RequestParam String date, @RequestParam String description, @RequestParam String taskDone, @SessionAttribute String username)
    {
        /*model.addAttribute("taskId", taskId);
        model.addAttribute("title", title);
        model.addAttribute("date", date);
        model.addAttribute("description", description);
        model.addAttribute("taskDone", taskDone);

        Task task = new EntityCreator().updateTask(model);*/

        Task task = taskService.getTaskById(username, Long.valueOf(taskId));
        task.setTitle(title);
        task.setDate(LocalDate.parse(date));
        task.setDescription(description);
        task.setTaskDone(Boolean.valueOf(taskDone));

        model.addAttribute("task", task);

        return "edittask";
    }

    @PostMapping("/editTask")
    public String editTask(Model model, @ModelAttribute Task task, @SessionAttribute String username)
    {
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
    public String orderTask(HttpServletRequest request, Model model, @SessionAttribute String username)
    {
        filterTask(username, request);
        sortTaskList(username, request);

        model.addAttribute("tasksList", taskList);

        return "taskslist";
    }

    private void filterTask(String username, HttpServletRequest request)
    {
        String filterList = request.getParameter("filter");
        //System.out.println("filterList from model " + filterList);
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

    private void sortTaskList(String username, HttpServletRequest request) {
        String sortList = request.getParameter("sort");
        //System.out.println("sortList from model " + sortList);
        if (sortList != null && !sortList.isEmpty()) {
            OrderOption orderOption = OrderOption.valueOf(sortList.toUpperCase());
            switch (orderOption) {
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
}
