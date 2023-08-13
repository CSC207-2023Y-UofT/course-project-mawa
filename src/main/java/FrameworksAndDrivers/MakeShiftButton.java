package FrameworksAndDrivers;

import InterfaceAdapters.GUIElement;
import InterfaceAdapters.MakeShiftButtonPresenter;
import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * The MakeShiftButton class represents a button for scheduling a new Shift in the GUI.
 * It extends JButton and implements the GUIElement interface.
 */
public class MakeShiftButton extends JButton implements GUIElement {
    private LocalDate date;
    private Page gui;

    /**
     * Constructs a MakeShiftButton object.
     *
     * @param date The date associated with the button in the DayView context ofr HR.
     * @param gui The current GUI page.
     */
    public MakeShiftButton(LocalDate date, Page gui){
        super();
        this.date = date;
        this.gui = gui;
        setText("Schedule New Shift");
        MakeShiftButtonPresenter presenter = new MakeShiftButtonPresenter(this);
        addActionListener(presenter);
    }
    /**
     * Navigates to MakeShiftForm.
     */
    @Override
    public void nextPage() {
        new MakeShiftForm(date, gui);
    }

    @Override
    public String getContent() {
        return getText();
    }
}
