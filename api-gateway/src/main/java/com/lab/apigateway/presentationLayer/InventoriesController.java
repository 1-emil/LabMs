package com.lab.apigateway.presentationLayer;

import com.lab.apigateway.businessLayer.InventoriesService;
import com.lab.apigateway.utils.exceptions.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventories")
public class InventoriesController {

    private InventoriesService inventoriesService;
    private final Integer UUID_LENGTH = 36;

    public InventoriesController(InventoriesService inventoriesService) {
        this.inventoriesService = inventoriesService;
    }

    @GetMapping(
            value = "/{inventoryId}",
            produces = "application/json"
    )
    ResponseEntity<ElectronicInventoryResponseModel> getInventoryElectronicAggregate(@PathVariable String inventoryId) {
        if(inventoryId.length() != UUID_LENGTH) {
            throw new InvalidInputException("Invalid inventoryId: " + inventoryId);
        }
        log.debug("1 is recieved in api gateway from inventories controller" + inventoryId);
        return ResponseEntity.ok().body(inventoriesService.getInventoryAggregate(inventoryId));
    }


}
