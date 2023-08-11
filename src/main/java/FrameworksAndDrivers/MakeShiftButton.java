package FrameworksAndDrivers;

import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MakeShiftButton extends JButton implements ActionListener {
    private LocalDate date;
    private Page gui;
    public MakeShiftButton(LocalDate date, Page gui){
        super();
        this.date = date;
        this.gui = gui;
        setText("Schedule New Shift");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            new MakeShiftForm(date, gui);
        }
    }
}
