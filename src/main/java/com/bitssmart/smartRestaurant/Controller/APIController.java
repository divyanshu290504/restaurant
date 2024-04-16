package com.bitssmart.smartRestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitssmart.smartRestaurant.Model.Login;
import com.bitssmart.smartRestaurant.Model.Respose;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Service.UserService;



@RestController
public class APIController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public Respose LoginValid(@RequestBody Login login){
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
        String userEmail = login.getUsername();
        User user = userService.findUserByEmail(userEmail);
        if(user != null){
            if(login.getPassword().equals(user.getPassword()))
                return new Respose(200,"Correct");
            else
                return new Respose(200,"Notcorrect");
        }
        else
            return new Respose(200,"Notcorrect");
    }
}