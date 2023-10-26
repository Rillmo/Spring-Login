package rillmo.project.springlogin;

import org.aspectj.lang.annotation.RequiredTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.UserRepository;
import rillmo.project.springlogin.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void saveUser() {
        User user = User.builder()
                .id("1")
                .name("user1")
                .build();
        User savedUser = userRepository.save(user);
        Assertions.assertThat(user.getId()).isEqualTo(savedUser.getId());
    }
}