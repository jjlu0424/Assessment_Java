package com.inventory.logic;

import com.inventory.logic.controllmodels.AbstractControllerModel;

public class Controller implements EventListener {
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

    public void run()  {
        // Tell the controller model to render UI upon Configuration success
        controllerModel.initUI();
    }

    private Controller(AbstractControllerModel cm)  {
        this.controllerModel = cm;
        if (!controllerModel.configureAccessObject(dbUrl)) {
            System.err.println("Something went wrong");
            System.exit(1);
        }
    }


    @Override
    public void update(UpdateType updateType, Object[] values, String daoName) {
        System.out.println("hi");
        switch (updateType)
        {
            case CREATE -> this.controllerModel.create(values, daoName);
            case UPDATE -> this.controllerModel.update(values, daoName);
            case DELETE -> this.controllerModel.delete(values, daoName);
        }
    }
}
