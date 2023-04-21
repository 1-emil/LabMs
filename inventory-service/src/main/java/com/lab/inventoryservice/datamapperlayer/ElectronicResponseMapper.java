package com.lab.inventoryservice.datamapperlayer;

import com.lab.inventoryservice.datalayer.Electronic;
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
public interface ElectronicResponseMapper {

    @Mapping(expression = "java(electronic.getElectronicIdentifier().getEin())", target = "electronicId")
    @Mapping(expression = "java(electronic.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    ElectronicResponseModel entityToResponseModel(Electronic electronic);

    List<ElectronicResponseModel> entityListToResponseModelList(List<Electronic> electronic);

    @AfterMapping
    default void addLinks(@MappingTarget ElectronicResponseModel model, Electronic electronic) {
        //self link
        Link selfLink = linkTo(methodOn(InventoryController.class)
                .getElectronicInInventory(model.getInventoryId(), model.getElectronicId()))
                .withSelfRel();
        model.add(selfLink);

        Link inventoryLink = linkTo(methodOn(InventoryController.class)
                .getInventoryDetails(model.getInventoryId()))
                .withRel("inventory");
        model.add(inventoryLink);
    }
}
