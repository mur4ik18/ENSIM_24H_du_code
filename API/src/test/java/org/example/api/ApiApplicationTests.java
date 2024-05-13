package org.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTest {

    private static final int PORT = 8080; // Définissez le port ici


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
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
    void testRegisterEndpointWithoutPassWord() {
        String baseUrl = "http://localhost:" + PORT + "/api/v1/auth/register";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"username\":\"Alex13\",\"firstName\":\"Alex\",\"lastName\":\"KOTOV\",\"email\":\"sqjskjdlqjl@gmail.com\",\"password\":\"\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
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

        requestBody = "{\"nom\":\"Alex\",\"motDePasse\":\"pdjslkqsljdlqjsdljqs\"}";

        request = new HttpEntity<>(requestBody, headers);

        response = restTemplate.postForEntity(baseUrl, request, Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}




