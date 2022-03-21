package com.inventory.presentation;

import javax.swing.*;

/**
 * UI Provider for displaying table view
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public abstract class AbstractTableFrame extends JFrame {
    /**
     *
     * @param title The title of the UI
     */
    public AbstractTableFrame(String title) {
        super();
        setTitle(title);
    }


    /**
     * Insert a row into table view
     * @param data The data to be inserted
     */
    public abstract void insertRow(Object[] data);

    /**
     * Delete a row from table view
     * @param row The row to be deleted
     */
    public abstract void deleteRow(int row);

    /**
     * Update a row from table view
     * @param data The updated data
     * @param row The row to be updated
     */
    public abstract void setRow(Object[] data, int row);

    /**
     * Empties the whole tabke view
     */
    public abstract void emptyTable();
}

