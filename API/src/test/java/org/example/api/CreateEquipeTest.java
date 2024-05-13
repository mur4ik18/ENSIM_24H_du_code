package org.example.api;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
public class CreateEquipeTest {
    private static final int PORT = 8080; // Définissez le port ici

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testcreateEquipe() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void testcreateEquipe_CONFLIT() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
