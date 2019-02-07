package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet
{
    TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idStr = request.getParameter("task_id");
        long taskId = Long.parseLong(idStr);
        String title = request.getParameter("title");
        String dateStr = request.getParameter("taskdate");
        LocalDate date = LocalDate.parse(dateStr);
        String description = request.getParameter("description");

        Task task = new Task(taskId, title, date, description);
        String message = taskService.changeTask(task);

        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
}
