package FrameworksAndDrivers;

import InterfaceAdapters.*;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The DayCell class represents a cell in the CalendarGUI, displaying information about a specific day.
 * It extends JButton and implements the GUIElement interface (so it can be referenced by its presenter via DIP).
 */
public class DayCell extends JButton implements GUIElement{
    private LocalDate day;
    private String weekday;
    private boolean isPayday;
    private ArrayList<Integer> shifts;
    private DayCellPresenter presenter;
    private int user;

    /**
     * Constructs a DayCell object.
     *
     * @param gui The CalendarGUI page associated with the DayCell.
     * @param day The LocalDate representing the day displayed in the cell.
     * @param weekday The weekday label for the day cell.
     * @param isPayday A flag indicating whether it's a payday.
     * @param shifts The list of shift data associated with the DayCell's day.
     * @param user The user viewing the DayCell.
     */
    public DayCell(Page gui, LocalDate day, String weekday, boolean isPayday, ArrayList<Integer> shifts, int user){
        super();
        super.repaint();
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        this.user = user;
        if (!weekday.equals("")) {
            this.setText(String.valueOf(day.getDayOfMonth()));
        }
        if (isPayday){
            setBackground(Color.GREEN);
            setOpaque(true);
        }
        this.presenter = new DayCellPresenter(gui, this, 160, 80, shifts);
        this.addActionListener(presenter);
        repaint();

    }

    /**
     * Paints the component, rendering the DayCell's colour and associated shift dots.
     *
     * @param g1 The Graphics object used for painting.
     */
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        if (weekday.equals("")){
            setBackground(Color.GRAY);
            setOpaque(true);
            return;
        }
        int x = presenter.getXcoord();
        ArrayList<Integer> ycoords = presenter.getYcoords();
        for (int y:ycoords) {
                g1.setColor(Color.RED);
                g1.fillOval(x , y, 80 / 15, 80 / 15);
                g1.drawOval(x, y, 80 / 15, 80 / 15);
        }

    }

    /**
     * Retrieves the list of shifts associated with the DayCell.
     *
     * @return The list of shifts.
     */
    public ArrayList<Integer> getShifts(){
        return shifts;
    }

    /**
     * Sets the list of shifts associated with the DayCell.
     *
     * @param shifts The list of shifts to set.
     */
    public void setShifts(ArrayList<Integer> shifts){
        this.shifts = shifts;
    }

    /**
     * Handles advancing to the next page.
     * Opens a new DayView with relevant information.
     */
    @Override
    public void nextPage() {
        new DayView(day, weekday, isPayday, shifts, user);
    }

    /**
     * Retrieves the content of the day cell (day of the month) as text.
     *
     * @return The day of the month.
     */
    @Override
    public String getContent() {
        return this.getText();
    }
}
