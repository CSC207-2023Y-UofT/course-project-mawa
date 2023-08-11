package InterfaceAdapters;

import UseCases.NotificationFileReader;
import UseCases.ShiftFileReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimeOffButtonPresenter implements ActionListener {
    private GUIElement button;
    private int shift;
    public TimeOffButtonPresenter(GUIElement button, int shift){

        this.button = button;
        this.shift = shift;
    }

    public LocalDateTime getDate(){
        ShiftFileReader reader = ShiftFileReader.getInstance();
        return reader.getDate(shift);
    }
    public float getDuration(){
        ShiftFileReader reader = ShiftFileReader.getInstance();
        return reader.getDuration(shift);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        NotificationFileReader nreader = NotificationFileReader.getInstance();
        ArrayList<Integer> existingNotif = nreader.getIds(shift);
        if (e.getSource() == button && existingNotif.size() == 0){
            button.nextPage();
        } else if (existingNotif.size() > 0){
            button.getContent();
            System.out.println(existingNotif);
        }
    }
}
