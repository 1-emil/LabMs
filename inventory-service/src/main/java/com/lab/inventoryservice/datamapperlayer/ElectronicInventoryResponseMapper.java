package com.lab.inventoryservice.datamapperlayer;

import com.lab.inventoryservice.presentationlayer.ElectronicInventoryResponseModel;
import com.lab.inventoryservice.datalayer.Inventory;
import com.lab.inventoryservice.presentationlayer.ElectronicResponseModel;
import com.lab.inventoryservice.presentationlayer.InventoryController;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ElectronicInventoryResponseMapper {

    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(electronics)", target = "availableElectronics")
    ElectronicInventoryResponseModel entitiesToResponseModel(Inventory inventory, List<ElectronicResponseModel> electronics);

    @AfterMapping
    default void addLinks(@MappingTarget ElectronicInventoryResponseModel model, Inventory inventory) {
        // self link
         Link selfLink = linkTo(methodOn(InventoryController.class)
                 .getInventoryDetails(model.getInventoryId()))
                 .withSelfRel();
         model.add(selfLink);
    }
}
