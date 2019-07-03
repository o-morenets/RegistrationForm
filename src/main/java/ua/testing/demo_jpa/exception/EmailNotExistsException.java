package ua.testing.demo_jpa.exception;

import lombok.Getter;
import ua.testing.demo_jpa.dto.UserLoginDTO;

public class EmailNotExistsException extends RuntimeException {

    @Getter
    private UserLoginDTO userLoginDTO;

    public EmailNotExistsException(String message, UserLoginDTO userLoginDTO) {
        super(message);
        this.userLoginDTO = userLoginDTO;
    }
}
