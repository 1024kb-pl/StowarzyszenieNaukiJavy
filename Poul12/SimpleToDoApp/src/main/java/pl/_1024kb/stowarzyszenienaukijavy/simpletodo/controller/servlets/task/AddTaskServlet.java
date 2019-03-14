package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.EntityCreator;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet
{
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(request.getSession(false).getAttribute("username") != null)
        {
            request.getRequestDispatcher("WEB-INF/pages/addtask.jsp").forward(request, response);
        }else
        {
            response.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        Task task = new EntityCreator().createTask(request);

        String username = request.getSession(false).getAttribute("username").toString();

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
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);

    }
}
