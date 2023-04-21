package com.lab.inventoryservice.datalayer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "electronics")
@Data
public class Electronic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    @Embedded
    private ElectronicIdentifier electronicIdentifier;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    private String manufacturer;

    private String model;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "electronic_options", joinColumns = @JoinColumn(name = "electronic_id"))
    private List<Option> options;

    @Embedded
    private Price price;

    public Electronic() {

    }

    public Electronic(ElectronicIdentifier electronicIdentifier, Status status, UsageType usageType, List<Option> options, Price price) {
        this.electronicIdentifier = electronicIdentifier;
        this.status = status;
        this.usageType = usageType;
        this.options = options;
        this.price = price;
    }


}
