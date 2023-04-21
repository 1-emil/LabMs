package com.lab.inventoryservice.datamapperlayer;

import com.lab.inventoryservice.datalayer.Electronic;
import com.lab.inventoryservice.datalayer.ElectronicIdentifier;
import com.lab.inventoryservice.datalayer.InventoryIdentifier;
import com.lab.inventoryservice.datalayer.Price;
import com.lab.inventoryservice.presentationlayer.ElectronicRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ElectronicRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(electronicIdentifier)", target = "electronicIdentifier"),
            @Mapping(expression = "java(inventoryIdentifier)", target = "inventoryIdentifier"),
            @Mapping(expression = "java(price)", target = "price")
    })
    Electronic requestModelToEntity(ElectronicRequestModel electronicRequestModel, ElectronicIdentifier electronicIdentifier, InventoryIdentifier inventoryIdentifier, Price price);
}

