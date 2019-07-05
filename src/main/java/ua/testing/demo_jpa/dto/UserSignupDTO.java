package ua.testing.demo_jpa.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
