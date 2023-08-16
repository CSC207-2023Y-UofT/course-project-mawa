package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import InterfaceAdapters.PaymentHistoryPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

/**
 * This is the GUI class for payment history page
 *
 */

public class PaymentHistory extends JFrame implements InterfaceAdapters.Page, ActionListener  {

    private JFrame historyFrame = new JFrame();
    private JPanel historyPanel = new JPanel();

    private JPanel infoPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private Label employeeLabel = new Label();


    private int employeeID;
    private int employerID;

    private ArrayList<String> addingList = new ArrayList<>();
    private PaymentHistoryPresenter presenter= new PaymentHistoryPresenter();

    /**
     * Payment history will do the basic of creating a frame and adding panels to it and
     * initializing the attributes
     * @param employee is the employee that using this page
     * @param employer is the employer that is using this page
     *
     */
    public PaymentHistory(int employee, int employer){

        this.employeeID =employee;
        this.employerID =employer;
        this.addTitle();
        JButton homebutton= new JButton("Home");
        homebutton.setActionCommand("home");
        homebutton.addActionListener(this);
        this.addHomeButton();
        buttonPanel.add(homebutton);
        historyFrame.setSize(800, 600);
        historyPanel.setLayout(new BorderLayout());
        this.addContent();
        historyFrame.add(historyPanel,BorderLayout.CENTER);
        historyFrame.add(infoPanel,BorderLayout.PAGE_START);
        historyFrame.add(buttonPanel,BorderLayout.PAGE_END);
        historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUser(employeeID);
        setUser(employerID);
        historyFrame.setVisible(true);




    }

    /**
     * Add the title to the page
     */
    @Override
    public void addTitle() {
        historyFrame.setTitle("Payment History");

    }

    /**
     * Add the content page such as the list of payments, the scrollbar...etc
     */
    @Override
    public void addContent() {
        employeeLabel.setText(presenter.getLabel(employeeID));
        infoPanel.add(employeeLabel);
        // below are codes related to then scroll bar and the list
        DefaultListModel<String> mylist = new DefaultListModel<>();
        addingList = presenter.PaymentHistoryPresenter(employeeID);
        for(int i = 0; i< addingList.size(); i++){
            mylist.addElement(addingList.get(i));
        }
        JList<String> paymentlist = new JList<>(mylist);// creating a list to be shown
        paymentlist.setBackground(Color.gray);// having the ist to be gray so that it is more visible
        paymentlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        paymentlist.setLayoutOrientation(JList.VERTICAL);
        paymentlist.setVisibleRowCount(-1);
        paymentlist.setBounds(0,0,500,500);
        JScrollPane scroller = new JScrollPane(paymentlist);
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        historyPanel.add(scroller, BorderLayout.CENTER);
        //history_panel.add(paymentlist);


    }

    @Override
    public void setUser(int user) {

    }

    @Override
    public void addHomeButton() {

    }

    /**
     * The home button to be able to return to the home button.
     */


    @Override
    public void update() {

    }
    public void actionPerformed(ActionEvent e) {
        if ("home".equals(e.getActionCommand())) {
            new HomePage(employerID);
            historyFrame.dispose();
        }
    }





}
