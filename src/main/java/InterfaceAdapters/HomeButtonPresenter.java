package InterfaceAdapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The HomeButtonPresenter class handles the action of navigating to the HomePage when the button is clicked.
 * It implements the ActionListener interface.
 */
public class HomeButtonPresenter implements ActionListener {
    private Page gui;
    private GUIElement button;

    /**
     * Constructs a HomeButtonPresenter object.
     *
     * @param gui The current GUI page.
     * @param button The HomeButton triggering the action.
     */
    public HomeButtonPresenter(Page gui, GUIElement button){
        this.gui = gui;
        this.button = button;
    }

    /**
     * Performs the action of navigating to the HomePage and disposing of the current page.
     *
     * @param e The ActionEvent triggering the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            button.nextPage();
            gui.dispose();
        }
    }
}
