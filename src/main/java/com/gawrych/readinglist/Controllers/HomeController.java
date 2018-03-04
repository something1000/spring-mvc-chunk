package com.gawrych.readinglist.Controllers;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @RequestMapping({"/","/main"})
    public String home(HttpServletRequest req){
        req.setAttribute("xd", 3);
        return "mainView";
    }

}
