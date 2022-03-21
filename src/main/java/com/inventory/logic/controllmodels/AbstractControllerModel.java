package com.inventory.logic.controllmodels;

import javax.swing.*;
import java.util.ArrayList;


/**
 * The abstract controller model for a controller going between persistence and UI Layer
 * @param <T> The type of persistence service
 * @param <U> The type of UI service
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public abstract class AbstractControllerModel <T, U extends JFrame> {

    /**
     * The persistence service provider
     */
    protected T persistenceSP;

    /**
     * The UI service provider
     */
    protected U uiRenderer;

    /**
     * The list of data from persistence service provider
     */
    protected ArrayList data;

    /**
     * Whether UI is rendered for the first time
     */
    protected boolean uiInitialized;

    public AbstractControllerModel() {
        data = new ArrayList<>();
        uiInitialized = false;
        /* Empty Constructor for specific subclass to overwrite behaviour */
    }

    /**
     * Returns the header for table view
     * @return String[] An array of header strings
     */
    public abstract String[] getHeaders();

    /**
     * Configures persistence service with a DB path
     * @return boolean Whether configuration is successful
     */
    public abstract boolean configureAccessObject(String dbPath);

    /**
     * Render UI for the first time
     */
    public abstract void initUI();

    /**
     * Refresh the UI
     */
    public abstract void refreshUI();

    /**
     * Updates DB when UI fires a create event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
    public abstract void create(Object[] values, String daoName);

    /**
     * Updates DB when UI fires a delete event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
    public abstract void delete(Object[] values, String daoName);

    /**
     * Updates DB when UI fires an update event
     * @param values The object array that contains resources to be updated
     * @param daoName The name of the database access object
     */
    public abstract void update(Object[] values, String daoName);
}
