package com.inventory.presentation;

import com.inventory.logic.ItemInventory;
import com.inventory.logic.UpdateType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateWindow extends JDialog {
    public UpdateWindow(JFrame parent, String title, int inventoryId, int available, int inStock, int onOrder) {
        super(parent, title, true);
        setLayout(null);

        JLabel availableLabel = new JLabel("Available");
        JTextField availableTF = new JTextField();
        JLabel inStockLabel = new JLabel("In Stock");
        JTextField inStockTF = new JTextField();
        JLabel onOrderLabel = new JLabel("On Order");
        JTextField onOrderTF = new JTextField();
        JButton updateButton = new JButton("Update");

        // Setting sizes
        setSize(500, 500);
        availableLabel.setBounds(20, 20, 100, 25);
        availableTF.setBounds(150, 20, 100, 25);
        inStockLabel.setBounds(20, 50, 100, 25);
        inStockTF.setBounds(150, 50, 100, 25);
        onOrderLabel.setBounds(20, 80, 100, 25);
        onOrderTF.setBounds(150, 80, 100, 25);
        updateButton.setBounds(200, 200, 100, 100);

        availableTF.setText(Integer.toString(available));
        inStockTF.setText(Integer.toString(inStock));
        onOrderTF.setText(Integer.toString(onOrder));

        // Adding all in
        add(availableLabel);
        add(availableTF);
        add(inStockLabel);
        add(inStockTF);
        add(onOrderLabel);
        add(onOrderTF);
        add(updateButton);

        updateButton.addActionListener (new DefaultActionListener(true)
        {
            public void actionPerformed( ActionEvent e )
            {

            }
        });
        setVisible(true);
    }
}
