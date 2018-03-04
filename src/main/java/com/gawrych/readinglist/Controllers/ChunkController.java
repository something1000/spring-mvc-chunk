package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ChunkController {

    private ChunkService chunkService;
    private UserService userService;
    @Autowired
    public ChunkController(ChunkService chunkService, UserService userService) {
        this.chunkService = chunkService;
        this.userService = userService;
    }

    @RequestMapping(value = "/chunk",method= RequestMethod.POST)
    public String addChunk(@Valid ChunkEntity chunk, Principal currentUser){
        chunk.setAuthorID(userService.findByUsername(currentUser.getName()));
        chunkService.saveChunk(chunk);
        return "redirect:/";
    }
}