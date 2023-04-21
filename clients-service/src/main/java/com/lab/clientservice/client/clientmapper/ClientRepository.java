package com.lab.clientservice.client.clientmapper;


import com.lab.clientservice.client.datalayer.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


        Client findByClientIdentifier_ClientId(String clientId);



}
