package FrameworksAndDrivers;


import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        buttonsPanel.setLayout(new GridLayout(4, 1));
        for (JButton button: presenter.makeHomeButtons(userID)){
            buttonsPanel.add(button);
            button.addActionListener(this);
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
                    //Open calendar code here
                    frame.dispose();
                    break;
                case "Notification Center":
                    //Open notification center here
                    frame.dispose();
                    break;
                case "Manage Employees":
                    new ManageEmployeesGUI(userID);
                    frame.dispose();
                    break;
                case "Add HR Account":
                    new AddHRGUI(userID);
                    frame.dispose();
                case "View Payment History":
                    //Open the employees' payment history.
                    frame.dispose();
                    break;
            }
        }

    }
}
