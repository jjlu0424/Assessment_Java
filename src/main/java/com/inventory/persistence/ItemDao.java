package com.inventory.persistence;

import com.inventory.logic.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDao implements Dao<Item>{
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
                item = new Item(itemId, category, description);
            }
            return item;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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
                Item item = new Item(id, category, description);

                itemList.add(item);;
            }
            return itemList;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Item itemInventory, Item updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int inventoryId) {
        return false;
    }
}
