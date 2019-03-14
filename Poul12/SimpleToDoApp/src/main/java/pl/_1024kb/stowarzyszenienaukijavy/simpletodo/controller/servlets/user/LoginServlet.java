package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    private UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
         request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String message = "Nie udało się zalogować :(";

        try
        {
            if(userServiceImpl.loginVerification(username, password))
            {
                message = "Witaj " + username + " :)";
                request.getSession(true).setAttribute("username", username);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
        
    }
}
