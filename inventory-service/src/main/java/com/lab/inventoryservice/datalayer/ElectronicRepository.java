package com.lab.inventoryservice.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {

    List<Electronic> findAllByInventoryIdentifier_InventoryId(String inventoryId);
    List<Electronic> findAllByInventoryIdentifier_InventoryIdAndStatusEquals(String inventoryId, Status status);
    List<Electronic> findAllByInventoryIdentifier_InventoryIdAndUsageTypeEquals(String inventoryId, UsageType usageType);
    List<Electronic> findAllByInventoryIdentifier_InventoryIdAndStatusEqualsAndUsageTypeEquals(String inventoryId, Status status, UsageType usageType);
    Electronic findByInventoryIdentifier_InventoryIdAndElectronicIdentifier_Ein(String inventoryId, String ein);
    Electronic findByElectronicIdentifier_Ein(String ein);
}
