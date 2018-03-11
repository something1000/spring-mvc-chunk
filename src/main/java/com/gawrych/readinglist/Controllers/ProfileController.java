package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChunkService chunkService;

    @RequestMapping(value = "/profile/{username}",method = RequestMethod.GET)
    public String getUserInfo(){
        return "profile";
    }
}
