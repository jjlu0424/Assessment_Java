package com.inventory.presentation;

import com.inventory.logic.EvenListener;
import javax.swing.*;
import java.util.ArrayList;

public class Button extends JButton {
    private ArrayList<EvenListener> evenListeners;

    public Button(String text)
    {
        super(text);
        evenListeners = new ArrayList<>();
    }

    public void register(EvenListener evenListener)
    {
        evenListeners.add(evenListener);
    }

    private void publish()
    {
        for (EvenListener evenListener : evenListeners)
        {
//            evenListener.update();
        }
    }

}
