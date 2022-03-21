package com.inventory.presentation;

import com.inventory.domain.ItemInventory;
import com.inventory.logic.UpdateType;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The specific UI Provider for displaying table view for inventory
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class InventoryTableFrame extends AbstractTableFrame {
    private final TableModel tableModel;

    /**
     *
     * @param headers Table headers
     * @param title The title of the UI
     */
    public InventoryTableFrame(String[] headers, String title) {
        super(title);

        tableModel = new TableModel(new ArrayList<>(), headers);
        JTable table = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        // Setting text fileds
        JLabel itemIdLb = new JLabel("Item Id");
        JTextField itemIdTF = new JTextField();

        // Buttons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Set");

        // Setting layouts
        itemIdLb.setBounds(300, 220, 50, 25);
        itemIdTF.setBounds(350, 220, 100, 25);
        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        setLayout(null);

        // Adding elements into the frame
        add(pane);
        add(itemIdLb);
        add(itemIdTF);
        add(btnAdd);
        add(itemIdTF);
        add(btnDelete);
        add(btnUpdate);

        // Creating button methods
        btnAdd.addActionListener(new DefaultActionListener(true) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] object = new Object[1];
                try {
                    object[0] = Integer.parseInt(itemIdTF.getText());
                    publish(UpdateType.CREATE, object, ItemInventory.DAO_REF_NAME);
                } catch (Exception err) {
                    itemIdTF.setText("");
                }
            };
        });

        // button remove row
        btnDelete.addActionListener(new DefaultActionListener(true) {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = new Object[1];
                try {
                    int row = table.getSelectedRow();
                    Object inventoryId = tableModel.getValueAt(row, ItemInventory.INV_ID_IND);;
                    objects[0] = inventoryId;
                    publish(UpdateType.DELETE, objects, ItemInventory.DAO_REF_NAME);
                } catch (Exception err) {
                    System.err.println(err.toString());
                }
            };
        });

        // button update row
        btnUpdate.addActionListener(new DefaultActionListener(true){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (table.getSelectedRow() >= 0) {
                    new UpdateWindow(InventoryTableFrame.this, "Update", tableModel.getRowAt(row).clone());
                    table.clearSelection();
                };
            }
        });

        setVisible(true);
    }

    /**
     * Insert a row into table view
     * @param data The data to be inserted
     */
    @Override
    public void insertRow(Object[] data) {
        tableModel.insertRow(data);
    }

    /**
     * Delete a row from table view
     * @param inventoryId The row containing inventoryId will be deleted
     */
    @Override
    public void deleteRow(int inventoryId) {
        tableModel.deleteRow(inventoryId);
    }

    /**
     * Update a row from table view
     * @param data The updated data
     * @param inventoryId The row containing inventoryId will be updated
     */
    @Override
    public void setRow(Object[] data, int inventoryId) {
        tableModel.setRow(data, inventoryId);
    }

    /**
     * Empties the whole table view
     */
    @Override
    public void emptyTable() {
        tableModel.clearTable();
    }

    // Model for table constructions and manipulations specific to this table
    private static class TableModel extends AbstractTableModel {
        private final String[] headers;
        private ArrayList<Object[]> data;

        public TableModel(ArrayList<Object[]> data, String[] headers) {
            super();
            this.data = data;
            this.headers = headers;
        }

        @Override
        public int getColumnCount() {
            return headers.length;
        }

        @Override
        public int getRowCount() {
            if (data == null) return 0;
            return data.size();
        }

        @Override
        public String getColumnName(int col) {
            return headers[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data.get(row)[col] = value;
            fireTableCellUpdated(row, col);
        }

        protected void insertRow(Object[] rowData) {
            data.add(rowData);
            fireTableRowsInserted(data.size() , data.size());
        }

        protected void deleteRow(int inventoryId) {
            int row = findRowById(inventoryId);
            if (row >= 0) {
                data.remove(row);
                fireTableRowsDeleted(row + 1, row + 1);
                fireTableDataChanged();
            }
        }

        protected void setRow(Object[] rowdata, int inventoryId) {
            int row = findRowById(inventoryId);
            data.set(row, rowdata);
            if (row >= 0) fireTableRowsUpdated(row + 1, row + 1);
        }

        protected Object[] getRow(int inventoryId) {
            int row = findRowById(inventoryId);
            if (row >= 0)
            {
                return data.get(row);
            }
            return null;
        }

        protected Object[] getRowAt(int row) {
            return data.get(row);
        }

        protected int findRowById(int inventoryId)
        {
            int row = -1;
            for (int i = 0; i<data.size(); i++)
            {
                if (data.get(i)[1].toString().equals(Integer.toString(inventoryId))) return i;
            }
            return row;
        }

        protected void clearTable()
        {
            int size = data.size();
            data.clear();
            fireTableRowsDeleted(0, size);
        }
    }
}
