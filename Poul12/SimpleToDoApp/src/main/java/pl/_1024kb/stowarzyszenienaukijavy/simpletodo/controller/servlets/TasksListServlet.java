package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TasksListServlet extends HttpServlet
{
    TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getSession(false).getAttribute("username").toString();
        List<Task> taskList = taskService.getAllTasksByUserId(username);

        request.setAttribute("tasksList", taskList);
        request.getRequestDispatcher("taskslist.jsp").forward(request, response);
    }
}
