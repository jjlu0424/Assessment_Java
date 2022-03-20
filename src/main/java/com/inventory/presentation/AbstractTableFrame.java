package com.inventory.presentation;

import javax.swing.*;


public abstract class AbstractTableFrame extends JFrame {
        public AbstractTableFrame(String title) {
            super();
            setTitle(title);
        }

    public abstract void insertRow(Object[] data);

    public abstract void deleteRow(int id);

    public abstract void setRow(Object[] data, int id);

    public abstract Object[] getRow(int id);

    public abstract void emptyTable();
}

