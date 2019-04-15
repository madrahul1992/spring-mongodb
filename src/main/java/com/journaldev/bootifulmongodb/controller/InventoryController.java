package com.journaldev.bootifulmongodb.controller;

import com.journaldev.bootifulmongodb.dal.InventoryDAL;
import com.journaldev.bootifulmongodb.dal.InventoryRepository;
import com.journaldev.bootifulmongodb.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "inventory")
public class InventoryController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final InventoryDAL inventoryDAL;

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository, InventoryDAL inventoryDAL){
        this.inventoryRepository = inventoryRepository;
        this.inventoryDAL = inventoryDAL;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Inventory addProduct(@Valid @RequestBody Inventory inventory) {
        LOG.info("Inside create Order.");
        return inventoryDAL.addProduct(inventory);
//        return order;
    }
}
