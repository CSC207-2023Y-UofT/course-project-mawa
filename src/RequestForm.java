package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class RequestForm extends JFrame implements ActionListener, Page {
    private Employee employee;
    private LocalDateTime time1, time2;
    private JTextField startField, endField;
    private JButton submitButton;

    public RequestForm(LocalDateTime t1, LocalDateTime t2, Employee employee){
        this.employee = employee;
        this.time1 = t1;
        this.time2 = t2;
        this.submitButton = new JButton("Submit Time Off Request");
        this.submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void addTitle() {

    }

    @Override
    public void addContent() {

    }
}
