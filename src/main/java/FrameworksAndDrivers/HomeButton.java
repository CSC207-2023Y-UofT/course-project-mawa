
package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;
import InterfaceAdapters.HomeButtonPresenter;
import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The HomeButton class represents a button for navigating to the home page in the GUI.
 * It extends JButton and implements the GUIElement interface.
 */
public class HomeButton extends JButton implements GUIElement {
    private int userId;
    private HomeButtonPresenter presenter;

    /**
     * Constructs a HomeButton object.
     *
     * @param currPage The current page in the GUI.
     * @param userId The userId of user who is viewing GUI.
     */
    public HomeButton(Page currPage, int userId){
        super();
        presenter = new HomeButtonPresenter(currPage, this);
        this.setText("Home");
        this.addActionListener(presenter);
        this.userId = userId;

        setVisible(true);
    }

    /**
     * Navigates to HomePage.
     */
    @Override
    public void nextPage() {
        new HomePage(userId);
    }

    @Override
    public String getContent() {
        return getText();
    }
}
