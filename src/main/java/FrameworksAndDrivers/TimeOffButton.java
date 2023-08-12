package FrameworksAndDrivers;

import InterfaceAdapters.TimeOffButtonPresenter;
import InterfaceAdapters.GUIElement;

import javax.swing.*;
import java.time.LocalDateTime;
/**
 * The TimeOffButton class represents a button leading to requesting time off for a shift.
 * It extends JButton and implements the GUIElement interface.
 */
public class TimeOffButton extends JButton implements GUIElement {
    private int shift, employee;
    private TimeOffButtonPresenter presenter;
    /**
     * Constructs a TimeOffButton object.
     *
     * @param shift The ID of the shift.
     * @param employee The ID of the user associated with the shift.
     */
    public TimeOffButton(int shift, int employee){
        this.shift = shift;
        this.employee = employee;
        this.presenter = new TimeOffButtonPresenter(this, shift);
        setText("Request Shift Off");
        addActionListener(presenter);
    }

    /**
     * Handles the action of moving to the next page (requesting time off).
     */
    @Override
    public void nextPage() {
        LocalDateTime date = presenter.getDate();
        int hours = (int) Math.floor(presenter.getDuration());
        int mins = (int) ((presenter.getDuration() - hours) * 60);
        new RequestForm(date, date.plusHours(hours).plusMinutes(mins), employee, shift);
    }

    /**
     * Retrieves a warning if the shift has already been requested off of the GUI element.
     *
     * @return displays a warning message.
     */
    @Override
    public String getContent() {
        JOptionPane.showMessageDialog (null,
                "You have already requested this shift off.",
                "alert", JOptionPane.ERROR_MESSAGE);
        return "Warning";
    }
}
