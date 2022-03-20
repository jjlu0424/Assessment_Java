package com.inventory.presentation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableFrame extends JFrame {
    private final TableModel tableModel;

    public TableFrame(String[] headers, String title) {
        super();
        setTitle(title);

        tableModel = new TableModel(new ArrayList<>(), headers);
        JTable table = new JTable(tableModel);
        JScrollPane pane = new JScrollPane(table);

        // Default sizes
        setSize(1366, 768);
        table.setBounds(30, 80, 640, 480);

        // Adding elements into the frame
        add(pane);
        add(new JTextField(16));
        add(new JButton("Search"));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
    }

    public void insertRow(Object[] data)
    {
        tableModel.addRow(data);
    }

    public void setRow(Object[] data)
    {
        tableModel.addRow(data);
    }

    // https://stackoverflow.com/questions/16785982/how-to-refresh-data-in-jtable-i-am-using-tablemodel
    // Model for constructing JTable
    private static class TableModel extends AbstractTableModel {
        private String[] headers;
        private ArrayList<Object[]> data;

        public TableModel(ArrayList<Object[]> data, String[] headers) {
            super();
            this.data = data;
            this.headers = headers;
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

        public void setRow(Object[] row)
        {

        }

        public void addRow(Object[] row)
        {
            data.add(row);
            fireTableRowsInserted(data.size() - 1 , data.size());
        }
    }
}
