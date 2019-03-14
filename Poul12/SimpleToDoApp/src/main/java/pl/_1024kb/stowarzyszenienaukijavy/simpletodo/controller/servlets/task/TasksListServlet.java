package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.FilterOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.OrderOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/tasks")
public class TasksListServlet extends HttpServlet
{
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();
    private List<Task> taskList = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getSession(false).getAttribute("username").toString();

        taskList = taskService.getAllTasksByUserId(username);

        filterTask(username, request);

        sortTaskList(username, request);

        request.setAttribute("tasksList", taskList);
        request.getRequestDispatcher("WEB-INF/pages/taskslist.jsp").forward(request, response);
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
                    taskList = taskService.getAllTasksByUserId(username);
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
                    taskList = taskService.getAllTasksByUserId(username);
                    break;
            }

        }
    }

}