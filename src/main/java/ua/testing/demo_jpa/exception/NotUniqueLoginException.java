package ua.testing.demo_jpa.exception;

import lombok.Getter;
import ua.testing.demo_jpa.entity.User;

public class NotUniqueLoginException extends RuntimeException {

    @Getter
    private User user;

    public NotUniqueLoginException(String message, User user) {
        super(message);
        this.user = user;
    }
}
