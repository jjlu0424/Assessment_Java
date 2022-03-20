package com.inventory.logic;
import com.inventory.logic.controllmodels.ItemInventoryControllerModel;

import com.inventory.persistence.DaoService;

public class Application {
    public static void main(String[] args) {
        Controller.createInstance(new ItemInventoryControllerModel());
    }
}
