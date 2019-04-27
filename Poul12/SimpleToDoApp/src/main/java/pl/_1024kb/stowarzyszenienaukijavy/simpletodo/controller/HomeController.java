package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController
{
    @RequestMapping("/")
    public String home(HttpSession session)
    {
        session.setAttribute("username", null);
        return "index";
    }
}
