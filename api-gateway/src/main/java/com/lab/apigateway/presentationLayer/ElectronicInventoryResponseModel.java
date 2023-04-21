package com.lab.apigateway.presentationLayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class ElectronicInventoryResponseModel extends RepresentationModel<ElectronicInventoryResponseModel> {

    String inventoryId;
    String type;
    List<ElectronicResponseModel> availableElectronics;
}
