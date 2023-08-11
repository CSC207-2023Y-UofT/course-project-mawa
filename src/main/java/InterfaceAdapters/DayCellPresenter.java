package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.ShiftSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class DayCellPresenter implements ActionListener {
    private Page gui;
    private float width, height;
    private ArrayList<Integer> shifts;
    private boolean isPayDay;
    private ShiftFileReader reader;
    private GUIElement button;
    private String day;

    public DayCellPresenter(Page gui, GUIElement button, float width, float height, ArrayList<Integer> shifts,
                            String day){
        this.day = day;
        reader = ShiftFileReader.getInstance();
        this.width = width;
        this.height = height;
        this.gui = gui;
        this.shifts = shifts;
        this.button = button;
    }

    public ArrayList<Integer> getYcoords(){
        ArrayList<Integer> ycoords = new ArrayList<>();
        ycoords.add(0);
        for (int i:shifts) {
            LocalDateTime time = reader.getDate(i);
            int y = (int) ((time.getHour() * 60.0 + time.getMinute()) / (60.0 * 24.0) * height * 0.7);
            int x = (int) (2.6 * width / 3.0);
            y = (int) Math.max((width / 15 + ycoords.get(i - 1)), y);
            ycoords.add(y);
        }
        return ycoords;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button) && !day.equals("")){
            button.nextPage();
            gui.dispose();
        }
    }
}
