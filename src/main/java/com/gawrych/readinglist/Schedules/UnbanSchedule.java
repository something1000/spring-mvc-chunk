package com.gawrych.readinglist.Schedules;

import com.gawrych.readinglist.Model.User;
import com.gawrych.readinglist.Services.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class UnbanSchedule {
    private UserService userService;

    @Autowired
    public UnbanSchedule(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24) //codzienne sprawdzanie odbanowywania
    public void unbanUsers(){

        LocalDate currentDate = LocalDate.now();
        for(User user : userService.findByBanned()){
            if(user.getBan_date().isBefore(currentDate) || user.getBan_date().isEqual(currentDate)) {
                user.setBan_date(null);
                user.setBanned(false);
                userService.updateUser(user);
                System.out.println("\npassword" + user.getPassword());
                System.out.println("Unbanned " + user.getUsername());
            }
        }
    }
}
