package com.inventory.logic;
import com.inventory.persistence.ItemInventoryDao;
import com.inventory.presentation.TableFrame;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;
import com.inventory.persistence.DaoService;

public class Application {
    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:sqlite:/C:\\Users\\jjlu0\\Desktop\\Sqlite\\sqlite-tools-win32-x86-3380100\\InventoryDb.db";
//        try{
//            Connection connection = DriverManager.getConnection(jdbcUrl);
//            String sql = "SELECT * FROM users";
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next())
//            {
//                System.out.println(resultSet);
//            }
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        // Create DaoService singleton instance
        DaoService.createInstance();

        // Pass a frame (UI) and daoService to the controller
        String[] defaultColumnHeaders = {"Item ID", "Category", "Description", "Available", "In Stock", "On Order"};
        String[][] emptyData = {};
        TableFrame tf = new TableFrame(emptyData, defaultColumnHeaders, "Inventory");
        Controller.createInstance(tf, DaoService.getInstance());
    }
}
