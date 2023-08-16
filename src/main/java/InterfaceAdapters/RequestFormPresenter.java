package InterfaceAdapters;


import UseCases.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RequestFormPresenter class handles the actions related to the RequestForm.
 * It implements the ActionListener interface.
 */
public class RequestFormPresenter implements ActionListener {
    private GUIElement submitButton, cancelButton, reasonField;
    private int employee, shift;
    protected UserFileReader reader;
    protected NotificationBuilder nb;

    /**
     * Constructs a RequestFormPresenter object.
     *
     * @param submitButton The submit button GUIElement.
     * @param cancelButton The cancel button GUIElement.
     * @param shift The ID of the shift associated with the request.
     * @param reasonField The GUIElement for the reason input field.
     * @param employee The ID of the employee submitting the request.
     */
    public RequestFormPresenter(GUIElement submitButton, GUIElement cancelButton,
                                int shift, GUIElement reasonField,
                                int employee){
        this.submitButton = submitButton;
        this.cancelButton = cancelButton;
        this.shift= shift;
        this.reasonField = reasonField;
        this.employee = employee;
        this.reader = UserFileReader.getInstance();
        this.nb = new NotificationBuilder();
    }
    /**
     * Handles the action events triggered by the submit and cancel buttons.
     *
     * @param e The ActionEvent object representing the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            nb.createRequest(shift, reasonField.getContent(), employee, reader.getHRId());
            submitButton.nextPage();
        }else if (e.getSource() == cancelButton) {
            cancelButton.nextPage();
        }
    }
}
