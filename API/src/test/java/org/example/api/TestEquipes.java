package org.example.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEquipes {
    private static final int PORT = 8080;
    private final RestTemplate restTemplate;

    public TestEquipes() {
        this.restTemplate = new RestTemplateBuilder().build();
    }


    @Test
    void testcreateEquipe() {
        String token = login("sqjskjdlqjl@gmail.com", "pdjslkqsljdlqjsdljqs");

        String baseUrl = "http://localhost:"+PORT+"/api/v1/equipe";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        String requestBody = "{\"nom\":\"TestEquipe\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
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

    @Test
    void testJoinEquipe() {
        String token = login("emily.chang@example.com", "TechPass#2024");

        String baseUrl = "http://localhost:"+PORT+"/api/v1/equipe/join";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        String requestBody = "{\"nom\":\"TestEquipe\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testLeaveEquipe() {
        String token = login("sqjskjdlqjl@gmail.com", "pdjslkqsljdlqjsdljqs");

        String baseUrl = "http://localhost:"+PORT+"/api/v1/equipe/leave";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);

        String requestBody = "{}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    String login(String username, String password) {
        String baseUrl = "http://localhost:8080/api/v1/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");


        String requestBody = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", username, password);


        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        System.out.println(response);
        // Retrieving the token from response headers
        return response.getBody().split(":")[1].replace("\"", "").replace("}", "");
    }
}
