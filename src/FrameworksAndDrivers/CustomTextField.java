package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;

import javax.swing.*;

public class CustomTextField extends JTextField implements GUIElement {
    @Override
    public void nextPage() {

    }

    @Override
    public String getContent() {
        return getText();
    }
}
