package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.ReplyChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class DeleteController {

    private UserService userService;
    private ChunkService chunkService;
    private ReplyChunkService replyChunkService;

    @Autowired
    public DeleteController(UserService userService, ChunkService chunkService, ReplyChunkService replyChunkService) {
        this.userService = userService;
        this.chunkService = chunkService;
        this.replyChunkService = replyChunkService;
    }

    @RequestMapping(value = "/delete/{post_id}")
    public String showDeleteForm(@PathVariable Long post_id, Model model, Principal principal){
        model.addAttribute("chunk",chunkService.findChunkById(post_id));
        return "deleteView";
    }



    @RequestMapping(value="/delete/{post_id}", method = RequestMethod.POST)
    public String deleteChunk(@PathVariable Long post_id, @RequestParam(value = "reason") String reason,
                              RedirectAttributes redirectAttributes, Principal principal){
        if(chunkService.findChunkById(post_id).isDeleted()){
            chunkService.undeleteById(post_id, principal.getName());
            return "redirect:/";
        }else if(chunkService.deleteById(post_id, reason, principal.getName())){
            System.out.println(reason);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("isDeleted",false);
            return "deleteView";
        }
    }
}
