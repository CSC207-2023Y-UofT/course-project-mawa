package InterfaceAdapters;


import UseCases.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RequestFormPresenter implements ActionListener {

    private GUIElement submitButton, cancelButton, reasonField;
    private int employee, shift;
    public RequestFormPresenter(GUIElement submitButton, GUIElement cancelButton,
                                int shift, GUIElement reasonField,
                                int employee){
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.shift= shift;
        this.reasonField = reasonField;
        this.employee = employee;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton){
            NotificationBuilder nb = new NotificationBuilder();
            UserFileReader ufr = UserFileReader.getInstance();
            nb.createRequest(shift, reasonField.getContent(), employee, ufr.getHRId());
            submitButton.nextPage();
        }else if (e.getSource() == cancelButton) {
            cancelButton.nextPage();
        }
    }
}
