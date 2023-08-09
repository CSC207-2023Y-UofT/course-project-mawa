package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;
import InterfaceAdapters.Page;

import javax.swing.*;

public class CloseButton extends JButton implements GUIElement {
    private Page gui;
    public CloseButton(Page gui, String text){
        super();
        setText(text);
        this.gui = gui;
    }
    @Override
    public void nextPage() {
        gui.dispose();
    }

    @Override
    public String getContent() {
        return getText();
    }
}
