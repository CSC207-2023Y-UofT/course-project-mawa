package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;

import javax.swing.*;

public class CustomComboBox extends JComboBox<String> implements GUIElement {

    public CustomComboBox(String[] text){
        super(text);
    }

    @Override
    public void nextPage() {

    }

    @Override
    public String getContent() {
        return (String)getSelectedItem();
    }
}
