package com.lab.apigateway.presentationLayer;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class ElectronicResponseModel extends RepresentationModel<ElectronicResponseModel> {

    String electronicId;
    String inventoryId;
    Status status;
    UsageType usageType;
    String manufacturer;
    String model;
    List<Option> options;
    Price price;
}
