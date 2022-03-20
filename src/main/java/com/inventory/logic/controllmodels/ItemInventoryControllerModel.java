package com.inventory.logic.controllmodels;

import com.inventory.logic.Item;
import com.inventory.logic.ItemInventory;
import com.inventory.logic.UpdateType;
import com.inventory.persistence.DaoService;
import com.inventory.persistence.ItemDao;
import com.inventory.persistence.ItemInventoryDao;
import com.inventory.presentation.InventoryTableFrame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        this.uiRenderer = new InventoryTableFrame(getHeaders(), "Inventory", ItemInventory.FIELD_LEN);

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
    public void refreshUI() {

    }

    @Override
    public UpdateType getCommandType(String commandString) {
        return commandTypes.get(commandString);
    }

    private void handlesUpdate(String daoName, HashMap<String, Integer> commandArgsMap) throws Exception {
        if(daoName.equals(ItemInventory.DAO_REF_NAME)) {
            int id;
            int inventoryId;
            int available;
            int inStock;
            int onOrder;

            id = commandArgsMap.get("-itemid");
            inventoryId = commandArgsMap.get("-inventoryid");
            available = commandArgsMap.get("-available");
            inStock = commandArgsMap.get("-inStock");
            onOrder = commandArgsMap.get("-onOrder");






        }
        else if (daoName.equals(Item.DAO_REF_NAME)) {
            // Not allowing setting Item objects as of now
        }
        else {
            throw new Exception();
        }

    }

    private void handlesCreate(String daoName, HashMap<String, Integer> commandArgsMap) throws Exception {

    }

    private void handlesDelete(String daoName, HashMap<String, Integer> commandArgsMap) throws Exception {

    }

    @Override
    public void onUserInput(UpdateType updateType, String daoName, String[] commands) throws Exception{
        // Record the command arguemnt:Integer pair e.g. -id 2
        HashMap<String, Integer> commandArgsMap = new HashMap<>();
        for (int i = 0; i<commands.length; i+=2)
        {
            commandArgsMap.put(commands[i], Integer.parseInt(commands[i+1]));
        }

        if (updateType == UpdateType.UPDATE) {


        }
        else if (updateType == UpdateType.DELETE) {


        }
        else if (updateType == UpdateType.CREATE) {

        }

    }

    @Override
    public void readInput() {
        // D iteminventory -id 5 -itemid 6
        //

        // Starts scanning commands
        Scanner scanner = new Scanner(System.in);
        try {
            // Get the action at Index 0 (C || U || D)
            String[] commands = scanner.nextLine().split(" ");
            UpdateType updateType = getCommandType(commands[0]);

            if (updateType == null) throw new Exception("Command Invalid!");

            // Redirect rest of command for specific actions
            onUserInput(updateType, commands[1], Arrays.copyOfRange(commands, 2, commands.length));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Command too short!");
        }
        catch (Exception e) {
            System.err.println("Command invalid!");
        }
    }
}
