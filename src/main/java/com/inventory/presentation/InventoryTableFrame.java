package com.inventory.presentation;

import com.inventory.logic.Controller;
import com.inventory.logic.ItemInventory;
import com.inventory.logic.UpdateType;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InventoryTableFrame extends AbstractTableFrame {
    private final TableModel tableModel;

    public InventoryTableFrame(String[] headers, String title, int dataFieldLength) {
        super(title);

        tableModel = new TableModel(new ArrayList<>(), headers, dataFieldLength);
        JTable table = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        // Setting text fileds
        JLabel itemIdLb = new JLabel("Item Id");
        JTextField itemIdTF = new JTextField();

        // Buttons
        Button btnAdd = new Button("Add");
        Button btnDelete = new Button("Delete");
        Button btnUpdate = new Button("Update");

        // Register controller to buttons
        btnAdd.register(Controller.getInstance());
        btnDelete.register(Controller.getInstance());
        btnUpdate.register(Controller.getInstance());

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
                    err.printStackTrace();
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
                    Object inventoryId = tableModel.getValueAt(row, 1);
                    System.out.println(inventoryId);
                    objects[0] = inventoryId;
                    publish(UpdateType.DELETE, objects, ItemInventory.DAO_REF_NAME);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            };
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                // i = the index of the selected row
                int row = table.getSelectedRow();

//                textId.setText(model.getValueAt(i, 0).toString());
//                textFname.setText(model.getValueAt(i, 1).toString());
//                textLname.setText(model.getValueAt(i, 2).toString());
//                textAge.setText(model.getValueAt(i, 3).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new DefaultActionListener(true){

            @Override
            public void actionPerformed(ActionEvent e) {

//                // i = the index of the selected row
//                int i = table.getSelectedRow();
//
//                if(i >= 0)
//                {
//                    model.setValueAt(textId.getText(), i, 0);
//                    model.setValueAt(textFname.getText(), i, 1);
//                    model.setValueAt(textLname.getText(), i, 2);
//                    model.setValueAt(textAge.getText(), i, 3);
//                }
//                else{
//                    System.out.println("Update Error");
//                }
            }
        });
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });


        setVisible(true);
    }

    public void insertRow(Object[] data) {
        tableModel.insertRow(data);
    }

    public void deleteRow(int inventoryId) {
        tableModel.deleteRow(inventoryId);
    }

    public void setRow(Object[] data, int inventoryId) {
        tableModel.setRow(data, inventoryId);
    }

    public Object[] getRow(int inventoryId) {
        return tableModel.getRow(inventoryId);
    }

    public void emptyTable() {
        tableModel.clearTable();
    }


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
            if (data == null) return 0;

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

        public void insertRow(Object[] rowData) {
            data.add(rowData);
            fireTableRowsInserted(data.size() , data.size());
        }

        protected void deleteRow(int inventoryId) {
            int row = findRowById(inventoryId);
            if (row >= 0) {
                data.remove(row);
                fireTableRowsDeleted(row+1, row+1);
            }
        }

        protected void setRow(Object[] rowdata, int inventoryId) {
            int row = findRowById(inventoryId);
            data.set(row, rowdata);
            if (row >= 0) fireTableRowsUpdated(row+1, row+1);
        }

        protected Object[] getRow(int inventoryId) {
            int row = findRowById(inventoryId);
            if (row >= 0)
            {
                return data.get(row);
            }
            return null;
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
