package com.lab.inventoryservice.datalayer;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class InventoryIdentifier {

    private String inventoryId;

    public InventoryIdentifier() {
        this.inventoryId = UUID.randomUUID().toString();
    }

    public String getInventoryId() {
        return inventoryId;
    }
}

