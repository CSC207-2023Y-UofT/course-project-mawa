package FrameworksAndDrivers;


import Entities.Employee;
import Entities.Shift;
import FrameworksAndDrivers.Page;

import FrameworksAndDrivers.HomeButton;


import InterfaceAdapters.ShiftPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShiftView extends JFrame implements Page {
    private JPanel panel;
    private Shift shift;
    private JButton timeOffButton;
    private int employee;

    private EmployeeDataBaseInteractor empDB;
    private ShiftPresenter presenter;
    public ShiftView(Shift shift, int employee){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.shift = shift;
        setUser(employee);
        this.empDB = new EmployeeDataBaseInteractor();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addTitle();
        addContent();
        this.presenter = new ShiftPresenter(shift, this, timeOffButton, employee);
    }

    @Override
    public void addTitle() {
        JLabel title = new JLabel(shift.getTime().getDayOfWeek()+", "
                +shift.getTime().getMonth()+" "+shift.getTime().getDayOfMonth());
        title.setFont(new Font("Serif", Font.PLAIN, getHeight()/8));
        JPanel titlePanel = new JPanel(new FlowLayout());

        panel.add(title);
    }

    @Override
    public void addContent() {
        JLabel time = new JLabel(shift.getTime().getHour() +":"+shift.getTime().getMinute());
        ArrayList<Employee> coworkers = new ArrayList<Employee>();
        for (int id : shift.getCoworkers()){
            if (!(id == employee)){
                coworkers.add((Employee)empDB.getEmployee(id));
            }
        }
        JLabel coworkersLabel = new JLabel(coworkers.toString());
        timeOffButton = new JButton("Request Entities.Shift Off");
        timeOffButton.addActionListener(this);
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
