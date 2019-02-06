package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet
{
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String dateStr = request.getParameter("taskdate");
        String description = request.getParameter("description");

        LocalDate date = LocalDate.parse(dateStr);

        String username = request.getSession(false).getAttribute("username").toString();
        Task task = new Task(title, date, description);

        String message = taskService.createTask(task, username);

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);

    }
}
