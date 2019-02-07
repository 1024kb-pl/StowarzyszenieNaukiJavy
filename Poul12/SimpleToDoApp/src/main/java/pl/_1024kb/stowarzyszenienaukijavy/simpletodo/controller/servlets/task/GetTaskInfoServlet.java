package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.servlets.task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getTaskInfo")
public class GetTaskInfoServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String description = request.getParameter("description");

        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("date", date);
        request.setAttribute("description", description);
        request.getRequestDispatcher("edittask.jsp").forward(request, response);
    }
}
