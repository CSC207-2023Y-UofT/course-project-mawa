package InterfaceAdapters;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timeOffButton){
            gui.update();
            gui.dispose();
        }
    }
}