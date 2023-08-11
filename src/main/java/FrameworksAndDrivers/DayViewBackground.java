package FrameworksAndDrivers;

import InterfaceAdapters.DayViewLogic;
import InterfaceAdapters.Page;

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
    public DayViewBackground(DayViewLogic dvl, float height, float width,
                             ArrayList<Integer> shifts, Page gui, int user){
        super();
        this.height = height;
        this.width = width;
        this.dvl = dvl;
        this.shifts = shifts;
        this.gui = gui;
        this.user = user;
        repaint();
        addShifts();
        setVisible(true);
    }

    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        ArrayList<Integer> timeRange = dvl.getHours();
        float increment = (float) (14 * height / 15) /(timeRange.get(timeRange.size() - 1) - timeRange.get(0));
        for (int y : timeRange){
            int i = y - timeRange.get(0) + 1;
            g2.drawString(String.valueOf(y), (int) (width /14),
                    (int) (increment * i + height /30));
            g2.drawLine((int) ( (width) /7),
                    (int) yCoord(i, timeRange.get(timeRange.size() - 1) - timeRange.get(0)),
                    (int) ( (6 * width) /7),
                    (int) yCoord(i, timeRange.get(timeRange.size() - 1) - timeRange.get(0)));
        }

    }

    private float yCoord(float i, float scale){
        return (((float) (14 * height) /15) * i / scale + (float) height /30);
    }

    public void addShifts(Graphics g){
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i = 0; i<shifts.size(); i++){
            ShiftCell cell = new ShiftCell(i, gui, user);
            cell.setBounds(areas.get(i));
            this.add(cell);
            g.drawRect((int) areas.get(i).getX(), (int) areas.get(i).getY(),
                    areas.get(i).getBounds().width, areas.get(i).getBounds().height);
        }
    }

    public void addShifts(){
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i = 0; i<shifts.size(); i++){
            ShiftCell cell = new ShiftCell(i, gui, user);
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
