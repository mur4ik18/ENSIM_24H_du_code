package org.example.api.api.auth;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public static class AuthenticationController {
        private final AuthenticationService authenticationService;

        @PostMapping("/register")
        public ResponseEntity register(@RequestBody RegisterRequest request) {
            if (request.getEmail().isEmpty() || request.getPassword().isEmpty() || request.getFirstName().isEmpty() || request.getLastName().isEmpty() || request.getUsername().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // I need to add here message that email already exists
            if (authenticationService.EmailExists(request.getEmail())) {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
            return ResponseEntity.ok(authenticationService.register(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
            return ResponseEntity.ok(authenticationService.login(request));
        }
    }
}
