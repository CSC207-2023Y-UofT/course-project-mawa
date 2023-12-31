package FrameworksAndDrivers;


import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class HomePage implements ActionListener, Page {


    private JFrame frame = new JFrame();

    private HomePagePresenter presenter = new HomePagePresenter();

    private JPanel buttonsPanel = new JPanel();


    private int userID;

    public HomePage(int idNum){
        //Create the page by setting its title, and content, which is dependent on the type of user.
        this.userID = idNum;
        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();
    }
    @Override
    public void addTitle() {
        frame.setTitle("Home");
    }

    @Override
    public void addContent() {
        //Add the buttons created by the presenter to the panel.
        buttonsPanel.setLayout(new GridLayout(3, 1));
        for (String label: presenter.makeHomeButtons(userID)){
            JButton b = new JButton(label);
            buttonsPanel.add(b);
            b.addActionListener(this);
        }
        frame.add(buttonsPanel);
    }

    @Override
    public void setUser(int user) {

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //Depending on which button is pushed, we open a particular other page.
        Object source = e.getSource();
        if (source instanceof JButton){
            switch (((JButton)source).getText()){

                case "Schedule":
                    new CalendarGUI(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), userID);
                    frame.dispose();
                    break;
                case "Notification Center":
                    new NotificationGUI(userID);
                    frame.dispose();
                    break;
                case "Manage Employees":
                    new ManageEmployeesGUI(userID);
                    frame.dispose();
                    break;
                case "View Payment History":
                    new PaymentHistory(userID, userID);
                    frame.dispose();
                    break;
            }
        }

    }
}
