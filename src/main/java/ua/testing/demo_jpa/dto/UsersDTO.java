package ua.testing.demo_jpa.dto;

import lombok.*;
import ua.testing.demo_jpa.entity.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private List<User> users;
}
