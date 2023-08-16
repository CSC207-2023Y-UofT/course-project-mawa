package InterfaceAdapters;

import UseCases.ShiftFileReader;
import UseCases.DayCellModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The DayCellPresenter class handles the presentation logic for a day cell in the CalendarGUI.
 * It implements the ActionListener interface to respond to DayCell click events.
 */

public class DayCellPresenter implements ActionListener {
    private Page gui;
    public float width, height;
    private ArrayList<Integer> shifts;
    private GUIElement button;
    public ShiftFileReader reader = ShiftFileReader.getInstance();

    /**
     * Constructs a new DayCellPresenter.
     *
     * @param gui The CalendarGUI page associated with the DayCell.
     * @param button The GUIElement button (DayCell) associated with this presenter.
     * @param width The width of the DayCell.
     * @param height The height of the DayCell.
     * @param shifts The list of shifts associated with the DayCell's day.
     */

    public DayCellPresenter(Page gui, GUIElement button, float width, float height, ArrayList<Integer> shifts){
        this.width = width;
        this.height = height;
        this.gui = gui;
        this.shifts = shifts;
        this.button = button;
    }
    /**
     * Calculates and returns the Y coordinates for rendering red dots that represent shifts
     * in the DayCell.
     *
     * @return The list of Y coordinates for rendering shift dots.
     */
    public ArrayList<Integer> getYcoords(){
        ArrayList<Integer> ycoords = new ArrayList<>();
        ycoords.add(0);
        for (int i = 1; i< shifts.size()+1; i++) {
            LocalDateTime time = reader.getDate(i);
            int y = DayCellModel.getYcoord(time.getHour(), time.getMinute(), height, ycoords.get(i -1));
            ycoords.add(y);
        }
        ycoords.remove(0);
        return ycoords;
    }

    /**
     * Calculates and returns the X coordinate for rendering the shift dot
     * painted on DayCell.
     *
     * @return The X coordinate for rendering the shift dot.
     */
    public int getXcoord(){
        return DayCellModel.getXcoord(width);
    }

    /**
     * Handles the click of the associated DayCell.
     * Advances to the DayView and disposes of the CalendarGUI.
     *
     * @param e The ActionEvent object representing the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button)){
            button.nextPage();
            gui.dispose();
        }
    }
}
