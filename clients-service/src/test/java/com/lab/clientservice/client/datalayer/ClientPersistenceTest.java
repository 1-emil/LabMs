package com.lab.clientservice.client.datalayer;

import com.lab.clientservice.client.clientmapper.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientPersistenceTest {

    private Client preSavedClient;
    private Address preSavedAddress;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void saveNewClient_shouldSucceed() {
        // Arrange
        String expectedName = "John";
        String expectedSurname = "Doe";
        String expectedEmail = "asdf@gmail.com";

        String expectedStreet = "123 main street";
        String expectedCity = "Montreal";
        String expectedProvince = "Quebec";
        String expectedCountry = "Canada";
        String expectedPostalCode = "H3H 3H3";

        Client newClient = new Client();
        newClient.setFirstName(expectedName);
        newClient.setLastName(expectedSurname);
        newClient.setEmailAddress(expectedEmail);

        newClient.setAddress(new Address(expectedStreet, expectedCity, expectedProvince, expectedCountry, expectedPostalCode));

        // Act
        Client savedClient = clientRepository.save(newClient);

        // Assert
        assertNotNull(savedClient);
        assertNotNull(savedClient.getId());
        assertNotNull(savedClient.getClientIdentifier().getClientId());
        assertNotNull(savedClient.getFirstName());
        assertNotNull(savedClient.getLastName());
        assertNotNull(savedClient.getEmailAddress());
        assertNotNull(savedClient.getAddress().getStreetAddress());
        assertNotNull(savedClient.getAddress().getCity());
        assertNotNull(savedClient.getAddress().getProvince());
        assertNotNull(savedClient.getAddress().getCountry());
        assertNotNull(savedClient.getAddress().getPostalCode());

        assertEquals(expectedName, savedClient.getFirstName());
        assertEquals(expectedSurname, savedClient.getLastName());
        assertEquals(expectedEmail, savedClient.getEmailAddress());
        assertEquals(expectedStreet, savedClient.getAddress().getStreetAddress());
        assertEquals(expectedCity, savedClient.getAddress().getCity());
        assertEquals(expectedProvince, savedClient.getAddress().getProvince());
        assertEquals(expectedCountry, savedClient.getAddress().getCountry());
        assertEquals(expectedPostalCode, savedClient.getAddress().getPostalCode());
    }


}