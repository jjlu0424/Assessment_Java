package com.inventory.logic;

import com.inventory.logic.controllmodels.AbstractControllerModel;

/**
 * The singleton controller that updates UI and receives event from UI to update DB
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class Controller implements EventListener {
    private static Controller instance;
    private final String dbUrl =
            "jdbc:sqlite:/C:\\Users\\jjlu0\\Desktop\\Sqlite\\sqlite-tools-win32-x86-3380100\\InventoryDb.db";

    private final AbstractControllerModel controllerModel;


    /**
     * Creating the singleton instance
     */
    public static void createInstance(AbstractControllerModel controllerModel) {
        if (instance == null) {
            instance = new Controller(controllerModel);
        }
    }

    /**
     * Returns the existing instance
     * @return Controller The singleton instance, null if it does not exist
     */
    public static Controller getInstance() {
        return instance;
    }

    /**
     * Run the controller's model
     */
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

    /**
     * Listening to UI event and make updates to DB and UI accordingly
     */
    @Override
    public void update(UpdateType updateType, Object[] values, String daoName) {
        switch (updateType)
        {
            case CREATE -> this.controllerModel.create(values, daoName);
            case UPDATE -> this.controllerModel.update(values, daoName);
            case DELETE -> this.controllerModel.delete(values, daoName);
        }
    }
}
