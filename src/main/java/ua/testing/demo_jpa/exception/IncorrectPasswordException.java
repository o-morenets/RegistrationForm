package ua.testing.demo_jpa.exception;

import lombok.Getter;
import ua.testing.demo_jpa.dto.UserLoginDTO;

public class IncorrectPasswordException extends RuntimeException {

    @Getter
    private UserLoginDTO userLoginDTO;

    public IncorrectPasswordException(String message, UserLoginDTO userLoginDTO) {
        super(message);
        this.userLoginDTO = userLoginDTO;
    }
}
