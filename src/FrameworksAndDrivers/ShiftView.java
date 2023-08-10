package FrameworksAndDrivers;


import Entities.Employee;

import InterfaceAdapters.*;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class ShiftView extends JFrame implements Page {
    private JPanel panel;
    private int shift;
    private JButton timeOffButton;
    private int employee;
    private UserFileReader empDB;
    private ShiftFileReader shiftDB;
    private LocalDateTime date;
    public ShiftView(int shift, int employee){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.shift = shift;
        setUser(employee);
        this.empDB = new UserFileReader();
        this.shiftDB = new ShiftFileReader(FileNameConstants.SHIFT_FILE_NAME);
        this.date = shiftDB.getDate(shift);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addTitle();
        addContent();
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
        String coworkers = "";
        for (int id : shiftDB.getEmployeeId(shift)){
            if (id != employee){
                coworkers += String.format(", %s %s", empDB.getFirstName(id),
                        empDB.getSurname(id));
            }
        }
        JLabel coworkersLabel = new JLabel(coworkers.substring(2));
        TimeOffButton timeOffButton = new TimeOffButton(shift, employee);
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
    }


}
