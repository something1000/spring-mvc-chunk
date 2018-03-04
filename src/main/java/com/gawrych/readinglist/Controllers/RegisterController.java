package com.gawrych.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class RegisterController {

    private UserService userService;
    private MailService mailService;

    @Autowired
    public RegisterController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerView(Model model, User user){
        model.addAttribute("user", user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/";
        }

        return "registerView";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult bindingResult,Model model, HttpServletResponse httpServletResponse){

       User exists = userService.findByEmail(user.getEmail());

       System.out.println(exists);

       if(exists != null){
           model.addAttribute("istnieje", "Wyglada na to ze uzytkownik juz istnieje");
           return "register";
       }

       if(bindingResult.hasErrors()){
           System.out.println(bindingResult.getAllErrors());
           return "registerView";
       } else {

           user.setEnabled(false);
           user.setPassword(user.getPassword());
           user.setConftoken(UUID.randomUUID().toString());
           System.out.println(user.getConftoken());

           userService.saveUser(user);
       }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/";
        }
        return "loginView";
    }
}
