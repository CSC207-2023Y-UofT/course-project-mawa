package InterfaceAdapters;
/**
 * The GUIElement interface defines methods that represent interactive elements (like buttons, and drop down menus)
 * in a GUI.
 * Mainly used for the calendar.
 */
public interface GUIElement {
    /**
     * Advances to the next page or state in the GUI.
     */
    public void nextPage();
    /**
     * Retrieves the content or value associated with the GUI element.
     *
     * @return The content or value of the GUI element.
     */
    public String getContent();
}
