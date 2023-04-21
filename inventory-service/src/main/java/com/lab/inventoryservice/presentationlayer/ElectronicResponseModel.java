package com.lab.inventoryservice.presentationlayer;

import com.lab.inventoryservice.datalayer.Option;
import com.lab.inventoryservice.datalayer.Price;
import com.lab.inventoryservice.datalayer.Status;
import com.lab.inventoryservice.datalayer.UsageType;
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
