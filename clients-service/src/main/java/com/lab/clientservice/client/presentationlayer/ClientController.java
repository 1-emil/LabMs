package com.lab.clientservice.client.presentationlayer;

import com.lab.clientservice.client.businesslayer.ClientService;
import com.lab.clientservice.client.datalayer.Client;
import com.lab.clientservice.client.utils.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientResponseModel>> getClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClients());
    }

    @GetMapping("/{clientId}")
    public Client getClientByClientId(@PathVariable String clientId){
        return clientService.getClientByClientId(clientId);
    }

    @PostMapping()
    ResponseEntity<Client> addClient(@RequestBody Client newClient){
        if (!newClient.getEmailAddress().contains("@") || !newClient.getEmailAddress().contains(".")){
            throw new InvalidInputException("Invalid client email address");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(newClient));
    }

    @PutMapping("/{clientId}")
    ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable String clientId){
        return ResponseEntity.status(HttpStatus.OK).body(
                clientService.updateClient(client,clientId));
    }

    @DeleteMapping("/{clientId}")
    ResponseEntity<Void> removeClient(@PathVariable String clientId){
        clientService.removeClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
