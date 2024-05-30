package org.example.api;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class CreateEquipeTest {
    private static final int PORT = 8080; // Définissez le port ici

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    void beforeAll() {
        System.setProperty("spring.profiles.active", "test");
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        for (int i = 1; i <= 6; i++) {
            String username = "utilisateur" + i;
            String requestBody = String.format(
                    "{\"username\":\"%s\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"utilisateur%d@example.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}",
                    username, i);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

            System.out.println(response.getStatusCode() + " " + username);
        }
    }
    @Test
    void testcreateEquipe() {
        String token = login("utilisateur2");

        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        String requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void testcreateEquipeWithoutToken() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testcreateEquipeWithWrongToken() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + "wrongToken");

        String requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testGetEquipe() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/equipe/all";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    String login(String username) {
        String baseUrl = "http://localhost:8080/api/v1/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");


        String requestBody = String.format("{\"email\":\"%s@example.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}", username);


        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        System.out.println(response);
            // Retrieving the token from response headers
        return response.getBody().split(":")[1].replace("\"", "").replace("}", "");
    }
}
