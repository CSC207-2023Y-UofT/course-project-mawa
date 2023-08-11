package FrameworksAndDrivers;

import InterfaceAdapters.*;

import InterfaceAdapters.Page;



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
    private int user;
    private DayViewLogic dvl;
    private JPanel titlePanel;
    private Dimension screenSize;
    private  DayViewBackground dvb;
    public DayView(LocalDate day, String weekday, boolean isPayday, ArrayList<Integer> shifts, int user){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        this.shifts = shifts;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.dvl = new DayViewLogic(shifts, screenSize.width, (float) (21 * screenSize.height) /30,
                user, day);
        setUser(user);
        this.shifts = dvl.getShifts();
        dvb = new DayViewBackground(dvl, (float) (21 * screenSize.height) /30,
                screenSize.width, this, user);
        panel = new JPanel(new BorderLayout());
        panel.add(dvb, BorderLayout.CENTER);
        titlePanel = new JPanel(new FlowLayout());
        addTitle();
        addHomeButton();
        addContent();
        setContentPane(panel);
        this.shifts = dvl.getShifts();
        setVisible(true);
    }

    @Override
    public void addTitle() {
        if (dvl.isHR()){
            titlePanel.add(new MakeShiftButton(day, this));
        }
        JLabel title = new JLabel(day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        titlePanel.add(title);
    }

    @Override
    public void addContent() {

    }

    @Override
    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, user);
        titlePanel.add(hb);
        panel.add(titlePanel, BorderLayout.PAGE_START);
    }

    @Override
    public void update() {
        dvl.update();
        new DayView(day, weekday,isPayday, dvl.getShifts(), user);
        dispose();

    }

    public static void main(String[] args){

        new DayView(LocalDate.now(),"Fri", true,
                new ArrayList<Integer>(),1);
    }
}
