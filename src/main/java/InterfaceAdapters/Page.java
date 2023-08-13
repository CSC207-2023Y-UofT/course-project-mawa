
package InterfaceAdapters;
/**
 * The Page interface defines methods for creating and managing a graphical user interface (GUI) page.
 * Implementations of this interface are responsible for adding various components and handling interactions.
 */
public interface Page {
    /**
     * Adds a title to the GUI page.
     */
    void addTitle();

    /**
     * Adds content or elements to the GUI page.
     */
    void addContent();

    /**
     * Sets the user associated with the GUI page.
     *
     * @param user The identifier of the user.
     */
    void setUser(int user);
    /**
     * Disposes of the GUI page, releasing associated resources.
     */
    void dispose();

    /**
     * Adds a home button to the GUI page.
     */
    void addHomeButton();

    /**
     * Updates the GUI page with new information or changes.
     */
    void update();

    //public void addFwdBackRefreshButton(); Something we would like to implement in the future
}
