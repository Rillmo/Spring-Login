package rillmo.project.springlogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rillmo.project.springlogin.dto.UpdateUserDTO;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Long id, UpdateUserDTO request) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        User updateUser = findUser.update(request.getName(), request.getEmail(), request.getPassword());
        return updateUser;
    }

    public User deleteUser(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        userRepository.delete(findUser);
        return findUser;
    }
}
