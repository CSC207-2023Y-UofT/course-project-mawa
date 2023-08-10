package InterfaceAdapters;


import Entities.NotificationRequest;

import UseCases.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestFormPresenter implements ActionListener {

    private GUIElement submitButton, cancelButton, reasonField;
    private int employee, shift;
    private ShiftFileReader reader;
    public RequestFormPresenter(GUIElement submitButton, GUIElement cancelButton,
                                int shift, GUIElement reasonField,
                                int employee){
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.shift= shift;
        this.reasonField = reasonField;
        this.employee = employee;
        reader = new ShiftFileReader(FileNameConstants.SHIFT_FILE_NAME);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            UserNotificationRequest notif =
                    NotificationBuilder.newNotificationRequest(shift, reasonField.getContent(), employee);
            submitButton.nextPage();
        }else if (e.getSource() == cancelButton) {
            cancelButton.nextPage();
        }
    }
}
