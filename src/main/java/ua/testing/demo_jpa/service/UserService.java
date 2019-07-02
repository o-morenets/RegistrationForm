package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserLoginDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.NotUniqueLoginException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.sql.SQLException;

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

    public User findByEmail(UserLoginDTO userLoginDTO) {
        return userRepository.findByEmail(userLoginDTO.getEmail());
    }

    public void saveNewUser(User user) {
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
                log.warn("Email already exists!");
                throw new NotUniqueLoginException("Email already exists!", user);
            }

            throw ex;
        }
    }
}
