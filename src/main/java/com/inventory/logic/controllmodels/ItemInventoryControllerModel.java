package com.inventory.logic.controllmodels;

import com.inventory.logic.ItemInventory;
import com.inventory.logic.UpdateType;
import com.inventory.persistence.DaoService;
import com.inventory.persistence.ItemInventoryDao;
import com.inventory.presentation.TableFrame;

public class ItemInventoryControllerModel extends ControllerModel<DaoService, TableFrame, ItemInventory> {

    public ItemInventoryControllerModel(DaoService persistenceSP, TableFrame uiRenderer) {
        super(persistenceSP, uiRenderer);
        persistenceSP.createNewDao(ItemInventory.DAO_REF_NAME, new ItemInventoryDao());
    }

    public ItemInventoryControllerModel()
    {
        super();
        // Create DaoService singleton instance
        DaoService.createInstance();
        String[] defaultColumnHeaders = {"Item ID", "Inventory ID", "Category", "Description", "Available",
                "In Stock", "On Order"};
        this.persistenceSP = DaoService.getInstance();
        this.uiRenderer = new TableFrame(defaultColumnHeaders, "Inventory");

        persistenceSP.createNewDao(ItemInventory.DAO_REF_NAME, new ItemInventoryDao());
    }

    @Override
    public boolean configureAccessObject(String dbPath) {
        return persistenceSP.configureDao(ItemInventory.DAO_REF_NAME, dbPath);
    }

    @Override
    public void initUI() {
        if (uiInitialized) return;
        getAll();
        for (Object o: data)
        {
            try {
                ItemInventory i = (ItemInventory) o;
                Object[] row = new Object[ItemInventory.FIELD_LEN];

                int currentIndex = 0;
                row[currentIndex++] = i.getId();
                row[currentIndex++] = i.getInventoryId();
                row[currentIndex++] = i.getItemCategory();
                row[currentIndex++] = i.getItemDescription();
                row[currentIndex++] = i.getAvailable();
                row[currentIndex++] = i.getInStock();
                row[currentIndex++] = i.getOnOrder();

                uiRenderer.insertRow(row);
            } catch (Exception e)
            {

            } finally {
                uiInitialized = true;
            }
        }
    }


    @Override
    public void upateUI() {

    }

    @Override
    public void getAll() {
        data = persistenceSP.getDao(ItemInventory.DAO_REF_NAME).getAll();
    }

    @Override
    public void onUserInput(UpdateType updateType, String targetAccessObject, Object[] updatedRecord) {
        if (updateType == UpdateType.UPDATE) {

        }
        else if (updateType == UpdateType.DELETE) {

        }
        else if (updateType == UpdateType.INSERT) {

        }
    }
}
