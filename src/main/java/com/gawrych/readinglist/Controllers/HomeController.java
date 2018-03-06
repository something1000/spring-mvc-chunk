package com.gawrych.readinglist.Controllers;


import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    ChunkService chunkService;
    @Autowired
    UserService userService;
    @Value("${chunk.main.page-size}")
    int pageSize;
    @RequestMapping(value = {"/","/main"}, method = RequestMethod.GET)
    public String home(@RequestParam(name="page",defaultValue = "1") int page, Model model, Principal user){
        if(user!= null) {
            model.addAttribute("chunks", chunkService.getChunkPage(page-1,pageSize));
            model.addAttribute("pages",chunkService.getNumberOfChunks()/10+1);
        }
        return "mainView";
    }

}
