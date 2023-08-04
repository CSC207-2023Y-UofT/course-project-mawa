package InterfaceAdapters;

import Entities.UserNotificationRequest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.*;
import InterfaceAdapters.*;

public class RequestFormPresenter implements ActionListener {

    private Page gui;
    private JButton submitButton, cancelButton;
    private JTextField startField, endField, reasonField;
    private int employee;
    public RequestFormPresenter(Page gui, JButton submitButton, JButton cancelButton,
                                JTextField startField, JTextField endField, JTextField reasonField,
                                int employee){
        this.gui = gui;
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.startField = startField;
        this.endField = endField;
        this.reasonField = reasonField;
        this.employee = employee;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            try {
                UserNotificationRequest notif = NotificationBuilder.newNotificationRequest(startField.getText(),
                        endField.getText(), reasonField.getText(), employee);
                gui.dispose();
            } catch (InvalidTimeException ex){
                JOptionPane.showMessageDialog (submitButton, "Invalid Time Range Entered",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == cancelButton) {
            gui.dispose();
        }
    }
}
