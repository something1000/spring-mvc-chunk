package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.User;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ConfirmController {

    @Autowired
    private UserService userService;

    @RequestMapping("/confirm")
    public String confirmEmail(Model model, @RequestParam("token") String token, RedirectAttributes redirectAttributes){
        User user = userService.findByConftoken(token);

        if(user == null){
            model.addAttribute("badtoken","Podany token jest nie prawidlowy");
            return "redirect:/";
        } else {
            model.addAttribute("goodtoken",user.getConftoken());
            user.setEnabled(true);
           redirectAttributes.addFlashAttribute("potwierdzono","Potwierdzam");
        }
        return "redirect:/register";
    }


}
