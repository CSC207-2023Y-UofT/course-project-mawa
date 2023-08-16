package FrameworksAndDrivers;

import InterfaceAdapters.CompleteUserListPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import InterfaceAdapters.*;

/**
 * The CompleteUserListGUI class displays a complete list of users with their attributes and activation status.
 * Users can be activated or deactivated by clicking corresponding buttons.
 */
public class CompleteUserListGUI implements ActionListener, Page {

    private JFrame frame = new JFrame();

    private int viewerID;

    private CompleteUserListPresenter presenter = new CompleteUserListPresenter();

    private HashMap<JButton, Integer> buttonsToIDs = new HashMap<JButton, Integer>();

    private JPanel titlePanel = new JPanel();

    private JButton back = new JButton("Back");

    private JPanel backPanel = new JPanel();

    /**
     * Creates an instance of the CompleteUserListGUI class.
     *
     * @param id The ID of the user viewing the list.
     */
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
        //If a. activate/deactivate button is clicked, the controller makes a user factory updates the user, and the
        // page is reloaded to show the change.
        Object source = e.getSource();
        if (buttonsToIDs.containsKey(source)){
            UserController uc = new UserController();
            uc.changeActivation(buttonsToIDs.get(source));
            new CompleteUserListGUI(viewerID);
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Employee has been updated.", "", JOptionPane.INFORMATION_MESSAGE);
            //Handling of the back button.
        } else if (source.equals(back)){
            new ManageEmployeesGUI(viewerID);
            frame.dispose();
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Complete User List");
    }

    /**
     * Creates a header panel with labels for user attributes.
     */
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
        backPanel.setLayout(new GridLayout(1, 1));
        backPanel.add(back);
        back.addActionListener(this);
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(backPanel);
        all_panels.add(titlePanel);
        for (Object[] attributes: presenter.makeUserPanels()){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 13));
            for (int i = 0; i < 11; i ++){
                panel.add(new JLabel((String) attributes[i]));
            }
            if ((Integer)attributes[11] == 0){
                panel.add(new JLabel("N/A"));
            } else {
                String s;
                if (attributes[10].equals("Yes")){
                    s = "Deactivate";
                } else{
                    s = "Reactivate";
                }
                JButton b = new JButton(s);
                buttonsToIDs.put(b, (Integer)attributes[11]);
                b.addActionListener(this);
                panel.add(b);
            }
            all_panels.add(panel);
        }

        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);
    }

    @Override
    public void setUser(int user) {
        this.viewerID = user;
    }

    @Override
    public void dispose() {
        // Not used in this context
    }

    @Override
    public void addHomeButton() {
        // Not used in this context
    }

    @Override
    public void update() {
        // Not used in this context
    }
}
