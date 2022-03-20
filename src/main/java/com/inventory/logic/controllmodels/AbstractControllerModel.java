package com.inventory.logic.controllmodels;
import com.inventory.logic.UpdateType;
import com.inventory.persistence.DaoService;
import com.inventory.presentation.AbstractTableFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractControllerModel {

    protected DaoService persistenceSP;
    protected AbstractTableFrame uiRenderer;
    protected ArrayList data;
    protected boolean uiInitialized;

    public AbstractControllerModel() {
        data = new ArrayList<>();
        uiInitialized = false;
        /* Empty Constructor for specific subclass to overwrite behaviour */
    }

    public abstract String[] getHeaders();

    public abstract boolean configureAccessObject(String dbPath);

    public abstract void initUI();

    public abstract void refreshUI();

    public abstract void create(Object[] values, String daoName);

    public abstract void delete(Object[] values, String daoName);

    public abstract void update(Object[] values, String daoName);

//    public abstract void onUserInput(UpdateType updateType, String targetAccessObject, String[] commands)
//            throws Exception;
//
//    public abstract void readInput();
//
//    public abstract UpdateType getCommandType(String commandString);
}
