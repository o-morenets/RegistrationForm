package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.dto.UserSignupDTO;
import ua.testing.demo_jpa.entity.RoleType;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RegFormController {

    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/reg_form", method = RequestMethod.POST)
    public void registrationFormController(UserSignupDTO userSignupDTO) {
        User user = User.builder()
                .firstName(userSignupDTO.getFirstName())
                .lastName(userSignupDTO.getLastName())
                .email(userSignupDTO.getEmail())
                .role(RoleType.ROLE_USER)
                .build();
        try {
            userService.saveNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Email already exists!");
        }
        log.info("{}", userSignupDTO);
        log.info("{}", user);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
