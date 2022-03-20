package com.inventory.presentation;

import javax.swing.*;


public abstract class AbstractTableFrame extends JFrame {
        public AbstractTableFrame(String title) {
            super();
            setTitle(title);
        }

    public abstract void insertRow(Object[] data);

    public abstract void deleteRow(int itemId, int inventoryId);

    public abstract void setRow(Object[] data, int itemId, int inventoryId);

    public abstract Object[] getRow(int itemId, int inventoryId);
}

