package InterfaceAdapters;


import UseCases.InvalidTimeException;
import UseCases.ShiftMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * The MakeShiftFormPresenter class handles user interactions with the MakeShiftForm.
 * It implements the ActionListener interface to listen for button clicks.
 */
public class MakeShiftFormPresenter implements ActionListener {
    private GUIElement timeField, durationField;
    private GUIElement submitButton, cancelButton;
    private LocalDate date;
    private Page gui;
    private Page form;
    /**
     * Constructs a MakeShiftFormPresenter object.
     *
     * @param timeField The GUIElement representing the time input field.
     * @param durationField The GUIElement representing the duration input field.
     * @param submitButton The GUIElement representing the submit button.
     * @param cancelButton The GUIElement representing the cancel button.
     * @param date The date for which the shift is being scheduled.
     * @param gui The main page of the application (CalendarGUI).
     * @param form The form page where the shift is being scheduled.
     */
    public MakeShiftFormPresenter(GUIElement timeField, GUIElement durationField,
                                  GUIElement submitButton, GUIElement cancelButton,
                                  LocalDate date, Page gui, Page form){
        this.timeField = timeField;
        this.durationField = durationField;
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.date = date;
        this.gui = gui;
        this.form = form;
    }
    /**
     * Handles the action performed when a button is clicked in the MakeShiftForm.
     *
     * @param e The ActionEvent representing the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime time = LocalDateTime.of(date, LocalTime.parse(timeField.getContent(), formatter));
            ShiftMaker maker = new ShiftMaker(time, Float.parseFloat(durationField.getContent()));
            try {
                maker.makeShift();
                gui.update();
                submitButton.nextPage();
            } catch (InvalidTimeException ex) {
                form.update();
            }
        } else if (e.getSource() == cancelButton){
            cancelButton.nextPage();
        }
    }
}
