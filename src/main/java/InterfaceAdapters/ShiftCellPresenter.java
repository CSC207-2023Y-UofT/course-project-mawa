package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.UserFileReader;
import UseCases.UserTypeConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ShiftCellPresenter implements ActionListener {
    private GUIElement button;
    private int shift, user;

    public ShiftCellPresenter(GUIElement button, int shift, int user) {
        this.shift = shift;
        this.user = user;
        this.button = button;
    }

    public boolean isHR(){
        UserFileReader reader = UserFileReader.getInstance();
        return reader.getType(user).equals(UserTypeConstants.HR);
    }

    public String getString(){
        ShiftFileReader reader = ShiftFileReader.getInstance();
        return reader.getDate(shift).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
