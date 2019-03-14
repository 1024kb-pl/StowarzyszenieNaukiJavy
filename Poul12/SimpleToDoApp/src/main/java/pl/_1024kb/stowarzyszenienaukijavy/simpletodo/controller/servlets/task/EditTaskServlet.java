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

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet
{
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        String doneTask = request.getParameter("done");

        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("date", date);
        request.setAttribute("description", description);
        request.setAttribute("done", doneTask);

        request.getRequestDispatcher("WEB-INF/pages/edittask.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        Task task = new EntityCreator().updateTask(request);

        String message = "Pomyślnie zaktualizowano zadanie :)";
        try
        {
            taskService.changeTask(task);

        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
    }
}
