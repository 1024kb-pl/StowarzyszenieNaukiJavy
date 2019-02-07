package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet
{
    TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idStr = request.getParameter("id");
        long id = Long.parseLong(idStr);

        taskService.deleteTaskById(id);

        request.getRequestDispatcher("tasks").forward(request, response);
    }
}
