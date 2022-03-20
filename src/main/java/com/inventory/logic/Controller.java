package com.inventory.logic;

import com.inventory.logic.controllmodels.ControllerModel;

public class Controller {
    private static Controller instance;
    private final String dbUrl =
            "jdbc:sqlite:/C:\\Users\\jjlu0\\Desktop\\Sqlite\\sqlite-tools-win32-x86-3380100\\InventoryDb.db";

    private final ControllerModel controllerModel;

    public static void createInstance(ControllerModel controllerModel) {
        if (instance == null) {
            instance = new Controller(controllerModel);
        }
    }

    public static Controller getInstance() {
        return instance;
    }

    private void onInit() {
        // Tell the controller model to render UI upon Configuration success
        if (controllerModel.configureAccessObject(dbUrl)) {
            controllerModel.initUI();
        }
    }

    private Controller(ControllerModel cm)
    {
        this.controllerModel = cm;
        onInit();
    }
}
