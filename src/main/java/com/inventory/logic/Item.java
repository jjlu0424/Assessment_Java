package com.inventory.logic;

public class Item {
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
}
