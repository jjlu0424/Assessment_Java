package com.inventory.domain;

/**
 * The Item Data Transfer Object for the "Items" Table in the database
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class Item extends DTO {
    /**
     * The referred name of this class by {@link com.inventory.persistence.Dao}
     */
    public static final String DAO_REF_NAME = "items";

    /**
     * The index of itemId in an object array representation of the class instance
     */
    public static final int ID_IND = 0;

    /**
     * The index of category in an object array representation of the class instance
     */
    public static final int CATEGORY_IND = 2;

    /**
     * The index of description in an object array representation of the class instance
     */
    public static final int ITEM_DECRIIP_IND = 3;

    /**
     * Number of fields
     */
    public static final int FIELD_LEN = 3;

    private int id;
    private String category;
    private String description;

    /**
     * Creates a new instance
     * @param id The id of the item
     * @param category The item category
     * @param description The item description
     * @return Item the created instance
     */
    public static Item create(int id, String category, String description) {
        return new Item(id, category, description);
    }

    /**
     * Creates a new instance with empty category and description
     * @param id The id of the item
     * @return Item the created instance
     */
    public static Item create(int id) {
        return new Item(id, null, null);
    }

    /**
     * Creates a new instance from an array of Objects
     * @param values An array of object
     * @return Item the created instance. null if the Object array cannot be interpreted
     */
    public static Item fromObjectArray(Object[] values) {
        try {
            int id = Integer.parseInt(values[0].toString());
            String category = values[1].toString();
            String description = values[2].toString();
            return Item.create(id, category, description);
        } catch (Exception e) {
            return null;
        }
    }

    private Item(int id, String category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
    }

    /**
     *
     * @return int The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return String The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @return String The description
     */
    public String getDescription() {
        return description;
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
        newArr[currentIndex++] = getCategory();
        newArr[currentIndex] = getDescription();

        return newArr;
    }

    /**
     * Determines whether this instance follows the business rule
     * @return boolean The validation result
     */
    @Override
    public boolean followsBusinessRule() {
        return true;
    }
}
