package com.lab.inventoryservice.datalayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ElectronicIdentifier {

    @Column(unique = true)
    private String ein;

    public ElectronicIdentifier() {

    }

    public ElectronicIdentifier(String ein) {
        this.ein = ein;
    }

    public String getEin() {
        return ein;
    }

}
