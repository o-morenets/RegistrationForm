package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if (user == null) {
            throw new RuntimeException("Wrong login or password");
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public UsersDTO getAllUsers() {
        UsersDTO allUsers = userService.getAllUsers();
        log.info("{}", allUsers);
        return allUsers;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex);
    }
}
