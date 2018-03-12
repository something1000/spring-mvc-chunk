package com.gawrych.readinglist.Controllers;

import com.gawrych.readinglist.Services.ChunkService;
import com.gawrych.readinglist.Services.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
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
    public String getUserInfo(){
        return "settingView";
    }

    @PostMapping(value = "/setting")
    public String updateProfile(@RequestParam("avatar") MultipartFile file, Principal principal){
        long x = System.currentTimeMillis();
        File avatar= new File(System.getProperty("user.dir")+"/avatars/" + file.getOriginalFilename());
        try {
            file.transferTo(avatar);
            System.out.println(avatar.getAbsolutePath());
            System.out.println(Files.probeContentType(avatar.toPath()));
            if(Files.probeContentType(avatar.toPath()).compareTo("image/jpeg") == 0 || Files.probeContentType(avatar.toPath()).compareTo("image/png") == 0){
                System.out.println("jestem");
                BufferedImage img = ImageIO.read(avatar);//.getScaledInstance(100,100,Image.SCALE_FAST);
                BufferedImage resized = new BufferedImage(100,100,img.getType());
                Graphics2D g = resized.createGraphics();
                g.drawImage(img,0,0,100,100,null);
                g.dispose();

                ImageIO.write(resized,"jpg",avatar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n"+ (System.currentTimeMillis() - x));
        return "redirect:/";
    }

}
