package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.ReplyChunkEntity;
import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ReplyController {
    private UserService userService;
    private ChunkService chunkService;

    @Autowired
    public ReplyController(UserService userService, ChunkService chunkService) {
        this.userService = userService;
        this.chunkService = chunkService;
    }

    @RequestMapping(value="/reply", method = RequestMethod.POST)
    public String addReply(@Valid ReplyChunkEntity replyChunkEntity, @RequestParam(required = true,name="pid") Long pid, Principal principal, HttpServletRequest request){
        replyChunkEntity.setPostdate(LocalDateTime.now());
        replyChunkEntity.setAuthor(userService.findByUsername(principal.getName()));
        replyChunkEntity.setReplyTo(chunkService.findChunkById(pid));
        chunkService.saveChunkReply(replyChunkEntity);
        return "redirect:"+request.getHeader("Referer");
    }
}
