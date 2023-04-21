package com.lab.inventoryservice.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class InventoryResponseModel extends RepresentationModel<InventoryResponseModel> {

    String inventoryId; //public id
    String type;
}
