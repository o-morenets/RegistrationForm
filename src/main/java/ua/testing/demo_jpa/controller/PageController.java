package ua.testing.demo_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String mainPage(){
        return "login";
    }

    @RequestMapping("/signup")
    public String regForm(){
        return "signup";
    }

    @RequestMapping("/all_users")
    public String userPage(){
        return "users/users";
    }
}
