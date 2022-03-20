package com.inventory.logic;

public class Item extends DTO {
    public static final String DAO_REF_NAME = "items";
    public static final int FIELD_LEN = 3;
    private int id;
    private String category;
    private String description;

    public Item(int id, String category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Object[] intoObjectArray() {
        Object[] newArr = new Object[FIELD_LEN];
        int currentIndex = 0;
        newArr[currentIndex++] = getId();
        newArr[currentIndex++] = getCategory();
        newArr[currentIndex] = getDescription();

        return newArr;
    }

    @Override
    public boolean followsBusinessRule(Object[] objects) {
        return true;
    }
}
