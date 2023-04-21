package com.lab.apigateway.domainLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.apigateway.presentationLayer.ElectronicInventoryResponseModel;
import com.lab.apigateway.utils.HttpErrorInfo;
import com.lab.apigateway.utils.exceptions.InvalidInputException;
import com.lab.apigateway.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String INVENTORY_SERVICE_BASE_URL;

    public InventoryServiceClient(RestTemplate restTemplate,
                                  ObjectMapper mapper,
                                  @Value("${app.inventory-service.host}") String inventoryServiceHost,
                                  @Value("${app.inventory-service.port}") String inventoryServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        INVENTORY_SERVICE_BASE_URL = "http://" + inventoryServiceHost + ":" + inventoryServicePort + "/api/v1/inventories";
    }

    public ElectronicInventoryResponseModel getInventoryAggregate(String inventoryId) {
        log.debug("3 recieved inventoryId in inventoryserviceclient: {}", inventoryId);
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId;
            ElectronicInventoryResponseModel electronicInventoryResponseModel = restTemplate
                    .getForObject(url, ElectronicInventoryResponseModel.class);
            log.debug("5 recieved electronicInventoryResponseModel in inventoryserviceclient: {}" + electronicInventoryResponseModel.getInventoryId());
            return electronicInventoryResponseModel;
        }
        catch(HttpClientErrorException ex){
            log.error("Error while getting inventory aggregate for inventoryId: {}", inventoryId);
            throw ex;
        }


    }
    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
//include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }

}
