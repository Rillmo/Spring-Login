package rillmo.project.springlogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rillmo.project.springlogin.dto.user.CreateUserDTO;
import rillmo.project.springlogin.dto.user.UpdateUserDTO;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.service.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

    private final UserService userService;

    // signup for users
    @PostMapping("/users")
    public ResponseEntity<Object> signup(@RequestBody CreateUserDTO request) {
        User savedUser = userService.saveUser(request.toEntity());
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<Object> userList() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // search user info (for admin)
    @GetMapping("/users")
    public ResponseEntity<Object> searchById(@RequestParam("id") String id) {
        User findUser = userService.findUserById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    // update user info (for admin)
    @PutMapping("/users")
    public ResponseEntity<Object> updateUserInfoForAdmin(@RequestParam("id") String id,
                                                         @RequestBody UpdateUserDTO request) {
        User updatedUser = userService.updateUser(id, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // delete user (for admin)
    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteUserForAdmin(@RequestParam("id") String id) {
        User deletedUser = userService.deleteUser(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
