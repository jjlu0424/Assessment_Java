package com.inventory.presentation;

import com.inventory.logic.Controller;
import com.inventory.logic.EventListener;
import com.inventory.logic.SubjectBase;
import com.inventory.logic.UpdateType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Default action listener for a button click event
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class DefaultActionListener implements ActionListener, SubjectBase {
    private ArrayList<EventListener> eventListeners;

    /**
     *
     * @param controllerRegistered Whether controller should be included in the subscriber list
     */
    public DefaultActionListener(boolean controllerRegistered)
    {
        eventListeners = new ArrayList<>();
        if (controllerRegistered) register(Controller.getInstance());
    }

    /**
     * Notifies the subscibers for updates
     * @param eventListener The subscriber of the current subject
     */
    public void register(EventListener eventListener)
    {
        eventListeners.add(eventListener);
    }

    /**
     * Notify subscribers for updates
     * @param updateType The type of update {@link UpdateType}
     * @param values The values after update
     * @param daoName Name of persistence service the update refers to
     */
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
