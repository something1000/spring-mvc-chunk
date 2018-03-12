package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.ReplyChunkEntity;
import com.gawrych.readinglist.Model.ReplyChunkRepository;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class DeleteReplyController {
    ReplyChunkRepository replyChunkService;
    UserService userService;
    @Autowired
    public DeleteReplyController(ReplyChunkRepository replyChunkService,UserService userService) {
        this.replyChunkService = replyChunkService;
        this.userService=userService;
    }

    @RequestMapping(value = "/deletereply/{reply_id}")
    public String showDeleteReplyForm(@PathVariable Long reply_id, Model model, Principal principal){
        model.addAttribute("chunk",replyChunkService.findById(reply_id));
        return "deleteView";
    }

    @RequestMapping(value = "/deletereply/{reply_id}", method = RequestMethod.POST)
    public String deleteReply(@PathVariable Long reply_id, String reason, Model model, Principal principal){
        ReplyChunkEntity reply = replyChunkService.findById(reply_id);
        if(!reply.isDeleted()){
            reply.setDeleted(true);
            reply.setDeleteReason(reason);
            reply.setDeleter(userService.findByUsername(principal.getName()));
        } else {
            reply.setDeleted(false);
            reply.setDeleteReason(null);
        }

        replyChunkService.save(reply);
            return "redirect:/";
    }
}
