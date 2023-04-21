package com.lab.inventoryservice.datamapperlayer;

import com.lab.inventoryservice.datalayer.Inventory;
import com.lab.inventoryservice.presentationlayer.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inventoryIdentifier", ignore = true)
    Inventory requestModelToEntity(InventoryRequestModel requestModel);
}
