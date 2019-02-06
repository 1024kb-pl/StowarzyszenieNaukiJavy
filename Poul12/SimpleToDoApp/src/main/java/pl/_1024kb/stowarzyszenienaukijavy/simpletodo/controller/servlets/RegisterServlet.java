package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    private UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPass = request.getParameter("repeated-pass");
        String email = request.getParameter("email");

        String message = "Passwords are not the same!";

        if(password.equals(repeatedPass) && !userServiceImpl.isUsernameAlreadyExist(username))
            message = userServiceImpl.createUser(new User(username, password, email));

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);

    }
}

