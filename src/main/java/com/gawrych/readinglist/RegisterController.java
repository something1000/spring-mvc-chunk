package com.gawrych.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    PortalUsersRepository portalUsersRepository;


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerView(){
        return "registerView";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam @Valid PortalUser user, Model model){
        portalUsersRepository.save(user);
        return "redirect:/";
    }
}
