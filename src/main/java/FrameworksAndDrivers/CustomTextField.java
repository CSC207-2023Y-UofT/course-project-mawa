package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;

import javax.swing.*;

/**
 * The CustomTextField class represents a text field in the GUI for user input.
 * It extends JTextField and implements the GUIElement interface.
 * ## (Megan) I didn't know that you could setActionCommand, so my solution to
 *  * allowing presenters reference a specific element was to create a custom class
 *  * that implements GUIElement (DIP)
 */
public class CustomTextField extends JTextField implements GUIElement {

    /**
     * This method is not implemented for a text field as it doesn't trigger navigation.
     */
    @Override
    public void nextPage() {
        // Not implemented for text field.
    }

    /**
     * Retrieves the content of the text field as text.
     *
     * @return The content of the text field.
     */
    @Override
    public String getContent() {
        return getText();
    }
}
