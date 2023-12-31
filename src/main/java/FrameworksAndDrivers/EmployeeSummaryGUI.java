package FrameworksAndDrivers;

import InterfaceAdapters.EmployeeSummaryPresenter;
import InterfaceAdapters.Page;
import InterfaceAdapters.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;


/**
 * The EmployeeSummaryGUI class displays a summary of active employees with their attributes.
 * Users can view payment history and make payments for employees.
 */
public class EmployeeSummaryGUI implements ActionListener, Page {


    private int viewerID;
    private JFrame frame = new JFrame();

    private EmployeeSummaryPresenter presenter = new EmployeeSummaryPresenter();

    private UserController controller = new UserController();

    private HashMap<JButton, Integer> payHistButtonsToIDs = new HashMap<JButton, Integer>();

    private HashMap<JButton, Integer> payButtonsToIDs = new HashMap<JButton, Integer>();

    private JPanel titlePanel = new JPanel();


    private JButton back = new JButton("Back");

    private JPanel backPanel = new JPanel();

    /**
     * Creates an instance of the EmployeeSummaryGUI class.
     *
     * @param id The ID of the user viewing the summary.
     */
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
        //If a pay button is clicked, pay the employee and display a confirmation.
        Object source = e.getSource();
        if (payHistButtonsToIDs.containsKey(source)){
            new PaymentHistory(payHistButtonsToIDs.get(source),viewerID);
            frame.dispose();
        } else if (payButtonsToIDs.containsKey(source)){
            controller.makePayment(payButtonsToIDs.get(source));
            JOptionPane.showMessageDialog(null, presenter.getName(payButtonsToIDs.get(source)) +
                            " has been paid for the month of "
                            + LocalDateTime.now().getMonth() + ".",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else if (source.equals(back)){
            new ManageEmployeesGUI(viewerID);
            frame.dispose();
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Active Employee Summary");
    }

    /**
     * Creates a header panel with labels for employee attributes.
     */
    public void makeHeader(){
        titlePanel.setLayout(new GridLayout(1, 14));
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
        titlePanel.add(new JLabel("Make Payment:"));
    }



    @Override
    public void addContent() {
        //Add the title panel and those of each employee. Also make the buttons respond to a click.

        JPanel all_panels = new JPanel();
        backPanel.setLayout(new GridLayout(1, 1));
        backPanel.add(back);
        back.addActionListener(this);
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(backPanel);
        all_panels.add(titlePanel);
        for (Object[] attributes: presenter.makeEmployeePanels()){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 12));
            for (int i = 0; i < 10; i ++){
                panel.add(new JLabel((String) attributes[i]));
            }
            if ((Integer)attributes[10] == 0){
                panel.add(new JLabel("N/A"));
                panel.add(new JLabel("N/A"));
            }
            JButton d = new JButton("View Payment History");
            payHistButtonsToIDs.put(d, (Integer)attributes[10]);
            JButton c = new JButton("Pay For the Month");
            payButtonsToIDs.put(c, (Integer)attributes[10]);
            c.addActionListener(this);
            d.addActionListener(this);
            panel.add(d);
            panel.add(c);
            all_panels.add(panel);
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
