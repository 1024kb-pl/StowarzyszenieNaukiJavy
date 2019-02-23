package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet
{
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = (String) request.getSession(false).getAttribute("username");

        String message = "Pomyślnie usunięto użytkownika";
        try
        {
            taskService.deleteAllTasks(username);
            userService.removeUser(username);
            request.getSession(false).invalidate();

        }catch (Exception e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
