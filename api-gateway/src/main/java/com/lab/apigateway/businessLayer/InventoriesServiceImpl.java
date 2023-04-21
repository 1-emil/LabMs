package com.lab.apigateway.businessLayer;

import com.lab.apigateway.domainLayer.InventoryServiceClient;
import com.lab.apigateway.presentationLayer.ElectronicInventoryResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoriesServiceImpl implements InventoriesService{

    private InventoryServiceClient inventoryServiceClient;

    public InventoriesServiceImpl(InventoryServiceClient inventoryServiceClient){
        this.inventoryServiceClient = inventoryServiceClient;
    }



        @Override
        public ElectronicInventoryResponseModel getInventoryAggregate(String inventoryId) {
        log.debug("Getting inventory aggregate for inventoryId: {}", inventoryId);
            return inventoryServiceClient.getInventoryAggregate(inventoryId);
        }

}
