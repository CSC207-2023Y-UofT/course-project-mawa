package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShiftPresenter implements ActionListener {
    private int shift;
    private Page gui;
    private int employee;
    private GUIElement timeOffButton;

    public ShiftPresenter(int shift, Page gui, GUIElement timeOffButton, int employee){

            this.shift = shift;
            this.gui = gui;
            this.timeOffButton = timeOffButton;
            this.employee = employee;

    }

    public LocalDateTime getDate(){
        ShiftFileReader shiftDB = ShiftFileReader.getInstance();
        return shiftDB.getDate(shift);
    }
    public float getDuration(){
        ShiftFileReader shiftDB = ShiftFileReader.getInstance();
        return shiftDB.getDuration(shift);
    }
    public String getCoworkerString(){
        String coworkers = "";
        ShiftFileReader shiftDB = ShiftFileReader.getInstance();
        ArrayList<Integer> co = shiftDB.getCoworkers(shift);
        for (int id : co){
            if (id != employee){
                UserFileReader userDB = UserFileReader.getInstance();
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