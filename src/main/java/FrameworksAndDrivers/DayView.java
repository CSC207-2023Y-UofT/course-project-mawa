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

/**
 * The DayView class shows a schedule view of all the relevant shifts in a day.
 * It extends JFrame and implements the Page interface.
 */
public class DayView extends JFrame implements Page {
    private LocalDate day;
    private String weekday;
    private boolean isPayday;
    private JPanel panel;
    private int user;
    private DayViewLogic dvl;
    private JPanel titlePanel;
    private Dimension screenSize;
    private  DayViewBackground dvb;

    /**
     * Constructs a DayView object for a specific day.
     *
     * @param day The LocalDate representing the day to display.
     * @param weekday The weekday label for the day.
     * @param isPayday A flag indicating whether it's a payday.
     * @param shifts The list of shift data associated with the day.
     * @param user The user associated with the view.
     */
    public DayView(LocalDate day, String weekday, boolean isPayday, ArrayList<Integer> shifts, int user){
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.day = day;
        this.weekday = weekday;
        this.isPayday = isPayday;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.dvl = new DayViewLogic(shifts, screenSize.width, (float) (21 * screenSize.height) /30,
                user, day);
        setUser(user);
        dvb = new DayViewBackground(dvl, (float) (21 * screenSize.height) /30,
                screenSize.width, user);
        panel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        addTitle();
        addHomeButton();
        addContent();
        setContentPane(panel);
        setVisible(true);
    }
    /**
     * Adds the title for the DayView.
     */
    @Override
    public void addTitle() {
        if (dvl.isHR()){
            titlePanel.add(new MakeShiftButton(day, this));
        }
        JLabel title = new JLabel(day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        titlePanel.add(title);
    }
    /**
     * Adds the content of the DayView.
     */
    @Override
    public void addContent() {
        panel.add(dvb, BorderLayout.CENTER);
    }

    /**
     * Sets the user associated with the view.
     *
     * @param user The user to set association with.
     */
    @Override
    public void setUser(int user) {
        this.user = user;
    }

    /**
     * Adds home button to the view.
     */
    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, user);
        titlePanel.add(hb);
        panel.add(titlePanel, BorderLayout.PAGE_START);
    }
    /**
     * Updates the view by creating new instance of DayView (suboptimal).
     * ##Would be able to actually reload the DayView with a stronger understanding
     * of the model in MVP.
     */
    @Override
    public void update() {
        dvl.update();
        new DayView(day, weekday,isPayday, dvl.getShifts(), user);
        dispose();

    }
}
