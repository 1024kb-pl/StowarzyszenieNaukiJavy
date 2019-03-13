package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.EntityCreator;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet
{
    private UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        String sessionUsername = (String) request.getSession(false).getAttribute("username");
        if(!sessionUsername.equals(username))
        {
            request.getSession(false).setAttribute("username", username);
        }

        User user = new EntityCreator().createUser(request);
        String message = "Pomyślnie zmieniono dane użytownika";
        try
        {
            userServiceImpl.editUser(user, sessionUsername);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
