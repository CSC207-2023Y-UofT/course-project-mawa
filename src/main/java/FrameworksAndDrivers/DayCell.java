package FrameworksAndDrivers;

import InterfaceAdapters.*;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DayCell extends JButton implements GUIElement{
    private LocalDate day;
    private String weekday;
    private boolean isPayday;
    private ArrayList<Integer> shifts;
    private boolean isSelected = false;
    private DayCellPresenter presenter;
    private int user;

    public DayCell(Page gui, LocalDate day, String weekday, boolean isPayday, ArrayList<Integer> shifts, int user){
        super();
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        this.user = user;
        this.setText(String.valueOf(day));
        if (isPayday){
            setBackground(Color.GREEN);
            setOpaque(true);
        }
        this.presenter = new DayCellPresenter(gui, this, getWidth(), getHeight(), shifts);
        repaint();

    }

    public void paintComponent(Graphics g1) {
        if (day == null){
            return;
        }
        Graphics2D g = (Graphics2D) g1.create();
        super.paintComponent(g);
        int x = (int)(2.6 * getWidth() / 3.0);
        ArrayList<Integer> ycoords = presenter.getYcoords();
        for (int y:ycoords) {
            g.setColor(Color.RED);
            g.fillOval(x , y, getWidth() / 15, getWidth() / 15);
            g.drawOval(x, y, getWidth() / 15, getWidth() / 15);
        }
        g.dispose();

    }


    public void setSelected(boolean sel){
        this.isSelected = sel;
    }
    public boolean getSelected(){
        return isSelected;
    }
    public LocalDate getDay(){
        return day;
    }
    public void setDay(LocalDate day){
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
    public ArrayList<Integer> getShifts(){
        return shifts;
    }
    public void setShifts(ArrayList<Integer> shifts){
        this.shifts = shifts;
    }

    @Override
    public void nextPage() {
        new DayView(day, weekday, isPayday, shifts, user);
    }

    @Override
    public String getContent() {
        return this.getText();
    }
}
