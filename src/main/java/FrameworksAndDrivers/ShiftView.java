package FrameworksAndDrivers;

import FrameworksAndDrivers.HomeButton;
import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShiftView extends JFrame implements Page {
    private JPanel panel;
    private int shift;
    private TimeOffButton timeOffButton;
    private int employee;
    private ShiftPresenter presenter;
    private LocalDateTime date;
    public ShiftView(int shift, int employee){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.shift = shift;
        setUser(employee);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addTitle();
        addContent();
        this.presenter = new ShiftPresenter(shift, this, timeOffButton, employee);
        this.date = presenter.getDate();
    }

    @Override
    public void addTitle() {

        JLabel title = new JLabel(date.getDayOfWeek()+", "
                +date.getMonth()+" "+date.getDayOfMonth());
        title.setFont(new Font("Serif", Font.PLAIN, getHeight()/8));
        JPanel titlePanel = new JPanel(new FlowLayout());

        panel.add(title);
    }

    @Override
    public void addContent() {
        JLabel time = new JLabel(date.getHour() +":"+date.getMinute());

        JLabel coworkersLabel = new JLabel(presenter.getCoworkerString());
        timeOffButton = new TimeOffButton(shift, employee);
        panel.add(time);
        panel.add(coworkersLabel);
        panel.add(timeOffButton);
        panel.setVisible(true);
    }

    @Override
    public void setUser(int user) {
        this.employee = user;
    }

    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, employee);
    }

    @Override
    public void update() {
        new RequestForm(date, date.plusHours((long) presenter.getDuration()), employee, shift);
    }

}