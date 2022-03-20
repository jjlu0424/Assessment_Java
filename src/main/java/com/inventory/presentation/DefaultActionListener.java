package com.inventory.presentation;

import com.inventory.logic.Controller;
import com.inventory.logic.EventListener;
import com.inventory.logic.UpdateType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DefaultActionListener implements ActionListener {

    ArrayList<EventListener> eventListeners;

    public DefaultActionListener(boolean controllerRegistered)
    {
        eventListeners = new ArrayList<>();
        if (controllerRegistered) register(Controller.getInstance());

    }

    public void register(EventListener eventListener)
    {
        eventListeners.add(eventListener);
    }

    public void publish(UpdateType updateType, Object[] values, String daoName)
    {
        for (EventListener eventListener : eventListeners)
        {
            System.out.println(eventListener);
            eventListener.update(updateType, values, daoName);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
