package FrameworksAndDrivers;

import InterfaceAdapters.TimeOffButtonPresenter;
import InterfaceAdapters.GUIElement;

import javax.swing.*;
import java.time.LocalDateTime;

public class TimeOffButton extends JButton implements GUIElement {
    private int shift, employee;
    private TimeOffButtonPresenter presenter;

    public TimeOffButton(int shift, int employee){
        this.shift = shift;
        this.employee = employee;
        this.presenter = new TimeOffButtonPresenter(this, shift);
        setText("Request Shift Off");
        addActionListener(presenter);
    }

    @Override
    public void nextPage() {
        LocalDateTime date = presenter.getDate();
        int hours = (int) Math.floor(presenter.getDuration());
        int mins = (int) ((presenter.getDuration() - hours) * 60);
        System.out.println(hours);
        new RequestForm(date, date.plusHours(hours).plusMinutes(mins), employee, shift);
    }

    @Override
    public String getContent() {
        JOptionPane.showMessageDialog (null,
                "You have already requested this shift off.",
                "alert", JOptionPane.ERROR_MESSAGE);
        return "Warning";
    }
}
