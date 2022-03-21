package com.inventory.domain;

/**
 * The Item Data Transfer Object for the "ItemInventory" Table in the database
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class ItemInventory extends DTO{
    /**
     * The referred name of this class by {@link com.inventory.persistence.Dao}
     */
     public static final String DAO_REF_NAME = "iteminventory";

     /**
     * Number of fields
     */
     public static final int FIELD_LEN = 7;
     private Item item;
     private int inventoryId;
     private int available;
     private int inStock;
     private int onOrder;


    /**
     * Creates a ItemInventory instance
     * @param itemId The id of item {@link Item} that refers to this Inventory
     * @param category The category of iiem {@link Item} that refers to this Inventory
     * @param description The description of iiem {@link Item} that refers to this Inventory
     * @param inventoryId The id of this inventory
     * @param available The amount of available stock
     * @param inStock The amount of stock
     * @param onOrder The amount of on order
     * @return ItemInventory The newly created instance
     */
     public static ItemInventory create(int itemId, String category, String description, int inventoryId, int available,
                                        int inStock, int onOrder)
     {
         Item item = Item.create(itemId, category, description);
         return new ItemInventory(item, inventoryId, available, inStock, onOrder);
     }


    /**
     * Creates a fresh ItemInventory instance
     * @param itemId The id of item {@link Item} that refers to this Inventory
     * @return ItemInventory The newly created instance
     */
    public static ItemInventory createNewForDB(int itemId)
    {
        Item item = Item.create(itemId);
        return new ItemInventory(item);
    }

    /**
     * Transform the instance into an array of Objects
     * @return Object[] The Object array containing all the instance fields
     */
     public static ItemInventory fromObjectArray(Object[] values) {

         try {
             if (values.length != FIELD_LEN) return null;

             // Grabbing field data from object array
             int itemId = Integer.parseInt(values[0].toString());
             int inventoryId = Integer.parseInt(values[1].toString());
             String category = values[2].toString();
             String descriptipn = values[3].toString();
             int available = Integer.parseInt(values[4].toString());
             int inStock = Integer.parseInt(values[5].toString());
             int onOrder = Integer.parseInt(values[6].toString());

             Item item = Item.create(itemId, category, descriptipn);
             return new ItemInventory(item, inventoryId, available, inStock, onOrder);

         } catch (Exception e) {
             System.err.println("Errors when converting into " + ItemInventory.class);
             e.printStackTrace();
             return null;
         }
     }

    private ItemInventory(Item item, int inventoryId, int available, int inStock, int onOrder) {
        this.item = item;
        this.inventoryId = inventoryId;
        this.available = available;
        this.inStock = inStock;
        this.onOrder = onOrder;
    }

    private ItemInventory(Item item) {
         this.item = item;
         this.available = this.inStock = this.onOrder = 0;
    }

    /**
     *
     * @return String The category of the instance
     */
    public String getItemCategory() { return item.getCategory(); }

    /**
     *
     * @return String The category of the instance
     */
    public String getItemDescription() { return item.getDescription(); }

    /**
     *
     * @return String The description of the instance
     */
    public int getId() {
        return item.getId();
    }

    /**
     *
     * @return int The available of the instance
     */
    public int getAvailable() {
        return available;
    }

    /**
     *
     * @return int The stock of the instance
     */
    public int getInStock() {
        return inStock;
    }

    /**
     *
     * @return int The inventory id of the instance
     */
    public int getInventoryId() {
        return inventoryId;
    }

    /**
     *
     * @return int The amount of on order
     */
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

    /**
     * Transform the instance into an array of Objects
     * @return Object[] The Object array containing all the instance fields
     */
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

    /**
     * Determines whether this instance follows the business rule
     * @return boolean The validation result
     */
    @Override
    public boolean followsBusinessRule() {
        if (available >= 0 && inStock >= 0 && onOrder >= 0)
        {
            return inStock >= available;
        }
        return false;
    }
}
