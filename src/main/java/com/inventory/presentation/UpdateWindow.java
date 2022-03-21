package com.inventory.presentation;

import com.inventory.domain.ItemInventory;
import com.inventory.logic.UpdateType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateWindow extends JDialog {
    protected UpdateWindow(JFrame parent, String title, Object[] values) {
        super(parent, title, true);
        setLayout(null);

        // UI elements
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

        // Setting field texts
        availableTF.setText(values[ItemInventory.AVAILABLE_PROP_IND].toString());
        inStockTF.setText(values[ItemInventory.INSTOCK_PROP_IND].toString());
        onOrderTF.setText(values[ItemInventory.ONORDER_PROP_IND].toString());

        // Add in elements
        add(availableLabel);
        add(availableTF);
        add(inStockLabel);
        add(inStockTF);
        add(onOrderLabel);
        add(onOrderTF);
        add(updateButton);

        // Handles when user clicks update after entering the textfields
        updateButton.addActionListener (new DefaultActionListener(true)
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    values[ItemInventory.AVAILABLE_PROP_IND] = Integer.parseUnsignedInt(availableTF.getText());
                    values[ItemInventory.INSTOCK_PROP_IND] = Integer.parseUnsignedInt(inStockTF.getText());
                    values[ItemInventory.ONORDER_PROP_IND] = Integer.parseUnsignedInt(onOrderTF.getText());
                    publish(UpdateType.UPDATE, values, ItemInventory.DAO_REF_NAME);
                    setVisible(false);
                } catch (Exception err)
                {
                    availableTF.setText(values[ItemInventory.AVAILABLE_PROP_IND].toString());
                    inStockTF.setText(values[ItemInventory.INSTOCK_PROP_IND].toString());
                    onOrderTF.setText(values[ItemInventory.ONORDER_PROP_IND].toString());
                }
            }
        });
        setVisible(true);
    }
}
