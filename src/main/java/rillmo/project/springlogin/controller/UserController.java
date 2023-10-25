package rillmo.project.springlogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.service.UserService;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> saveUser() throws ExecutionException, InterruptedException {
        User user = User.builder()
                .id("1")
                .name("user1")
                .build();
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
