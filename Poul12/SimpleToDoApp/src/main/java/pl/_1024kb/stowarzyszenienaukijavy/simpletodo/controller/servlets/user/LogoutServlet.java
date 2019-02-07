package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet
{
    private static Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = "Nie udało się wylogować :/";
        String username = (String) request.getSession().getAttribute("username");
        if (request.getSession(false) != null) 
        {
            request.getSession().invalidate();
            message = "Wylogowano ;)";
            logger.info("Pomyślnie wylogowano użytkownika {}", username);
        }
        else
            logger.error("Błąd podczas wylogowywania się uzytkownika {}", username);

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
        
    }
}
