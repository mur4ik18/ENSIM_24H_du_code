package org.example.api;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
public class LoginTests {
    private static final int PORT = 8080; // Définissez le port ici

    @Autowired
    private TestRestTemplate restTemplate;
}
