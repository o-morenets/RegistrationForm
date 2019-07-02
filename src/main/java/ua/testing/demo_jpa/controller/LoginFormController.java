package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.testing.demo_jpa.dto.UserLoginDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginFormController(UserLoginDTO userLoginDTO) {
        User user = userService.findByEmail(userLoginDTO);
        log.info("{}", userLoginDTO);
        log.info("{}", user);

        /*       userService.saveNewUser(User.builder()
                .firstName("Ann")
                .lastName("Reizer")
                .email("AnnReizer@testing.ua")
                .role(RoleType.ROLE_USER)
                .build());
        */
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public UsersDTO getAllUsers() {
        UsersDTO allUsers = userService.getAllUsers();
        log.info("{}", allUsers);
        return allUsers;
    }
}
