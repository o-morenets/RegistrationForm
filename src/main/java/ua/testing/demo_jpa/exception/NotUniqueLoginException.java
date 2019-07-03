package ua.testing.demo_jpa.exception;

import lombok.Getter;
import ua.testing.demo_jpa.dto.UserSignupDTO;

public class NotUniqueLoginException extends RuntimeException {

    @Getter
    private UserSignupDTO userSignupDTO;

    public NotUniqueLoginException(String message, UserSignupDTO userSignupDTO) {
        super(message);
        this.userSignupDTO = userSignupDTO;
    }
}
