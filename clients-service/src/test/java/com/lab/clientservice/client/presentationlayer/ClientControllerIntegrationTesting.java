package com.lab.clientservice.client.presentationlayer;

import com.lab.clientservice.client.clientmapper.ClientRepository;
import com.lab.clientservice.client.datalayer.Address;
import com.lab.clientservice.client.datalayer.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql({"/data-mysql.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientControllerIntegrationTesting {

    private final String BASE_URI_CLIENTS = "/api/v1/clients";
    private final String VALID_CLIENT_ID = "c3540a89-cb47-4c96-888e-ff96708db4d8";
    private final String VALID_CLIENT_FIRST_NAME = "Alick";
    private final String VALID_CLIENT_LAST_NAME = "Ucceli";
    private final String VALID_CLIENT_EMAIL = "aucceli0@dot.gov";
    private final String VALID_CLIENT_STREET = "73 Shoshone Road";
    private final String VALID_CLIENT_CITY = "Barraute";
    private final String VALID_CLIENT_PROVINCE = "Québec";
    private final String VALID_CLIENT_POSTAL_CODE = "P0M 2T6";
    private final String VALID_CLIENT_COUNTRY = "Canada";


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ClientRepository clientRepository;

    @AfterEach
    public void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    public void whenClientsExists_thenReturnAllClients() {
        // Act
        Integer expectedNumClients = 10;

        // Assert
        webTestClient.get()
                .uri(BASE_URI_CLIENTS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedNumClients);
    }

    //Get by ID

    @Test
    public void getValidClientById() {

        webTestClient.get()
                .uri(BASE_URI_CLIENTS + "/" + VALID_CLIENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    //post
    @Test
    public void whenValidClient_thenCreateClient() {

        // Act
        Address newAddress = new Address(VALID_CLIENT_STREET, VALID_CLIENT_CITY, VALID_CLIENT_PROVINCE, VALID_CLIENT_POSTAL_CODE, VALID_CLIENT_COUNTRY);
        Client newClient = new Client(VALID_CLIENT_FIRST_NAME, VALID_CLIENT_LAST_NAME, VALID_CLIENT_EMAIL, newAddress);

        webTestClient.post()
                .uri(BASE_URI_CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newClient))
                .exchange()
                .expectStatus().isCreated();


    }

    //delete

    @Test
    public void whenValidClient_thenDeleteClient() {

        // Act
        webTestClient.delete()
                .uri(BASE_URI_CLIENTS + "/" + VALID_CLIENT_ID)
                .exchange()
                .expectStatus().isNoContent();
    }


    //put

    @Test
    public void whenValidClient_thenUpdateClient() {
        Address newAddress = new Address(VALID_CLIENT_STREET, VALID_CLIENT_CITY, VALID_CLIENT_PROVINCE, VALID_CLIENT_POSTAL_CODE, VALID_CLIENT_COUNTRY);
        Client newClient = new Client(VALID_CLIENT_FIRST_NAME, VALID_CLIENT_LAST_NAME, VALID_CLIENT_EMAIL, newAddress);


        // Act
        webTestClient.put()
                .uri(BASE_URI_CLIENTS + "/" + VALID_CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newClient))
                .exchange()
                .expectStatus().isOk();
    }
}