package com.lab.clientservice.client.businesslayer;

import com.lab.clientservice.client.clientmapper.ClientRepository;
import com.lab.clientservice.client.clientmapper.ClientResponseMapper;
import com.lab.clientservice.client.datalayer.Client;
import com.lab.clientservice.client.presentationlayer.ClientResponseModel;
import com.lab.clientservice.client.utils.exceptions.DuplicateClientEmailException;
import com.lab.clientservice.client.utils.exceptions.InvalidInputException;
import com.lab.clientservice.client.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;
    private ClientResponseMapper clientResponseMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
    }

    @Override
    public List<ClientResponseModel> getClients() {
        return clientResponseMapper.entityListToResponseModelList(clientRepository.findAll());
    }

    @Override
    public Client getClientByClientId(String clientId) {
        Client client = clientRepository.findByClientIdentifier_ClientId(clientId);
        if (client == null){
            throw new NotFoundException("Client not found");
        }
        return clientRepository.findByClientIdentifier_ClientId(clientId);
    }

    @Override
    public Client addClient(Client newClient) {
        try {
            return clientRepository.save(newClient);
        }
        catch (Exception ex){
            if(ex.getMessage().contains("constraint [email_address]") || ex.getCause().toString().contains("must be unique")){
                throw new DuplicateClientEmailException("Client with email address " + newClient.getEmailAddress() + " already exists");

            }
            throw new InvalidInputException("An unknown error has occured");
        }
    }

    @Override
    public Client updateClient(Client client, String clientId) {
        //find if client exists
        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (existingClient == null){
            return null; //later on throw an exception
        }

        client.setId(existingClient.getId());
        client.setClientIdentifier(existingClient.getClientIdentifier());

        return clientRepository.save(client);
    }

    @Override
    public void removeClient(String clientId) {
        //find if client exists
        Client existingClient = clientRepository.findByClientIdentifier_ClientId(clientId);

        if (existingClient == null){
            return;
        }
        clientRepository.delete(existingClient);
    }
}
