package FrameworksAndDrivers;

import InterfaceAdapters.EmployeeSummaryPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeSummaryGUI implements ActionListener, Page {


    private int viewerID;
    private JFrame frame = new JFrame();

    private EmployeeSummaryPresenter presenter = new EmployeeSummaryPresenter();

    private JPanel titlePanel = new JPanel();

    public EmployeeSummaryGUI(int id){
        //Create the UI by storing the viewer, and adding the title, panels.
        this.setUser(id);
        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //If a button to view payment history is clicked, direct to the payment history page for
        //the particular employee.
        Object source = e.getSource();
        if (presenter.getMap().containsKey(source)){
            //Open payment history page using the value with source as a key.
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Active Employee Summary");
    }

    public void makeHeader(){
        //Before the list of employees, the names of the attributes being shown are displayed at
        //the beginning of the page, hence the label creation here.
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
        titlePanel.add(new JLabel("View Payment History:"));
    }



    @Override
    public void addContent() {
        //Add the title panel and those of each employee. Also make the buttons respond to a click.
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(titlePanel);
        for (JPanel panel: presenter.makeEmployeePanels()){
            all_panels.add(panel);
        }
        for (JButton button: presenter.getMap().keySet()){
            button.addActionListener(this);
        }

        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);
    }

    @Override
    public void setUser(int user) {
        this.viewerID = user;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void addHomeButton() {

    }

    @Override
    public void update() {

    }
}
