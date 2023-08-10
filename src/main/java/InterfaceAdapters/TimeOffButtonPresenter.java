package InterfaceAdapters;

import UseCases.ShiftFileReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TimeOffButtonPresenter implements ActionListener {
    private GUIElement button;
    private int shift;
    private ShiftFileReader reader = ShiftFileReader.getInstance();
    public TimeOffButtonPresenter(GUIElement button, int shift){

        this.button = button;
        this.shift = shift;
    }

    public LocalDateTime getDate(){
        return reader.getDate(shift);
    }
    public float getDuration(){
        return reader.getDuration(shift);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            button.nextPage();
        }
    }
}
