package com.bitssmart.smartRestaurant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    @GetMapping("/login")
    public String newLogin(Model model){
        return "login";
    }
}
