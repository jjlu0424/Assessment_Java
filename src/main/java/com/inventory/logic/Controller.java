package com.inventory.logic;

import com.inventory.logic.controllmodels.AbstractControllerModel;

import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    private static Controller instance;
    private final String dbUrl =
            "jdbc:sqlite:/C:\\Users\\jjlu0\\Desktop\\Sqlite\\sqlite-tools-win32-x86-3380100\\InventoryDb.db";

    private final AbstractControllerModel controllerModel;

    public static void createInstance(AbstractControllerModel controllerModel) {
        if (instance == null) {
            instance = new Controller(controllerModel);
        }
    }

    public static Controller getInstance() {
        return instance;
    }

    private void run() throws Exception {
        // Tell the controller model to render UI upon Configuration success
        if (controllerModel.configureAccessObject(dbUrl)) {
            controllerModel.initUI();
            controllerModel.readInput();
        } else
        {
            System.exit(1);
        }
    }

    private Controller(AbstractControllerModel cm)
    {
        this.controllerModel = cm;
        try {
            run();
        } catch (Exception e)
        {
            System.err.println("Something went wrong");
            System.exit(1);
        }
    }


}
