package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

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
        String password = request.getParameter("password");
        String repeatedPass = request.getParameter("repeated-pass");
        String email = request.getParameter("email");

        String sessionUsername = (String) request.getSession(false).getAttribute("username");
        if(!sessionUsername.equals(username))
            request.getSession(false).setAttribute("username", username);

        String message = userServiceImpl.editUser(new User(username, password, repeatedPass, email), sessionUsername);

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
