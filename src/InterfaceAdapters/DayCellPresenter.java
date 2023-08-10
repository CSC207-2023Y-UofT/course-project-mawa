package InterfaceAdapters;

import UseCases.FileNameConstants;
import UseCases.ShiftSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DayCellPresenter implements ActionListener {
    private Page gui;
    private float width, height;
    private ArrayList<Integer> shifts;
    private boolean isPayDay;
    private ShiftFileReader reader;
    private GUIElement button;

    public DayCellPresenter(Page gui, GUIElement button, float width, float height, ArrayList<Integer> shifts){
        reader = new ShiftFileReader(FileNameConstants.SHIFT_FILE_NAME);
        this.width = width;
        this.height = height;
        this.gui = gui;
        this.shifts = shifts;
        this.button = button;
    }

    public ArrayList<Integer> getYcoords(){
        ArrayList<Integer> sortedShifts = ShiftSorter.sortShiftsByDate(shifts);
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
        button.nextPage();
        gui.dispose();
    }
}
