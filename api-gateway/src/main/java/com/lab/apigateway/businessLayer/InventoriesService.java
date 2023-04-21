package com.lab.apigateway.businessLayer;

import com.lab.apigateway.presentationLayer.ElectronicInventoryResponseModel;

public interface InventoriesService {

    ElectronicInventoryResponseModel getInventoryAggregate(String inventoryId);

}
