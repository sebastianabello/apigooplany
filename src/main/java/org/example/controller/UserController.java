package org.example.controller;


import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.service.UserService;
import org.example.utils.QRCodeGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() throws IOException, WriterException {
        List<User> users = userService.getUsers();
        if (users.size() != 0 ) {
            for (User user : users) {
                QRCodeGenerator.generateQRCode(user);
            }
        }
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/addusers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.addUsers(users);
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
