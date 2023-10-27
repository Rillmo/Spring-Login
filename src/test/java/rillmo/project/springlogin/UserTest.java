package rillmo.project.springlogin;

import jakarta.persistence.SecondaryTable;
import org.aspectj.lang.annotation.RequiredTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import rillmo.project.springlogin.dto.UpdateUserDTO;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.UserRepository;
import rillmo.project.springlogin.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    private UserService userService;

    private static User user1;
    private static User user2;

    @BeforeAll
    static void setUser() {
        user1 = User.builder()
                .name("user1")
                .email("email1")
                .password("pw1")
                .build();
        user2 = User.builder()
                .name("user2")
                .email("email2")
                .password("pw2")
                .build();

    }

    @Test
    void saveUser() {
        User savedUser = userService.saveUser(user1);
        assertThat(user1.getId()).isEqualTo(savedUser.getId());
    }

    @Test
    void findUserById() {
        // save user
        userService.saveUser(user1);

        // success case
        User findUser = userService.findUserById(user1.getId());
        assertThat(user1.getId()).isEqualTo(findUser.getId());

        // fail case
        assertThrows(RuntimeException.class, () -> userService.findUserById(0L));
    }

    @Test
    void findAll() {
        // save user
        userService.saveUser(user1);
        userService.saveUser(user2);

        List<User> users = userService.findAll();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0).getId()).isEqualTo(user1.getId());
        assertThat(users.get(1).getId()).isEqualTo(user2.getId());
    }

    @Test
    void updateById() {
        // save user
        userService.saveUser(user1);

        // make update request
        UpdateUserDTO request = new UpdateUserDTO("updatedName", "updatedEmail", "updatedPW");

        User updatedUser = userService.updateUser(user1.getId(), request);

        assertThat(updatedUser.getId()).isEqualTo(user1.getId());
        assertThat(updatedUser.getName()).isEqualTo(request.getName());
        assertThat(updatedUser.getEmail()).isEqualTo(request.getEmail());
        assertThat(updatedUser.getPassword()).isEqualTo(request.getPassword());
    }

    @Test
    void deleteByID() {
        // save user
        userService.saveUser(user1);

        User deletedUser = userService.deleteUser(user1.getId());

        assertThat(deletedUser.getId()).isEqualTo(user1.getId());

        // user1 must not find user1
        assertThrows(Exception.class, () -> userService.findUserById(user1.getId()));
    }
}