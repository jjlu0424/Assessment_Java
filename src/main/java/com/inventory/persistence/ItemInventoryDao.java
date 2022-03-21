package com.inventory.persistence;

import com.inventory.domain.ItemInventory;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object for {@link com.inventory.domain.ItemInventory}
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class ItemInventoryDao implements Dao<ItemInventory> {
    private String dbUrl;
    private Connection connection;

    /**
     * Configures with database path
     * @param dbPath The path or url of the Database
     */
    @Override
    public boolean configure(String dbPath) {
        dbUrl = dbPath;
        try {
            connection = DriverManager.getConnection(dbUrl);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(dbPath ,config.toProperties());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get a single record with specified id
     * @param id The id of the sought resource
     * @return ItemInventory The result of the query
     */
    @Override
    public ItemInventory get(int id) {
        PreparedStatement pres = null;
        ItemInventory itemInventory = null;
        try{
            String sql = "SELECT * " +
                        "FROM ItemInventory INNER JOIN Items ON ItemInventory.ItemId = Items.Id " +
                        "WHERE ItemInventory.InventoryId =?";
            pres = connection.prepareStatement(sql);
            pres.setInt(1, id);

            ResultSet res = pres.executeQuery();
            while (res.next())
            {
                int itemId = res.getInt("Id");
                String category = res.getString("Category");
                String description = res.getString("Description");

                int inventoryId = res.getInt("InventoryId");
                int available = res.getInt("Available");
                int inStock = res.getInt("InStock");
                int onOrder = res.getInt("OnOrder");
                itemInventory = ItemInventory.create(id, category, description, inventoryId, available,
                                                    inStock, onOrder);

            }
            return itemInventory;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all records with specified id
     * @return ArrayList<ItemInventory> The result of query in an ArrayList
     */
    @Override
    public ArrayList<ItemInventory> getAll() {
        ArrayList<ItemInventory> itemInventoryList = new ArrayList<>();
        PreparedStatement pres = null;
        try{
            String sql = "SELECT * FROM ItemInventory INNER JOIN Items ON ItemInventory.ItemId = Items.Id";
            pres = connection.prepareStatement(sql);

            ResultSet res = pres.executeQuery();
            while (res.next())
            {
                int id = res.getInt("Id");
                String category = res.getString("Category");
                String description = res.getString("Description");

                int inventoryId = res.getInt("InventoryId");
                int available = res.getInt("Available");
                int inStock = res.getInt("InStock");
                int onOrder = res.getInt("OnOrder");
                ItemInventory itemInventory = ItemInventory.create(id, category, description, inventoryId, available,
                                                                    inStock, onOrder);

                itemInventoryList.add(itemInventory);;
            }
            return itemInventoryList;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserts a record into the database
     * @param itemInventory The record to be inserted
     * @return boolean Whether the insertion is successful
     */
    @Override
    public boolean insert(ItemInventory itemInventory) {
        PreparedStatement pres = null;
        try {
            String sql = "INSERT INTO ItemInventory VALUES (?, ?, ?, ?, ?)";
            pres = this.connection.prepareStatement(sql);

            // Set InventoryId to null to auto_increment in sqlite
            pres.setNull(1, java.sql.Types.INTEGER);
            pres.setInt(2, itemInventory.getId());
            pres.setInt(3, itemInventory.getAvailable());
            pres.setInt(4, itemInventory.getInStock());
            pres.setInt(5, itemInventory.getOnOrder());

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
    public boolean update(ItemInventory updatedObject) {
        PreparedStatement pres = null;
        try {
            String sql = "UPDATE ItemInventory SET Available=?, InStock=?, OnOrder=? WHERE InventoryId=?";
            pres = this.connection.prepareStatement(sql);

            pres.setInt(1, updatedObject.getAvailable());
            pres.setInt(2, updatedObject.getInStock());
            pres.setInt(3, updatedObject.getOnOrder());
            pres.setInt(4, updatedObject.getInventoryId());

            // .executeUpdate() for INSERT, UPDATE, DELETE
            pres.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a record from the database
     * @param inventoryId The id of the record to be deleted
     * @return boolean Whether the deletion is successful
     */
    @Override
    public boolean delete(int inventoryId) {
        PreparedStatement pres = null;
        try {
            String sql = "DELETE FROM ItemInventory WHERE InventoryId=?";
            pres = this.connection.prepareStatement(sql);

            pres.setInt(1, inventoryId);

            // .executeUpdate() for INSERT, UPDATE, DELETE
            pres.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
