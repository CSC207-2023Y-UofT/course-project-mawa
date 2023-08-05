package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import InterfaceAdapters.*;
import FrameworksAndDrivers.*;

public class EmployeeSummaryGUI implements ActionListener, Page {

    private JFrame frame = new JFrame();

    private HashMap<JButton, Integer> payButtonsToIDs = new HashMap<JButton, Integer>();

    private HashMap<JButton, Integer> schedButtonsToIDs = new HashMap<JButton, Integer>();

    private HashMap<JButton, Integer> payHistButtonsToIDs = new HashMap<JButton, Integer>();

    private JPanel titlePanel = new JPanel();

    public EmployeeSummaryGUI(){

        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (payButtonsToIDs.containsKey(source)){
            //Open payment page with that employee ID as a parameter
        } else if (schedButtonsToIDs.containsKey(source)){
            //Open rescheduling page
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Active Entities.Employee Summary");
    }

    public void makeHeader(){
        titlePanel.setLayout(new GridLayout(1, 13));
        titlePanel.add(new JLabel("First Name:"));
        titlePanel.add(new JLabel("Surname:"));
        titlePanel.add(new JLabel("Gender:"));
        titlePanel.add(new JLabel("Email:"));
        titlePanel.add(new JLabel("Phone Number:"));
        titlePanel.add(new JLabel("Role:"));
        titlePanel.add(new JLabel("ID Number:"));
        titlePanel.add(new JLabel("Birthday:"));
        titlePanel.add(new JLabel("Type:"));
        titlePanel.add(new JLabel("Salary/Wage:"));
        titlePanel.add(new JLabel("Pay Entities.Employee:"));
        titlePanel.add(new JLabel("View/Edit Schedule:"));
        titlePanel.add(new JLabel("View Entities.Payment History:"));
    }

    public JPanel makeEmployeePanel(Employee employee) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 13));
            panel.add(new JLabel(employee.getFirstname()));
            panel.add(new JLabel(employee.getSurname()));
            panel.add(new JLabel(employee.getGender()));
            panel.add(new JLabel(employee.getEmail()));
            panel.add(new JLabel(Long.toString(employee.getPhoneNum())));
            panel.add(new JLabel(employee.getRoleName()));
            panel.add(new JLabel(Integer.toString(employee.getUserNum())));
            panel.add(new JLabel(employee.getDob().toString()));
            String type = employee.getClass().getName();
            panel.add(new JLabel(type));
            if (type.equals("Entities.Volunteer")) {
                panel.add(new JLabel("0"));
            } else if (type.equals("Entities.WageWorker")) {
                panel.add(new JLabel(Float.toString(((WageWorker) employee).getHourlyWage())));
            } else {
                panel.add(new JLabel(Float.toString(((SalaryWorker) employee).getYearlySalary())));
            }
            JButton b = new JButton("Pay");
            payButtonsToIDs.put(b, employee.getUserNum());
            panel.add(b);
            b.addActionListener(this);
            JButton c = new JButton("View Schedule");
            schedButtonsToIDs.put(c, employee.getUserNum());
            panel.add(c);
            c.addActionListener(this);
            JButton d = new JButton("View Entities.Payment History");
            payHistButtonsToIDs.put(d, employee.getUserNum());
            panel.add(d);
            return panel;

        }


    @Override
    public void addContent() {
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(titlePanel);
        UserInteractor ui = new UserInteractor();

        ArrayList<Employee> employees = ui.getEmployeeList();

        for (Employee employee: employees){
            if (employee.isActive()){
                all_panels.add(this.makeEmployeePanel(employee));
            }

        }
        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);
    }
}
