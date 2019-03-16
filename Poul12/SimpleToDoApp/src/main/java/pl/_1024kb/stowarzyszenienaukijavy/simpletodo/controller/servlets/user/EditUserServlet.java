package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.EntityCreator;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.UserNotFoundException;
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
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = (String) request.getSession(false).getAttribute("username");
        User user = userService.getUserByUsername(username).orElseThrow(this::newRunTimeException);

        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());

        if(username != null)
        {
            request.getRequestDispatcher("WEB-INF/pages/edituser.jsp").forward(request, response);
        }else
        {
            response.sendError(403);
        }
    }

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
            userService.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
    }

    private UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired user");
    }
}
