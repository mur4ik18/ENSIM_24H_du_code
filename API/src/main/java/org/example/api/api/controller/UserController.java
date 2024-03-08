package org.example.api.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.api.model.User;
import org.example.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
