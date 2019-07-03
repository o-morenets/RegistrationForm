package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserLoginDTO;
import ua.testing.demo_jpa.dto.UserSignupDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.RoleType;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.EmailNotExistsException;
import ua.testing.demo_jpa.exception.IncorrectPasswordException;
import ua.testing.demo_jpa.exception.NotUniqueLoginException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class UserService {

    private static final int SQL_CONSTRAINT_NOT_UNIQUE = 1062;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        return new UsersDTO(userRepository.findAll());
    }

    private User findByEmail(UserLoginDTO userLoginDTO) {
        return userRepository.findByEmail(userLoginDTO.getEmail());
    }

    public Optional<User> login(UserLoginDTO userLoginDTO) {
        User user = findByEmail(userLoginDTO);
        if (user == null) {
            log.warn("No such email in database");
            throw new EmailNotExistsException("No such email in database", userLoginDTO);
        } else {
            // TODO check password
            if (new Random().nextBoolean()) { // FIXME --
                log.warn("Incorrect password");
                throw new IncorrectPasswordException("Incorrect password", userLoginDTO);
            }
        }
        return Optional.of(user);
    }

    public void saveNewUser(UserSignupDTO userSignupDTO) {
        User user = User.builder()
                .firstName(userSignupDTO.getFirstName())
                .lastName(userSignupDTO.getLastName())
                .email(userSignupDTO.getEmail())
                .role(RoleType.ROLE_USER)
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
