package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.MailSender;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reset")
public class ResetPasswordServlet extends HttpServlet
{
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("WEB-INF/pages/resetpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);

        String message = "Password reset, please check Your email :)";
        String newPass = user.getPassword().substring(0, 10);
        try
        {
            MailSender.sendEmail(email, newPass);
        } catch (MessagingException e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        try
        {
            user.setPassword(newPass);
            user.setRepeatedPassword(newPass);
            userService.editUser(user);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
    }
}
