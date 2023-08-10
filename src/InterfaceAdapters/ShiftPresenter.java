package InterfaceAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ShiftPresenter implements ActionListener {
    private int shift;
    private Page gui;
    private int employee;
    private GUIElement timeOffButton;
    private ShiftFileReader shiftDB = new ShiftFileReader();
    private UserFileReader userDB = new UserFileReader();

    public ShiftPresenter(int shift, Page gui, GUIElement timeOffButton, int employee){

            this.shift = shift;
            this.gui = gui;
            this.timeOffButton = timeOffButton;
            this.employee = employee;

    }

    public LocalDateTime getDate(){
        return shiftDB.getDate(shift);
    }
    public float getDuration(){
        return shiftDB.getDuration(shift);
    }
    public String getCoworkerString(){
        String coworkers = "";
        for (int id : shiftDB.getEmployeeId(shift)){
            if (id != employee){
                coworkers += String.format(", %s %s", userDB.getFirstName(id),
                        userDB.getSurname(id));
            }
        }
        return coworkers.substring(2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timeOffButton){
                gui.update();
                gui.dispose();
            }
        }
    }