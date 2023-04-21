package com.lab.inventoryservice.presentationlayer;

import com.lab.inventoryservice.businesslayer.ElectronicInventoryService;
import com.lab.inventoryservice.utils.exceptions.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
@RestController
@Slf4j
@RequestMapping("api/v1/inventory")
public class InventoryController {

    @Autowired
    private ElectronicInventoryService electronicInventoryService;

    @GetMapping()
    public List<InventoryResponseModel> getInventories() {
        return electronicInventoryService.getInventories();
    }

    @GetMapping("/{inventoryId}/electronics")
    public List<ElectronicResponseModel> getElectronics(@PathVariable String inventoryId,
                                                        @RequestParam Map<String, String> queryParams) {
        return electronicInventoryService.getElectronicsInInventoryByField(inventoryId, queryParams);
    }

    @GetMapping("/{inventoryId}")
    public ElectronicInventoryResponseModel getInventoryDetails(@PathVariable String inventoryId) {
        log.debug("4 recieved inventoryId in inventorycontroller: {}" + inventoryId);
        if (electronicInventoryService.getInventories() == null){
            throw new InvalidInputException("Invalid Inventory Id Provided: " + inventoryId);
        }
        return electronicInventoryService.getInventoryDetails(inventoryId);
    }

    @PostMapping()
    InventoryResponseModel addInventory(@RequestBody InventoryRequestModel inventoryRequestModel) {

        return electronicInventoryService.addInventory(inventoryRequestModel);
    }

    @PostMapping("/{inventoryId}/electronics")
    ElectronicResponseModel addElectronicToInventory(@RequestBody ElectronicRequestModel electronicRequestModel,
                                                     @PathVariable String inventoryId) {

        return electronicInventoryService.addElectronicToInventory(electronicRequestModel, inventoryId);
    }

    @PutMapping("/{inventoryId}")
    InventoryResponseModel updateInventory(@RequestBody InventoryRequestModel inventoryRequestModel,
                                           @PathVariable String inventoryId) {
        return electronicInventoryService.updateInventory(inventoryRequestModel, inventoryId);
    }

    @PutMapping("/{inventoryId}/electronics/{electronicId}")
    ElectronicResponseModel updateElectronicInInventory(@RequestBody ElectronicRequestModel electronicRequestModel,
                                                        @PathVariable String inventoryId,
                                                        @PathVariable String electronicId) {
        return electronicInventoryService.updateElectronicInInventory(electronicRequestModel, inventoryId, electronicId);
    }

    @DeleteMapping("/{inventoryId}/electronics/{electronicId}")
    void removeElectronicFromInventory(@PathVariable String inventoryId,
                                     @PathVariable String electronicId) {
        electronicInventoryService.removeElectronicFromInventory(inventoryId, electronicId);
    }

    @DeleteMapping("/{inventoryId}")
    void removeInventoryAndContents(@PathVariable String inventoryId) {
        electronicInventoryService.removeInventoryAndContents(inventoryId);
    }

    @GetMapping("/{inventoryId}/electronics/{electronicId}")
    public ElectronicResponseModel getElectronicInInventory(@PathVariable String inventoryId,
                                                            @PathVariable String electronicId) {
        return electronicInventoryService.getElectronicInInventoryByElectronicId(inventoryId, electronicId);
    }

}
