package com.inventory.logic;

public class ItemInventory {
     public static final String DAO_REF_NAME = "iteminventory";
     private Item item;
     private int inventoryId;
     private int available;
     private int inStock;
     private int onOrder;


    public ItemInventory(Item item) {
        this.item = item;
        available = inStock = onOrder = 0;
    }

    public ItemInventory(Item item, int inventoryId, int available, int inStock, int onOrder) {
        this.item = item;
        this.inventoryId = inventoryId;
        this.available = available;
        this.inStock = inStock;
        this.onOrder = onOrder;
    }

    public ItemInventory() { }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    public int getId() {
        return item.getId();
    }

    public int getAvailable() {
        return available;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getOnOrder() {
        return onOrder;
    }

    @Override
    public String toString() {
        return "ItemInventory{" +
                "item=" + item +
                ", available=" + available +
                ", inStock=" + inStock +
                ", onOrder=" + onOrder +
                '}';
    }
}
