package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DayView extends JFrame implements ActionListener, Page {
    private int day;
    private String weekday;
    private boolean isPayday;
    private Shift[] shifts;
    private JPanel panel;
    private ArrayList<ShiftCell> cells;
    public DayView(int day, String weekday, boolean isPayday, Shift[] shifts){
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        this.cells = new ArrayList<ShiftCell>();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        if (isPayday){
            setBackground(Color.GREEN);
        }
        repaint();
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        java.util.Arrays.sort(shifts,
                (a, b) -> ((LocalDateTime)a.getTime()).compareTo((LocalDateTime)b.getTime()));
        int[] timeRange = new int[] {Math.max(0, shifts[0].getTime().getHour() - 2),
                Math.min(24, shifts[shifts.length - 1].getTime().getHour() + 2)};
        ArrayList<Object> times = new ArrayList<Object>();
        for (int i = timeRange[0]; i <= timeRange[1]; i++){
            g2.drawString(String.valueOf(i), (float) getWidth() /14,
                    (float) ((14 * getHeight() / 15) * i) /(timeRange[1] - timeRange[0]) + (float) getHeight() /30);
            g2.draw(new Line2D.Float((float) (getWidth()) /7,
                    yCoord(i, timeRange[1] - timeRange[0]),
                    (float) (6 * getWidth()) /7,
                    yCoord(i, timeRange[1] - timeRange[0])));
        }
        for (Shift s: shifts){
            ShiftCell cell = new ShiftCell(s);
            cell.setBounds((int) ((float) getWidth() /10),
                    (int) yCoord(s.getTime().getHour() - timeRange[0] + (float)s.getTime().getMinute()/60, timeRange[1] - timeRange[0]),
                    (int) ((float) 8 * getWidth() /10),
                    (int) yCoord(s.getDuration(), timeRange[1] - timeRange[0]));
            cell.addActionListener(this);
            panel.add(cell);
        }

    }
    private float yCoord(float i, float scale){
        return (((float) (14 * getHeight()) /15) * i / scale + (float) getHeight() /30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ShiftCell){
            new ShiftView(((ShiftCell) e.getSource()).getShift());
            this.dispose();
        }
    }

    @Override
    public void addTitle() {

    }

    @Override
    public void addContent() {

    }
}
