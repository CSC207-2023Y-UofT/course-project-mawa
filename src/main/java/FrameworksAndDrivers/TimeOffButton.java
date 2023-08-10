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
        repaint();
    }

    @Override
    public void nextPage() {
        LocalDateTime date = presenter.getDate();
        new RequestForm(date, date.plusHours((long) presenter.getDuration()), employee, shift);
    }

    @Override
    public String getContent() {
        return getText();
    }
}
