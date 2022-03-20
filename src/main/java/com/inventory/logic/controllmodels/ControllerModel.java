package com.inventory.logic.controllmodels;

import com.inventory.logic.UpdateType;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ControllerModel <T, U extends JFrame, V> {

    protected T persistenceSP;
    protected U uiRenderer;
    protected ArrayList data;
    protected boolean uiInitialized;

    public ControllerModel(T persistenceSP, U uiRenderer)
    {
        this.persistenceSP = persistenceSP;
        this.uiRenderer = uiRenderer;
        data = new ArrayList<>();
        uiInitialized = false;
    }

    public ControllerModel(U uiRenderer)
    {
        this.uiRenderer = uiRenderer;
        data = new ArrayList<>();
        uiInitialized = false;
    }

    public ControllerModel() {
        data = new ArrayList<>();
        uiInitialized = false;
        /* Empty Constructor for specific subclass to overwrite behaviour */
    }

    public abstract boolean configureAccessObject(String dbPath);

    public abstract void initUI();

    public abstract void upateUI();

    public abstract void getAll();

    public abstract void onUserInput(UpdateType updateType, String targetAccessObject, Object[] updatedRecord);
}
