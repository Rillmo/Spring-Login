package rillmo.project.springlogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
