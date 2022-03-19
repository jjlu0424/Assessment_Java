package com.inventory.presentation;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class TableFrame extends JFrame {


    public TableFrame(Object[][] data, String[] headers, String title) {
        super();
        setTitle(title);

        JTable table = new JTable(new TableModel(data, headers));
        JScrollPane pane = new JScrollPane(table);

        // Default sizes
        setSize(1366, 768);
        table.setBounds(30, 80, 640, 480);

        JButton b = new JButton("Search");
        // Adding elements into the frame
        add(pane);
        add(new JTextField(16));
        add(b);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);

    }


    // Model for constructing JTable
    private class TableModel extends AbstractTableModel {
        private String[] headers;
        private Object[][] data;


        public TableModel(Object[][] data, String[] headers)
        {
            super();
            this.headers = headers;
            this.data = data;
        }


        public int getColumnCount() {
            return headers.length;
        }


        public int getRowCount() {
            return data.length;
        }


        public String getColumnName(int col) {
            return headers[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }


        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
