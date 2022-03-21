package com.inventory.persistence;

import com.inventory.domain.Item;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object for Item object {@link com.inventory.domain.Item}
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class ItemDao implements Dao<Item>{
    private Connection connection;

    /**
     * Configures with database path
     * @param dbPath The path or url of the Database
     */
    @Override
    public boolean configure(String dbPath) {
        try {
            connection = DriverManager.getConnection(dbPath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get a single record with specified id
     * @param id The id of the sought resource
     * @return Item The result of the query
     */
    @Override
    public Item get(int id) {
        PreparedStatement pres = null;
        Item item = null;
        try{
            String sql = "SELECT * " +
                    "FROM Items  " +
                    "WHERE Id =?";
            pres = connection.prepareStatement(sql);
            pres.setInt(1, id);

            ResultSet res = pres.executeQuery();
            while (res.next())
            {
                int itemId = res.getInt("Id");
                String category = res.getString("Category");
                String description = res.getString("Description");
                item = Item.create(itemId, category, description);
            }
            return item;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all records with specified id
     * @return ArrayList<Item> The result of query in an ArrayList
     */
    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> itemList = new ArrayList<>();
        PreparedStatement pres = null;
        try{
            String sql = "SELECT * FROM Items";
            pres = connection.prepareStatement(sql);

            ResultSet res = pres.executeQuery();
            while (res.next())
            {
                int id = res.getInt("Id");
                String category = res.getString("Category");
                String description = res.getString("Description");
                Item item = Item.create(id, category, description);

                itemList.add(item);;
            }
            return itemList;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserts a record into the database
     * @param item The record to be inserted
     * @return boolean Whether the insertion is successful
     */
    @Override
    public boolean insert(Item item) {
        PreparedStatement pres = null;
        try {
            String sql = "INSERT INTO Items VALUES (?, ?, ?)";
            pres = this.connection.prepareStatement(sql);

            // Set InventoryId to null to auto_increment in sqlite
            pres.setNull(1, java.sql.Types.INTEGER);
            pres.setString(2, item.getCategory());
            pres.setString(3, item.getDescription());


            // .executeUpdate() for INSERT, UPDATE, DELETE
            pres.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update a record in the database
     * @param updatedObject The record to be updated
     * @return boolean Whether the update is successful
     */
    @Override
    public boolean update(Item updatedObject) {
        return false;
    }

    /**
     * Delete a record from the database
     * @param inventoryId The id of the record to be deleted
     * @return boolean Whether the deletion is successful
     */
    @Override
    public boolean delete(int inventoryId) {
        return false;
    }
}
