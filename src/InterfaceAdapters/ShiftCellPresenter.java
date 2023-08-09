package InterfaceAdapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftCellPresenter implements ActionListener {
    private GUIElement button;

    public ShiftCellPresenter(GUIElement button){
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
