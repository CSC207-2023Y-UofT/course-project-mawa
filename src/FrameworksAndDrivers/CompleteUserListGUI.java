package FrameworksAndDrivers;

import InterfaceAdapters.CompleteUserListPresenter;

import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompleteUserListGUI implements ActionListener, Page {

    private JFrame frame = new JFrame();

    private int viewerID;

    private CompleteUserListPresenter presenter = new CompleteUserListPresenter();

    private JPanel titlePanel = new JPanel();


    public CompleteUserListGUI(int id){
        //Create the UI by first adding the title pane, and then adding the list of user panels.
        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();
        this.setUser(id);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //If a activate/deactivate button is clicked, the user factory updates the user, and the page is reloaded to show the change.
        Object source = e.getSource();
        if (presenter.getMap().containsKey(source)){
            UserFactory uf = new UserFactory();
            uf.changeActivation(buttonsToIDs.get(source));
            new CompleteUserListGUI(viewerID);
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Entities.Employee has been updated.", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Complete Entities.User List");
    }

    public void makeHeader(){
        //Make a panel which includes labels for all the attributes of the users the page shows.
        titlePanel.setLayout(new GridLayout(1, 12));
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
        titlePanel.add(new JLabel("Active:"));
        titlePanel.add(new JLabel("Change Status:"));
    }


    @Override
    public void addContent() {
        //Add the title panel and all user panels, by iterating through the list of all users.
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(titlePanel);
        for (JPanel panel: presenter.makeUserPanels()){
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
