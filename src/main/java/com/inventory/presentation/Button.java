package com.inventory.presentation;

import com.inventory.logic.EventListener;
import com.inventory.logic.UpdateType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button extends JButton {
    ArrayList<EventListener> eventListeners;

    public Button(String text)
    {
        super(text);
        eventListeners = new ArrayList<>();
    }


    public void register(EventListener eventListener)
    {
        eventListeners.add(eventListener);
    }

    public void publish(UpdateType updateType, Object[] values, String daoName)
    {
        for (EventListener eventListener : eventListeners)
        {
            eventListener.update(updateType, values, daoName);
        }
    }

}