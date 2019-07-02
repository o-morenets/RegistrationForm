package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserLoginDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.repository.UserRepository;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepository.findAll());
    }

    public User findByEmail(UserLoginDTO userLoginDTO) {
        //TODO check for user availability. password check
        return userRepository.findByEmail(userLoginDTO.getEmail());
    }

    public void saveNewUser(User user) {
        //TODO inform the user about the replay email
        userRepository.save(user);
    }
}
