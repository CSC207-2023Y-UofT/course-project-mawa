package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;
import InterfaceAdapters.Page;

import javax.swing.*;

/**
 * The CloseButton class represents a button in the GUI that, when clicked,
 * closes the associated GUI page.
 * It extends JButton and implements the GUIElement interface.
 * ## (Megan) I didn't know that you could setActionCommand, so my solution to
 * allowing presenters reference a specific element was to create a custom class
 * that implements GUIElement (DIP)
 */
public class CloseButton extends JButton implements GUIElement {
    private Page gui;
    /**
     * Constructs a CloseButton object.
     *
     * @param gui The GUI page associated with the close button.
     * @param text The text to display on the button.
     */
    public CloseButton(Page gui, String text){
        super();
        setText(text);
        this.gui = gui;
    }
    /**
     * Closes the associated GUI page when the button is clicked.
     */
    @Override
    public void nextPage() {
        gui.dispose();
    }

    /**
     * Retrieves the content of the close button as text.
     *
     * @return The content of the close button.
     */
    @Override
    public String getContent() {
        return getText();
    }
}
