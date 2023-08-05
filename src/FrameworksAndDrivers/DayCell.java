package FrameworksAndDrivers;

import InterfaceAdapters.*;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DayCell extends JButton{
    private int day;
    private String weekday;
    private boolean isPayday;
    private Shift[] shifts;
    private boolean isSelected = false;

    public DayCell(int day, String weekday, boolean isPayday, Shift[] shifts){
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        this.setText(String.valueOf(day));
        if (isPayday){
            setBackground(Color.GREEN);
            setOpaque(true);
        }
        repaint();

    }

    public void paintComponent(Graphics g1) {
        if (weekday == ""){
            return;
        }
        Graphics2D g = (Graphics2D) g1.create();
        super.paintComponent(g);
        Arrays.sort(shifts, new SortShiftByDate());
        int[] ycoords = new int[shifts.length + 1];
        ycoords[0] = 0;
        for (int i = 1; i < (shifts.length + 1); i++) {
            LocalDateTime time = ((LocalDateTime) shifts[i - 1].getTime());
            int y = (int) ((time.getHour() * 60.0 + time.getMinute()) / (60.0 * 24.0) * getHeight() * 0.7);
            //y += getHeight() / 9.0;
            int x = (int)(2.6 * getWidth() / 3.0);
            y = java.lang.Math.max((int)(getWidth() / 15 + ycoords[i-1]), y);
            ycoords[i] = y;
            g.setColor(Color.RED);
            g.fillOval(x , y, getWidth() / 15, getWidth() / 15);
            g.drawOval(x, y, getWidth() / 15, getWidth() / 15);
            g.setPaint(Color.BLACK);
            g.drawString(time.format(DateTimeFormatter.ofPattern("HH:mm")), x + getWidth() / 60,
                    y + getWidth() / 25);
        }
        g.dispose();

    }


    public void setSelected(boolean sel){
        this.isSelected = sel;
    }
    public boolean getSelected(){
        return isSelected;
    }
    public int getDay(){
        return day;
    }
    public void setDay(int day){
        this.day = day;
    }
    public String getWeekday(){
        return weekday;
    }
    public void setWeekday(String weekday){
        this.weekday = weekday;
    }
    public boolean getPayday(){
        return isPayday;
    }
    public void setPayday(boolean pd){
        this.isPayday = pd;
    }
    public Shift[] getShifts(){
        return shifts;
    }
    public void setShifts(Shift[] shifts){
        this.shifts = shifts;
    }
}
