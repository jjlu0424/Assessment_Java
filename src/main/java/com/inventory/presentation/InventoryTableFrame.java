package com.inventory.presentation;

import com.inventory.logic.Controller;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InventoryTableFrame extends AbstractTableFrame {
    private final TableModel tableModel;

    public InventoryTableFrame(String[] headers, String title, int dataFieldLength) {
        super(title);

        tableModel = new TableModel(new ArrayList<>(), headers, dataFieldLength);
        JTable table = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(table);

        // Default sizes
        setSize(2048, 1152);
        table.setBounds(0, 0, 1366, 768);
        table.setRowHeight( 32 );

        // Adding elements into the frame
        add(pane);
        setVisible(true);
    }

    public void insertRow(Object[] data)
    {
        tableModel.addRow(data);
    }

    public void deleteRow(int itemId, int inventoryId) {
        tableModel.deleteRow(itemId, inventoryId);
    }

    public void setRow(Object[] data, int itemId, int inventoryId)
    {
        tableModel.setRow(data, itemId, inventoryId);
    }

    public Object[] getRow(int itemId, int inventoryId) { return tableModel.getRow(itemId, inventoryId); }

    // Model for constructing Table
    private static class TableModel extends AbstractTableModel {
        private final String[] headers;
        private ArrayList<Object[]> data;
        private final int dataFieldLength;

        public TableModel(ArrayList<Object[]> data, String[] headers, int dataFieldLength) {
            super();
            this.data = data;
            this.headers = headers;
            this.dataFieldLength = dataFieldLength;
        }

        public int getColumnCount() {
            return headers.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return headers[col];
        }

        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }

        public void setValueAt(Object value, int row, int col) {
            data.get(row)[col] = value;
            fireTableCellUpdated(row, col);

        }

        protected void addRow(Object[] row) {
            data.add(row);
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }

        private void deleteRow(int row) {
            data.remove(row);
            fireTableRowsDeleted(row, row);
        }

        protected void deleteRow(int itemId, int inventoryId) {
            int row = findRowByIds(itemId, inventoryId);
            if (row >= 0) deleteRow(row);
        }

        private void setRow(Object[] rowdata, int row) {
            data.set(row, rowdata);
            fireTableRowsUpdated(row, row);
        }

        protected void setRow(Object[] rowdata, int itemId, int inventoryId) {
            int row = findRowByIds(itemId, inventoryId);
            setRow( rowdata, row);
        }

        private Object[] getRow(int row) {
            return data.get(row);
        }

        protected Object[] getRow(int itemId, int inventoryId) {
            int row = findRowByIds(itemId, inventoryId);
            if (row >= 0)
            {
                return data.get(row);
            }
            return null;
        }

        protected int findRowByIds(int itemId, int inventoryId)
        {
            int row = -1;
            for (int i = 0; i<data.size(); i++)
            {
                if (data.get(i).toString().equals(Integer.toString(itemId)) &&
                        data.get(i).toString().equals(Integer.toString(inventoryId))) return i;
            }
            return row;
        }

    }
}
