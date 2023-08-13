package InterfaceAdapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MakeShiftButtonPresenter class handles the action of scheduling a new shift when the button is clicked.
 * It implements the ActionListener interface.
 */
public class MakeShiftButtonPresenter implements ActionListener {
    private GUIElement button;

    /**
     * Constructs a MakeShiftButtonPresenter object.
     *
     * @param button The GUIElement button triggering the action.
     */
    public MakeShiftButtonPresenter(GUIElement button){
        this.button = button;
    }
    /**
     * Performs the action of navigating to the corresponding MakeShiftForm.
     *
     * @param e The ActionEvent triggering the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
