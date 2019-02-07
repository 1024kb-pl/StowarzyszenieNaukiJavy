package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkTask")
public class CheckTaskServlet extends HttpServlet
{
    private TaskServiceImpl taskService = TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String checkDone = request.getParameter("checktask");
        System.out.println(checkDone);
        if(checkDone == null)
            checkDone = "no";

        String checkIdStr = request.getParameter("task_id");
        long checkId = Long.parseLong(checkIdStr);

        taskService.setCheckTask(checkDone, checkId);

        request.getRequestDispatcher("tasks").forward(request, response);
    }
}
