import Entities.HR;
import FrameworksAndDrivers.AddHRGUI;
import FrameworksAndDrivers.ManageEmployeesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener, Page{

    private JFrame frame = new JFrame();

    private Container contentPane = frame.getContentPane();

    private JButton manageEmployees = new JButton("Manage Employees");

    private JButton schedule = new JButton("Schedule");

    private JButton addHR = new JButton("Add Entities.HR Account");

    private JButton notifCenter = new JButton("Entities.Notification Center");


    private JButton payHist = new JButton("View Entities.Payment History");

    private JPanel buttonsPanel = new JPanel();


    private int userID;

    public HomePage(int idNum){
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
        UserFactory uf = new UserFactory();
        buttonsPanel.setLayout(new GridLayout(4, 1));
        schedule.addActionListener(this);
        notifCenter.addActionListener(this);
        buttonsPanel.add(schedule);
        buttonsPanel.add(notifCenter);
        if (uf.idToUser(userID) instanceof HR){
            manageEmployees.addActionListener(this);
            buttonsPanel.add(manageEmployees);
            addHR.addActionListener(this);
            buttonsPanel.add(addHR);
        } else{
            payHist.addActionListener(this);
            buttonsPanel.add(payHist);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(schedule)){
            //Open Calendar with user as parameter.
            frame.dispose();
        } else if (source.equals(notifCenter)){
            //Open Entities.Notification center.
            frame.dispose();
        } else if (source.equals(manageEmployees)){
            new ManageEmployeesGUI();
            frame.dispose();
        } else if (source.equals(addHR)){
            new AddHRGUI();
            frame.dispose();
        } else if (source.equals(payHist)){
            //Go to their payment history.
            frame.dispose();
        }
    }
}
