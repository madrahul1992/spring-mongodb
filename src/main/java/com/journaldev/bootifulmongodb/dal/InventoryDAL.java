package com.journaldev.bootifulmongodb.dal;

import com.journaldev.bootifulmongodb.model.Inventory;

public interface InventoryDAL {

    Inventory addProduct(Inventory inventory);

    Inventory updateInventory(String itemName, int qty);

}
