package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The ShiftPresenter class handles the presentation logic for displaying shift details.
 * It implements the ActionListener interface.
 */
public class ShiftPresenter implements ActionListener {
    private int shift;
    private int employee;
    private GUIElement closeButton;
    protected ShiftFileReader shiftDB;
    protected UserFileReader userDB;

    /**
     * Constructs a ShiftPresenter object.
     *
     * @param shift The ID of the shift.
     * @param closeButton The GUIElement representing the close button.
     * @param employee The ID of the user associated with the shift.
     */
    public ShiftPresenter(int shift, GUIElement closeButton, int employee){
            this.shift = shift;
            this.closeButton = closeButton;
            this.employee = employee;
            this.shiftDB = ShiftFileReader.getInstance();
            this.userDB= UserFileReader.getInstance();
    }

    public LocalDateTime getDate(){
        return shiftDB.getDate(shift);
    }
    public float getDuration(){
        return shiftDB.getDuration(shift);
    }
    /**
     * Generates a string representing coworkers for the shift (excluding the
     * employee viewing shift).
     *
     * @return A formatted string containing the names of coworkers for the shift.
     */
    public String getCoworkerString(){
        String coworkers = "";
        ArrayList<Integer> co = shiftDB.getCoworkers(shift);
        for (int id : co){
            if (id != employee){
                coworkers += String.format(", %s %s", userDB.getFirstName(id),
                        userDB.getSurname(id));
            }
        }
        if (co.size() > 1){
            return coworkers.substring(2);
        }
        return "No coworkers";
    }

    /**
     * Handles action event from the closeButton GUIElement.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton){
            closeButton.nextPage();
        }
    }
}