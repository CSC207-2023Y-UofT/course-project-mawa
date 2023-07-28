package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ShiftView extends JFrame implements ActionListener, Page {
    private JPanel panel;
    private Shift shift;
    private JButton timeOffButton;
    public ShiftView(Shift shift){
        this.shift = shift;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addContent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void addTitle() {

    }

    @Override
    public void addContent() {
        JLabel time = new JLabel(shift.getTime().getHour() +":"+shift.getTime().getMinute());
        JLabel coworkers = new JLabel(shift.getCoworkers());
        timeOffButton = new JButton("Request Shift Off");
    }
}
