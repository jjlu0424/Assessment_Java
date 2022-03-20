package com.inventory.logic;
import com.inventory.logic.controllmodels.ItemInventoryControllerModel;
import com.inventory.presentation.TableFrame;

import com.inventory.persistence.DaoService;

public class Application {
    public static void main(String[] args) {
        // Pass a frame (UI) and daoService to the controller
        Controller.createInstance(new ItemInventoryControllerModel());
    }
}
