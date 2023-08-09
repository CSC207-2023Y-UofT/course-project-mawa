package FrameworksAndDrivers;

import InterfaceAdapters.FileNameConstants;
import InterfaceAdapters.GUIElement;
import InterfaceAdapters.Page;
import InterfaceAdapters.ShiftFileReader;

import javax.swing.*;
import java.time.LocalDateTime;

public class TimeOffButton extends JButton implements GUIElement {
    private int shift, employee;
    private ShiftFileReader reader;

    public TimeOffButton(int shift, int employee){
        this.shift = shift;
        this.employee = employee;
        try{
            this.reader = new ShiftFileReader(FileNameConstants.SHIFT_FILE_NAME);
        }catch(Exception e){
            System.out.println("Invalid File Name.");
        }
        setText("Request Shift Off");
        repaint();
    }

    @Override
    public void nextPage() {
        LocalDateTime date = reader.getDate(shift);
        new RequestForm(date, date.plusHours((long) reader.getDuration(shift)), employee, shift);
    }

    @Override
    public String getContent() {
        return getText();
    }
}
