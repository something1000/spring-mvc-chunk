package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class SettingController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChunkService chunkService;


    @RequestMapping(value = "/setting",method = RequestMethod.GET)
    public String getUserInfo(Model model, Principal principal){
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "settingView";
    }

    @PostMapping(value = "/setting")
    public String updateProfile(@RequestParam("avatar") MultipartFile file, Principal principal){
        if(file==null) return "redirect:/";
        long x = System.currentTimeMillis();
        File avatar= new File(System.getProperty("user.dir")+"/avatars/" + file.getOriginalFilename());
        try {
            file.transferTo(avatar);
            if(Files.probeContentType(avatar.toPath()).compareTo("image/jpeg") == 0 || Files.probeContentType(avatar.toPath()).compareTo("image/png") == 0){
                BufferedImage img = ImageIO.read(avatar);//.getScaledInstance(100,100,Image.SCALE_FAST);
                BufferedImage resized = new BufferedImage(200,200,BufferedImage.TYPE_3BYTE_BGR);
                Graphics2D g = resized.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawImage(img,0,0,200,200,null);
                g.dispose();
                File renamed = new File(System.getProperty("user.dir")+"/avatars/" + principal.getName()+".jpg");

                avatar.renameTo(renamed);
                ImageIO.write(resized,"jpg",renamed);
                userService.changeUserAvatar(principal.getName(),true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n" + (System.currentTimeMillis() - x));
        return "redirect:/";
    }

}
