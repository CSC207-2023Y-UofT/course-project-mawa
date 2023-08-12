package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;

import javax.swing.*;

/**
 * The CustomComboBox class represents a custom combo box in the GUI that allows selecting an item from a list.
 * It extends JComboBox and implements the GUIElement interface.
 *
 * @param <E> The type of elements in the combo box.
 *
 * ## (Megan) I didn't know that you could setActionCommand, so my solution to
 *  * allowing presenters reference a specific element was to create a custom class
 *  * that implements GUIElement (DIP)
 */
public class CustomComboBox<E> extends JComboBox<E> implements GUIElement {

    /**
     * Constructs a CustomComboBox object with the provided array of items.
     *
     * @param items An array of items to populate the combo box.
     */
    public CustomComboBox(E[] items){
        super(items);
    }

    /**
     * This method is not implemented for a combo box as it doesn't trigger navigation
     * by itself.
     */
    @Override
    public void nextPage() {
        // Not implemented for comboBox.
    }

    /**
     * Retrieves the selected content from the combo box as text.
     *
     * @return The selected content from the combo box.
     */
    @Override
    public String getContent() {
        return String.valueOf(getSelectedItem());
    }
}
