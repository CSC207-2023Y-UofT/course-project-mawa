package src;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class DayView extends JPanel {
    private int day;
    private String weekday;
    private boolean isPayday;
    private Shift[] shifts;
    public DayView(int day, String weekday, boolean isPayday, Shift[] shifts){
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        if (isPayday){
            setBackground(Color.GREEN);
            setOpaque(true);
        }
        repaint();

    }

    public void addTimes(){
        java.util.Arrays.sort(shifts,
                (a, b) -> ((LocalDateTime)a.getTime()).compareTo((LocalDateTime)b.getTime()));
        int[] timeRange = new int[] {Math.max(0, shifts[0].getTime().getHour() - 2),
        Math.min(24, shifts[shifts.length].getTime().getHour() + 2)};

    }

}
