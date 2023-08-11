package FrameworksAndDrivers;

import InterfaceAdapters.DayViewLogic;
import InterfaceAdapters.Page;
import UseCases.ShiftSorter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DayViewBackground extends JPanel {
    private DayViewLogic dvl;
    private float height, width;
    private int user;
    private ArrayList<Integer> shifts;
    private Page gui;
    private boolean painted;
    public DayViewBackground(DayViewLogic dvl, float height, float width, Page gui, int user){
        super();
        this.height = height;
        this.width = width;
        this.dvl = dvl;
        this.shifts = dvl.getShifts();
        this.gui = gui;
        this.user = user;
        this.painted = false;
        setVisible(true);
    }

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

    private float yCoord(float i, float scale){
        return (((float) (14 * height) /15) * i / scale + (float) height /30);
    }

    public void addShifts(Graphics g){
        this.shifts = dvl.getShifts();
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i = 0; i<shifts.size(); i++){

            g.drawRect((int) areas.get(i).getX(), (int) areas.get(i).getY(),
                    areas.get(i).getBounds().width, areas.get(i).getBounds().height);
            ShiftCell cell = new ShiftCell(shifts.get(i), gui, user, width);
            cell.setBounds(areas.get(i));
            this.add(cell);
        }

    }

    public void addShifts(){
        this.shifts = dvl.getShifts();
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i = 0; i<shifts.size(); i++){
            ShiftCell cell = new ShiftCell(i, gui, user, width);
            cell.setBounds(areas.get(i));
            this.add(cell);
        }

    }

    /*public void reload(){
        shifts = dvl.getShifts();
        removeAll();
        revalidate();
        repaint();
        addShifts();

    }*/



}
