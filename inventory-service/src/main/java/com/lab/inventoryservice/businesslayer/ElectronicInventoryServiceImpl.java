package com.lab.inventoryservice.businesslayer;

import com.lab.inventoryservice.datalayer.*;
import com.lab.inventoryservice.datamapperlayer.*;
import com.lab.inventoryservice.presentationlayer.*;
import com.lab.inventoryservice.utils.exceptions.DuplicateTypeException;
import com.lab.inventoryservice.utils.exceptions.InvalidInputException;
import com.lab.inventoryservice.utils.exceptions.NotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElectronicInventoryServiceImpl implements ElectronicInventoryService{
    private InventoryRepository inventoryRepository;
    private InventoryResponseMapper inventoryResponseMapper;
    private InventoryRequestMapper inventoryRequestMapper;
    private ElectronicRepository electronicRepository;
    private ElectronicResponseMapper electronicResponseMapper;
    private ElectronicRequestMapper electronicRequestMapper;
    private ElectronicInventoryResponseMapper electronicInventoryResponseMapper;

    public ElectronicInventoryServiceImpl(InventoryRepository inventoryRepository, InventoryResponseMapper inventoryResponseMapper, InventoryRequestMapper inventoryRequestMapper, ElectronicRepository electronicRepository, ElectronicResponseMapper electronicResponseMapper, ElectronicRequestMapper electronicRequestMapper, ElectronicInventoryResponseMapper electronicInventoryResponseMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.electronicRepository = electronicRepository;
        this.electronicResponseMapper = electronicResponseMapper;
        this.electronicRequestMapper = electronicRequestMapper;
        this.electronicInventoryResponseMapper = electronicInventoryResponseMapper;
    }

    @Override
    public List<InventoryResponseModel> getInventories() {
        return inventoryResponseMapper.entityListToResponseModelList(inventoryRepository.findAll());
    }

    @Override
    public List<ElectronicResponseModel> getElectronicsInInventoryByField(String inventoryId, Map<String, String> queryParams) {
        if (!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            return null;
        }

        String status = queryParams.get("status");
        String usageType = queryParams.get("usage");

        Map<String, Status> statusMap = new HashMap<String, Status>();
        statusMap.put("available", Status.AVAILABLE);
        statusMap.put("out_of_stock", Status.OUT_OF_STOCK);
        statusMap.put("sold", Status.SOLD);

        Map<String, UsageType> usageTypeMap = new HashMap<String, UsageType>();
        usageTypeMap.put("new", UsageType.NEW);
        usageTypeMap.put("used", UsageType.USED);

        if(status!=null && usageType!=null) {
            return electronicResponseMapper.entityListToResponseModelList(electronicRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEqualsAndUsageTypeEquals(inventoryId,
                    statusMap.get(status.toLowerCase()), usageTypeMap.get(usageType.toLowerCase())));
        }
        if(status!=null) {
            return electronicResponseMapper.entityListToResponseModelList(electronicRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEquals(inventoryId,
                    statusMap.get(status.toLowerCase())));
        }
        if(usageType!=null) {
            return electronicResponseMapper.entityListToResponseModelList(electronicRepository.findAllByInventoryIdentifier_InventoryIdAndUsageTypeEquals(inventoryId,
                    usageTypeMap.get(usageType.toLowerCase())));
        }
        return electronicResponseMapper.entityListToResponseModelList(electronicRepository.findAllByInventoryIdentifier_InventoryId(inventoryId));
            }

    @Override
    public ElectronicInventoryResponseModel getInventoryDetails(String inventoryId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if(inventory==null) {
            throw new NotFoundException("Inventory with id " + inventoryId + " not found");
        }

        List<ElectronicResponseModel> electronicResponseModels = electronicResponseMapper.entityListToResponseModelList(electronicRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEquals(inventoryId, Status.AVAILABLE));

        return electronicInventoryResponseMapper.entitiesToResponseModel(inventory, electronicResponseModels);
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {
        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
        Inventory saved = inventoryRepository.save(inventory);
        try {
            return inventoryResponseMapper.entityToResponseModel(saved);
        }
        catch (Exception ex){
            if(ex.getMessage().contains("constraint [type]") || ex.getCause().toString().contains("must be unique")){
                throw new DuplicateTypeException("Employee with email address " + inventoryRequestModel.getType() + " already exists");

            }
            throw new InvalidInputException("An unknown error has occured");
        }
    }


    @Override
    public ElectronicResponseModel addElectronicToInventory(ElectronicRequestModel electronicRequestModel, String inventoryId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if(inventory==null) {
             throw new NotFoundException("Inventory with id " + inventoryId + " not found");
        }

        ElectronicIdentifier electronicIdentifier = new ElectronicIdentifier(electronicRequestModel.getEin());

        double totalOptionsCost = electronicRequestModel.getOptions().stream()
                .mapToDouble(Option::getCost).sum();
        Price price = new Price(electronicRequestModel.getMsrp(), electronicRequestModel.getCost(), totalOptionsCost);

        Electronic electronic = electronicRequestMapper.requestModelToEntity(electronicRequestModel, electronicIdentifier,
                inventory.getInventoryIdentifier(), price);

            return electronicResponseMapper.entityToResponseModel(electronicRepository.save(electronic));
    }

    @Override
    public InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String inventoryId) {
        Inventory inventory=inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if(inventory==null){
            return null;
        }

        Inventory updateInventory=inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
        updateInventory.setInventoryIdentifier(inventory.getInventoryIdentifier());
        updateInventory.setId(inventory.getId());

        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(updateInventory));
    }

    @Override
    public ElectronicResponseModel updateElectronicInInventory(ElectronicRequestModel electronicRequestModel, String inventoryId, String electronicId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if(inventory==null) {
            return null;
        }
        Electronic electronic = electronicRepository.findByElectronicIdentifier_Ein(electronicId);
        if(electronic==null) {
            return null;
        }

        Electronic updateElectronic = electronicRequestMapper.requestModelToEntity(electronicRequestModel, electronic.getElectronicIdentifier(),
                electronic.getInventoryIdentifier(), electronic.getPrice());
        updateElectronic.setId(electronic.getId());

        return electronicResponseMapper.entityToResponseModel(electronicRepository.save(updateElectronic));
    }

    @Override
    public void removeElectronicFromInventory(String inventoryId, String electronicId) {
        if(!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            return;
        }

        Electronic electronic = electronicRepository.findByElectronicIdentifier_Ein(electronicId);

        if(electronic==null) {
            return;
        }

        electronicRepository.delete(electronic);
    }

    @Override
    public void removeInventoryAndContents(String inventoryId) {

        Inventory inventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if(inventory==null) {
            return;
        }

        List<Electronic> electronics = electronicRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);
        electronicRepository.deleteAll(electronics);
        inventoryRepository.delete(inventory);

    }

    @Override
    public ElectronicResponseModel getElectronicInInventoryByElectronicId(String inventoryId, String electronicId) {
        if(!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            return null;
        }

        Electronic foundElectronic = electronicRepository.findByInventoryIdentifier_InventoryIdAndElectronicIdentifier_Ein(inventoryId, electronicId);

        return electronicResponseMapper.entityToResponseModel(foundElectronic);
    }
}
