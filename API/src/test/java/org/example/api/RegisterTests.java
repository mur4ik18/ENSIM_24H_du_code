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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
public class RegisterTests {
    private static final int PORT = 8080; // Définissez le port ici

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpoint() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpointWithoutUsername() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpointwithoutfirstName() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"\",\"lastName\":\"KOTOV\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpointwithoutlastName() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"Alex\",\"lastName\":\"\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpointwithoutemail() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"\",\"password\":\"pdjslkqsljdlqjsdljqs\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testRegisterEndpointWithoutPassWord() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
