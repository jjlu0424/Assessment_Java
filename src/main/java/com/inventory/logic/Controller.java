package com.inventory.logic;

import com.inventory.persistence.DaoService;
import com.inventory.persistence.ItemInventoryDao;

import javax.swing.*;
import java.util.List;

public class Controller {
    private static Controller instance;
    private static String dbUrl =
            "jdbc:sqlite:/C:\\Users\\jjlu0\\Desktop\\Sqlite\\sqlite-tools-win32-x86-3380100\\InventoryDb.db";

    private JFrame ui;
    private final DaoService daoService;
    private List data;


    public static void createInstance(JFrame ui, DaoService daoService)
    {
        if (instance == null)
        {
            instance = new Controller(ui, daoService);
        }
    }

    public static Controller getInstance()
    {
        return instance;
    }

    private void onInit() {
        daoService.createNewDao(ItemInventory.DAO_REF_NAME, new ItemInventoryDao());
        // Configuration success
        if (daoService.configureDao(ItemInventory.DAO_REF_NAME, dbUrl)) {
            // TODO: Update frame here
            data = daoService.getDao(ItemInventory.DAO_REF_NAME).getAll();
        }
        else {

        }
    }

    private Controller(JFrame ui, DaoService daoService)
    {
        this.ui = ui;
        this.daoService = daoService;
        this.onInit();
    }
}
