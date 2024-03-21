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
    @GetMapping
    public ResponseEntity<String> getUser(String email){
        System.out.println();
        return ResponseEntity.ok(userService.findByEmail(email).toString());
    }
}
