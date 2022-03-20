package com.inventory.logic.controllmodels;

import com.inventory.logic.Item;
import com.inventory.logic.ItemInventory;
import com.inventory.logic.UpdateType;
import com.inventory.persistence.DaoService;
import com.inventory.persistence.ItemDao;
import com.inventory.persistence.ItemInventoryDao;
import com.inventory.presentation.InventoryTableFrame;

import java.util.HashMap;


public class ItemInventoryControllerModel extends AbstractControllerModel {
    private static HashMap<String, UpdateType> commandTypes = new HashMap<String, UpdateType>() {
        {
            put("C", UpdateType.CREATE);
            put("U", UpdateType.UPDATE);
            put("D", UpdateType.DELETE);
        }
    };

    public ItemInventoryControllerModel()
    {
        super();
        // Create DaoService singleton instance
        DaoService.createInstance();
        this.persistenceSP = DaoService.getInstance();

        persistenceSP.createNewDao(ItemInventory.DAO_REF_NAME, new ItemInventoryDao());
        persistenceSP.createNewDao(Item.DAO_REF_NAME, new ItemDao());
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"Item ID", "Inventory ID", "Category", "Description", "Available",
                "In Stock", "On Order"};
    }

    @Override
    public boolean configureAccessObject(String dbPath) {
        // Success if all the depended DAOs have been configured without errors
        return persistenceSP.configureDao(ItemInventory.DAO_REF_NAME, dbPath) &&
                persistenceSP.configureDao(Item.DAO_REF_NAME, dbPath);
    }

    @Override
    public void initUI() {
        // Only allows UI to be initialized once
        if (uiInitialized) return;

        this.uiRenderer = new InventoryTableFrame(getHeaders(), "Inventory", ItemInventory.FIELD_LEN);
        refreshUI();
    }


    /* Rerenders all the items */
    @Override
    public void refreshUI() {
        uiRenderer.emptyTable();
        // Grab all ItemInventory data for rendering
        data = persistenceSP.getDao(ItemInventory.DAO_REF_NAME).getAll();
        for (Object o: data)
        {
            try {
                ItemInventory i = (ItemInventory) o;
                uiRenderer.insertRow(i.intoObjectArray());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            } finally {
                uiInitialized = true;
            }
        }

    }

    @Override
    public void create(Object[] values, String daoName) {
        if (daoName.equals(ItemInventory.DAO_REF_NAME))
        {
            // Get the itemId and create a new itemInventory record
            try {
                int itemId = Integer.parseInt(values[0].toString());
                ItemInventory itemInventory = new ItemInventory(new Item(itemId, null, null), 0 ,0 ,0 ,0);
                if (DaoService.getInstance().getDao(daoName).insert(itemInventory)) {
                    refreshUI();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Object[] values, String daoName) {
        if (daoName.equals(ItemInventory.DAO_REF_NAME))
        {
            // Get the itemId and create a new itemInventory record
            try {
                int id = Integer.parseInt(values[0].toString());
                if (DaoService.getInstance().getDao(daoName).delete(id)) {
                    uiRenderer.deleteRow(id);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object[] values, String daoName) {

    }

}
