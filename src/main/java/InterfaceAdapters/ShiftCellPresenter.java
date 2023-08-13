package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;
import UseCases.UserTypeConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

/**
 * The ShiftCellPresenter class handles actions for ShiftCell buttons.
 * It implements the ActionListener interface.
 */
public class ShiftCellPresenter implements ActionListener {
    private GUIElement button;
    private int shift, user;

    /**
     * Constructs a ShiftCellPresenter object.
     *
     * @param button The GUIElement button (the ShiftCell).
     * @param shift The ID of the shift.
     * @param user The ID of the user.
     */
    public ShiftCellPresenter(GUIElement button, int shift, int user) {
        this.shift = shift;
        this.user = user;
        this.button = button;
    }

    /**
     * Checks if the user is of type HR.
     *
     * @return True if the user is HR, false otherwise.
     */
    public boolean isHR() {
        UserFileReader reader = UserFileReader.getInstance();
        return reader.getType(user).equals(UserTypeConstants.HR);
    }

    /**
     * Gets a formatted string representation of the shift start time.
     *
     * @return The formatted string representation of the shift time.
     */
    public String getString(){
        ShiftFileReader reader = ShiftFileReader.getInstance();
        return reader.getDate(shift).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    /**
     * Handles when the ShiftCell gets clicked.
     *
     * @param e The ActionEvent object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
