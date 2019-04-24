package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String home()
    {
        return "index";
    }
}
