package com.lab.clientservice.client.businesslayer;



import com.lab.clientservice.client.datalayer.Client;
import com.lab.clientservice.client.presentationlayer.ClientResponseModel;

import java.util.List;

public interface ClientService {

    List<ClientResponseModel> getClients();
    Client getClientByClientId(String clientId);
    Client addClient(Client newClient);
    Client updateClient(Client client, String clientId);

    void removeClient(String clientId);
}