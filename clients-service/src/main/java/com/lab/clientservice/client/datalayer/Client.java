package com.lab.clientservice.client.datalayer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // private id
    @Embedded
    private ClientIdentifier clientIdentifier; // public id

    private String firstName;
    private String lastName;
    private String emailAddress;

    @Embedded
    private Address address;

    public Client() {
        this.clientIdentifier = new ClientIdentifier();
    }

    public Client(String firstName, String lastName, String emailAddress, Address address) {
        this.clientIdentifier = new ClientIdentifier();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    //public Client(String validClientFirstName, String validClientLastName, String validClientEmail, String validClientStreet, String validClientCity, String validClientProvince, String validClientPostalCode, String validClientCountry) {

}
