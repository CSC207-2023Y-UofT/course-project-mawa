package InterfaceAdapters;

import UseCases.NotificationFileReader;
import UseCases.ShiftFileReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * The TimeOffButtonPresenter class handles the action associated with the TimeOffButton.
 * It implements the ActionListener interface.
 */
public class TimeOffButtonPresenter implements ActionListener {
    private GUIElement button;
    private int shift;
    protected ShiftFileReader shiftReader;
    protected NotificationFileReader notifReader;

    /**
     * Constructs a TimeOffButtonPresenter object.
     *
     * @param button The GUIElement representing the TimeOffButton.
     * @param shift The ID of the shift.
     */
    public TimeOffButtonPresenter(GUIElement button, int shift){

        this.button = button;
        this.shift = shift;
        this.shiftReader = ShiftFileReader.getInstance();
        this.notifReader = NotificationFileReader.getInstance();
    }

    public LocalDateTime getDate(){
        return shiftReader.getDate(shift);
    }
    public float getDuration(){
        return shiftReader.getDuration(shift);
    }
    /**
     * Handles the action performed by the TimeOffButton.
     * Checks for existing notifications and triggers the appropriate action.
     *
     * @param e The ActionEvent that triggered the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> existingNotif = notifReader.getIds(shift);
        if (e.getSource() == button && existingNotif.size() == 0){
            button.nextPage();
        } else if (existingNotif.size() > 0){
            button.getContent();
        }
    }
}
