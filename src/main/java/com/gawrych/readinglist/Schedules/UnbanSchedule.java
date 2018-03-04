package com.gawrych.readinglist.Schedules;

import com.gawrych.readinglist.Model.User;
import com.gawrych.readinglist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UnbanSchedule {
    private UserService userService;

    @Autowired
    public UnbanSchedule(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 20000)
    public void unbanUsers(){
        for(User user : userService.findByBanned()){
            userService.unbanUser(user.getUsername());
            System.out.println("\npassword" + user.getPassword());
            System.out.println("Unbanned " + user.getUsername());
        }
    }
}
