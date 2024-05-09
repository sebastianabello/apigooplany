package org.example.services.gateway;


import lombok.RequiredArgsConstructor;
import org.example.services.notification.dto.UserDTO;
import org.example.services.notification.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

    // Find all users
    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    // Find user by id
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.updateUser(id,userDTO), HttpStatus.ACCEPTED);
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.NO_CONTENT);
    }





    /**
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers() throws IOException, WriterException {
        List<UserEntity> userEntities = userService.getUsers();
        if (userEntities.size() != 0 ) {
            for (UserEntity userEntity : userEntities) {
                QRCodeGenerator.generateQRCode(userEntity);
            }
        }
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/adduser")
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return userService.addUser(userEntity);
    }

    @PostMapping("/addusers")
    public List<UserEntity> addUsers(@RequestBody List<UserEntity> userEntities) {
        return userService.addUsers(userEntities);
    }

    @GetMapping("/users/{id}")
    public UserEntity findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
    */
}
