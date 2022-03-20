package com.inventory.logic;

public class ItemInventory extends DTO{
     public static final String DAO_REF_NAME = "iteminventory";
     public static final int FIELD_LEN = 7;
     private Item item;
     private int inventoryId;
     private int available;
     private int inStock;
     private int onOrder;

     public static ItemInventory createForSQLOperation(int itemId, int inventoryId, int available, int inStock, int onOrder) {
         return new ItemInventory(new Item(itemId, null, null),
                                inventoryId, available, inStock, onOrder);
     }

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

    public String getItemCategory() { return item.getCategory(); }

    public String getItemDescription() { return item.getDescription(); }

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

    @Override
    public Object[] intoObjectArray() {
        Object[] newArr = new Object[FIELD_LEN];
        int currentIndex = 0;
        newArr[currentIndex++] = getId();
        newArr[currentIndex++] = getInventoryId();
        newArr[currentIndex++] = getItemCategory();
        newArr[currentIndex++] = getItemDescription();
        newArr[currentIndex++] = getAvailable();
        newArr[currentIndex++] = getInStock();
        newArr[currentIndex] = getOnOrder();

        return newArr;
    }

    @Override
    public boolean followsBusinessRule(Object[] objects) {
         if (objects.length != FIELD_LEN) {
             return false;
         }
        int available = Integer.parseInt(objects[4].toString());
        int inStock = Integer.parseInt(objects[5].toString());
        int onOrder = Integer.parseInt(objects[6].toString());

        if (available >= 0 && inStock >= 0 && onOrder >= 0)
        {
            return inStock >= available && available >= onOrder;
        }

        return false;
    }
}
