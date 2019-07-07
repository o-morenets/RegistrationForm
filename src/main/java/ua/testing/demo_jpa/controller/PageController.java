package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Slf4j
@Controller
public class PageController {

    @RequestMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false) String error,
                            @RequestParam(name = "logout", required = false) String logout,
                            Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        return "logoutSuccessfulPage";
    }

    @RequestMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // User page
    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("userInfo", loginedUser);

        return "userInfoPage";
    }

    // Admin page only
    @RequestMapping("/allUsers")
    public String adminPage() {
        return "users";
    }

    @RequestMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
}
