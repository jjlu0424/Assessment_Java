package com.inventory.logic;
import com.inventory.logic.controllmodels.ItemInventoryControllerModel;

/**
 * The Inventory system
 *
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class Application {
    public static void main(String[] args) {
        Controller.createInstance(new ItemInventoryControllerModel());
        Controller.getInstance().run();
    }
}
