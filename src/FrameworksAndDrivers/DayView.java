package FrameworksAndDrivers;

import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class DayView extends JFrame implements Page {
    private LocalDate day;
    private String weekday;
    private boolean isPayday;
    private ArrayList<Integer> shifts;
    private JPanel panel;
    private ArrayList<ShiftCell> cells;
    private int user;
    private DayViewLogic dvl;
    public DayView(LocalDate day, String weekday, boolean isPayday, ArrayList<Integer> shifts, int user){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        this.cells = new ArrayList<ShiftCell>();
        this.dvl = new DayViewLogic(shifts, getWidth(), getHeight());
        setUser(user);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (isPayday){
            setBackground(Color.GREEN);
        }
        panel = new JPanel();
        panel.setLayout(null);
        addTitle();
        addHomeButton();
        addContent();
        getContentPane().add(panel);
        repaint();
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int[] timeRange = dvl.getTimeRange();
        for (int i : timeRange){
            g2.drawString(String.valueOf(i), (float) getWidth() /14,
                    (float) ((14 * getHeight() / 15) * i) /(timeRange[1] - timeRange[0]) + (float) getHeight() /30);
            g2.draw(new Line2D.Float((float) (getWidth()) /7,
                    yCoord(i, timeRange[1] - timeRange[0]),
                    (float) (6 * getWidth()) /7,
                    yCoord(i, timeRange[1] - timeRange[0])));
        }
        g2.dispose();

    }
    private float yCoord(float i, float scale){
        return (((float) (14 * getHeight()) /15) * i / scale + (float) getHeight() /30);
    }

    @Override
    public void addTitle() {
        JLabel title = new JLabel(day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        title.setFont(new Font("Serif", Font.PLAIN, getHeight()/8));
        Dimension size = title.getPreferredSize();
        title.setBounds(getWidth() - size.width / 2, getHeight() - size.height / 2,
                size.width, size.height);
        panel.add(title);
    }

    @Override
    public void addContent() {
        ArrayList<Rectangle> areas = dvl.getShiftCellPosition();
        for (int i:shifts){
            ShiftCell cell = new ShiftCell(i, this, user);
            cell.setBounds(areas.get(i));
            panel.add(cell);
        }
    }

    @Override
    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, user);
        Dimension size = hb.getPreferredSize();
        hb.setBounds(14 * getWidth() / 15 - size.width, getHeight() / 15 + size.height,
                size.width, size.height);
        panel.add(hb);
    }

    @Override
    public void update() {

    }
}
