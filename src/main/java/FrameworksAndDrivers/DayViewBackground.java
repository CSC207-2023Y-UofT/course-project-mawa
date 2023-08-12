package FrameworksAndDrivers;

import InterfaceAdapters.DayViewLogic;
import InterfaceAdapters.Page;
import UseCases.ShiftSorter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * The DayViewBackground class represents the "background" (not an accurate name,
 * but I'm too tired to change it) panel for the DayView, displaying time lines and shifts.
 * It extends JPanel.
 */
public class DayViewBackground extends JPanel {
    private DayViewLogic dvl;
    private float height, width;
    private int user;
    private ArrayList<Integer> shifts;
    private boolean painted;

    /**
     * Constructs a DayViewBackground object.
     *
     * @param dvl The DayViewLogic associated with the overall DayView.
     * @param height The height of the render area.
     * @param width The width of the render area.
     * @param user The user viewing the panel.
     */
    public DayViewBackground(DayViewLogic dvl, float height, float width, int user){
        super();
        this.height = height;
        this.width = width;
        this.dvl = dvl;
        this.shifts = dvl.getShifts();
        this.user = user;
        this.painted = false;
        setVisible(true);
    }
    /**
     * Paints the background panel, including time slots and shifts.
     *
     * @param g2 The Graphics object used for painting.
     */
    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        if (!painted) {
            ArrayList<Integer> timeRange = dvl.getHours();
            float increment = (float) (14 * height / 15) / (timeRange.get(timeRange.size() - 1) - timeRange.get(0));
            for (int y : timeRange) {
                int i = y - timeRange.get(0) + 1;
                g2.drawString(String.valueOf(y), (int) (width / 14),
                        (int) (increment * i + height / 30));
                g2.drawLine((int) ((width) / 7),
                        (int) yCoord(i, timeRange.get(timeRange.size() - 1) - timeRange.get(0)),
                        (int) ((6 * width) / 7),
                        (int) yCoord(i, timeRange.get(timeRange.size() - 1) - timeRange.get(0)));
            }
            addShifts(g2);
            painted = true;
        }

    }

    /**
     * Calculates the Y coordinate based on scale and index.
     *
     * @param i The index.
     * @param scale The scale (range of indicies).
     * @return The calculated Y coordinate.
     */
    private float yCoord(float i, float scale){
        return (((float) (14 * height) /15) * i / scale + (float) height /30);
    }

    /**
     * Adds ShiftCells to this.
     *
     * @param g The Graphics context used for painting.
     */
    public void addShifts(Graphics g){
        this.shifts = dvl.getShifts();
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i = 0; i<shifts.size(); i++){

            g.drawRect((int) areas.get(i).getX(), (int) areas.get(i).getY(),
                    areas.get(i).getBounds().width, areas.get(i).getBounds().height);
            ShiftCell cell = new ShiftCell(shifts.get(i), user);
            cell.setBounds(areas.get(i));
            this.add(cell);
        }

    }
}
