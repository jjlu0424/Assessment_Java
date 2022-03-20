package com.inventory.persistence;

import com.inventory.logic.Item;
import com.inventory.logic.ItemInventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemInventoryDao implements Dao<ItemInventory> {
    private String dbUrl;
    private Connection connection;

    @Override
    public boolean configure(String dbPath) {
        System.out.println("here");
        dbUrl = dbPath;
        try {
            connection = DriverManager.getConnection(dbUrl);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
                Item item = new Item(itemId, category, description);

                int inventoryId = res.getInt("InventoryId");
                int available = res.getInt("Available");
                int inStock = res.getInt("InStock");
                int onOrder = res.getInt("OnOrder");
                itemInventory = new ItemInventory(item, inventoryId, available, inStock, onOrder);
                System.out.println(itemInventory);
            }
            return itemInventory;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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
                Item item = new Item(id, category, description);

                int inventoryId = res.getInt("InventoryId");
                int available = res.getInt("Available");
                int inStock = res.getInt("InStock");
                int onOrder = res.getInt("OnOrder");
                ItemInventory itemInventory = new ItemInventory(item, inventoryId, available, inStock, onOrder);

                itemInventoryList.add(itemInventory);;
            }
            return itemInventoryList;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
