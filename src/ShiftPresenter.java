import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftPresenter implements ActionListener {
    private Shift shift;
    private Page gui;
    private JButton timeOffButton;
    private int employee;

    public ShiftPresenter(Shift shift, Page gui, JButton timeOffButton, int employee){
        this.shift = shift;
        this.gui = gui;
        this.timeOffButton = timeOffButton;
        this.employee = employee;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timeOffButton){
            new RequestForm(shift.getTime(), shift.getTime().plusHours((long) shift.getDuration()), employee);
            gui.dispose();
        }
    }
}
