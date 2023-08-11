package InterfaceAdapters;

import FrameworksAndDrivers.CustomTextField;
import UseCases.ShiftInteractor;
import UseCases.ShiftMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MakeShiftFormPresenter implements ActionListener {
    private GUIElement timeField, durationField;
    private GUIElement submitButton, cancelButton;
    private LocalDate date;
    private Page gui;
    public MakeShiftFormPresenter(GUIElement timeField, GUIElement durationField,
                                  GUIElement submitButton, GUIElement cancelButton,
                                  LocalDate date, Page gui){
        this.timeField = timeField;
        this.durationField = durationField;
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.date = date;
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime time = LocalDateTime.of(date, LocalTime.parse(timeField.getContent(), formatter));
            ShiftMaker maker = new ShiftMaker(time, Float.parseFloat(durationField.getContent()));
            maker.makeShift();
            gui.update();
            submitButton.nextPage();
        } else if (e.getSource() == cancelButton){
            cancelButton.nextPage();
        }
    }
}
