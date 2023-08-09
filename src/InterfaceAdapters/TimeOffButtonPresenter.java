package InterfaceAdapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeOffButtonPresenter implements ActionListener {
    private GUIElement button;
    public TimeOffButtonPresenter(GUIElement button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
