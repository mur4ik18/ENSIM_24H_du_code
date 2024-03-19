package org.example.api.api.user;

import lombok.RequiredArgsConstructor;
import org.example.api.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    public ResponseEntity<String> getUser(Integer id){
        return ResponseEntity.ok(userService.findById(id).toString());
    }
}
