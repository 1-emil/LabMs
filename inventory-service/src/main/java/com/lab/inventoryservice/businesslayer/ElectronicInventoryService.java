package com.lab.inventoryservice.businesslayer;

import com.lab.inventoryservice.presentationlayer.ElectronicResponseModel;
import com.lab.inventoryservice.presentationlayer.InventoryResponseModel;
import com.lab.inventoryservice.presentationlayer.*;
import java.util.List;
import java.util.Map;

public interface ElectronicInventoryService {

    List<InventoryResponseModel> getInventories();
    List<ElectronicResponseModel> getElectronicsInInventoryByField(
            String inventoryId, Map<String,String> queryParams);

    ElectronicInventoryResponseModel getInventoryDetails(String inventoryId);
    InventoryResponseModel  addInventory(InventoryRequestModel inventoryRequestModel);
    ElectronicResponseModel addElectronicToInventory(ElectronicRequestModel electronicRequestModel, String inventoryId);
    InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId);
    ElectronicResponseModel updateElectronicInInventory(ElectronicRequestModel electronicRequestModel,String inventoryId,String electronicId);
    void removeElectronicFromInventory(String inventoryId, String electronicId);
    void removeInventoryAndContents(String inventoryId);
    ElectronicResponseModel getElectronicInInventoryByElectronicId(String inventoryId, String electronicId);

}
