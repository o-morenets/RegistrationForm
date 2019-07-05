package ua.testing.demo_jpa.service;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserLoginDTO;
import ua.testing.demo_jpa.dto.UserSignupDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.Role;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.NotUniqueLoginException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private static final int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        return new UsersDTO(userRepository.findAll());
    }

    public Optional<User> login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());
        return Optional.of(user);
    }

    public void saveNewUser(UserSignupDTO userSignupDTO) {
        User user = User.builder()
                .username(userSignupDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(userSignupDTO.getPassword()))
                .authorities(ImmutableList.of(Role.ROLE_USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .firstName(userSignupDTO.getFirstName())
                .lastName(userSignupDTO.getLastName())
                .build();

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            int errorCode = 0;

            Throwable specificException = NestedExceptionUtils.getMostSpecificCause(ex);

            if (specificException instanceof SQLException) {
                SQLException sqlException = (SQLException) specificException;
                errorCode = sqlException.getErrorCode();
            }

            if (errorCode == SQL_CONSTRAINT_NOT_UNIQUE) {
                log.warn("Email already exists");
                throw new NotUniqueLoginException("Email already exists", userSignupDTO);
            }

            throw ex;
        }
    }
}
