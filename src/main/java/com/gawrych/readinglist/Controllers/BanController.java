package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.User;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
public class BanController {

    private UserService userService;

    public BanController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/ban/{id}")
    public String banUser(@PathVariable("id") Long id){
        User x = userService.findById(id);
        x.setBanned(true);
        x.setBan_date(LocalDate.of(2018,3,5));
        userService.updateUser(x);
        return "redirect:/";
    }
}
