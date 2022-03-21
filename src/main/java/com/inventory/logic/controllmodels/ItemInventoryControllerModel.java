package com.inventory.logic.controllmodels;

import com.inventory.domain.Item;
import com.inventory.domain.ItemInventory;
import com.inventory.persistence.DaoService;
import com.inventory.persistence.ItemDao;
import com.inventory.persistence.ItemInventoryDao;
import com.inventory.presentation.AbstractTableFrame;
import com.inventory.presentation.InventoryTableFrame;

/**
 * The Controller model for this specific application
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class ItemInventoryControllerModel extends AbstractControllerModel <DaoService, AbstractTableFrame> {
    public ItemInventoryControllerModel()
    {
        super();
        // Create DaoService singleton instance
        DaoService.createInstance();
        this.persistenceSP = DaoService.getInstance();

        persistenceSP.createNewDao(ItemInventory.DAO_REF_NAME, new ItemInventoryDao());
        persistenceSP.createNewDao(Item.DAO_REF_NAME, new ItemDao());
    }

    /**
     * Returns the header for table view
     * @return String[] An array of header strings
     */
    @Override
    public String[] getHeaders() {
        return new String[]{"Item ID", "Inventory ID", "Category", "Description", "Available",
                "In Stock", "On Order"};
    }

    /**
     * Configures persistence service with a DB path
     * @return boolean Whether configuration is successful
     */
    @Override
    public boolean configureAccessObject(String dbPath) {
        // Success if all the depended DAOs have been configured without errors
        return persistenceSP.configureDao(ItemInventory.DAO_REF_NAME, dbPath) &&
                persistenceSP.configureDao(Item.DAO_REF_NAME, dbPath);
    }

    /**
     * Render UI for the first time
     */
    @Override
    public void initUI() {
        // Only allows UI to be initialized once
        if (uiInitialized) return;

        this.uiRenderer = new InventoryTableFrame(getHeaders(), "Inventory");
        refreshUI();
    }

    /**
     * Refresh the UI
     */
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

    /**
     * Updates DB when UI fires a create event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
    @Override
    public void create(Object[] values, String daoName) {
        if (daoName.equals(ItemInventory.DAO_REF_NAME))
        {
            // Get the itemId and create a new itemInventory record
            try {
                int itemId = Integer.parseInt(values[0].toString());
                ItemInventory itemInventory =  ItemInventory.createNewForDB(itemId);
                if (DaoService.getInstance().getDao(daoName).insert(itemInventory)) {
                    refreshUI();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates DB when UI fires a delete event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
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
               System.err.println("Something went wrong");
            }
        }
    }

    /**
     * Updates DB when UI fires an update event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
    @Override
    public void update(Object[] values, String daoName) {
        if (daoName.equals(ItemInventory.DAO_REF_NAME))
        {
            // Get the itemId and create a new itemInventory record
            try {
                int id = Integer.parseInt(values[ItemInventory.INV_ID_IND].toString());
                ItemInventory itemInventory = ItemInventory.fromObjectArray(values);

                if (itemInventory != null
                        && itemInventory.followsBusinessRule()
                        && DaoService.getInstance().getDao(daoName).update(itemInventory))
                {
                    System.out.println("here!");
                    uiRenderer.setRow(values, id);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
