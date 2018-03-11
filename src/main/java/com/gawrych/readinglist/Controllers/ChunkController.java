package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

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
    public String addChunk(@Valid ChunkEntity chunk, @RequestParam(required = false, name = "reply") Long reply, Principal currentUser){
        System.out.println("T: "+chunk.getId());
        if(reply != null){
            chunk.setReplyTo(chunkService.findChunkById(reply));
            System.out.println("X: "+chunk.getId());
        }
        chunk.setAuthor(userService.findByUsername(currentUser.getName()));
        chunk.setPostdate(LocalDateTime.now());
        chunkService.saveChunk(chunk);
        return "redirect:/";
    }

}
