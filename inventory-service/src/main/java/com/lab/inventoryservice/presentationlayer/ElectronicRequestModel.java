package com.lab.inventoryservice.presentationlayer;

import com.lab.inventoryservice.datalayer.Option;
import com.lab.inventoryservice.datalayer.Status;
import com.lab.inventoryservice.datalayer.UsageType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElectronicRequestModel {

    String ein;
    String inventoryId;
    Status status;
    UsageType usageType;
    String manufacturer;
    String model;
    List<Option> options;
    Double msrp;
    Double cost;

}
