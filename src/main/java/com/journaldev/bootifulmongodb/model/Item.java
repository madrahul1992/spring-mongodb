package com.journaldev.bootifulmongodb.model;


// TODO: Add unique SKU for each item and then add all item details

public class Item {

    private String itemName;

    private int qty;

//    public Item(String itemName, int qty){
//        this.itemName = itemName;
//        this.qty = qty;
//    }
    public Item(){

    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", qty=" + qty +
                '}';
    }
}
