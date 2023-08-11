package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Entities.Payment;
import InterfaceAdapters.PaymentHistoryPresenter;
import UseCases.PaymentInteractor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

/**
 * This is the GUI class for payment history page
 *
 */

public class PaymentHistory extends JFrame implements InterfaceAdapters.Page, ActionListener  {

    private JFrame history_frame= new JFrame();
    private JPanel history_panel = new JPanel();
    private PaymentInteractor interactor = new PaymentInteractor();

    private JPanel info_panel = new JPanel();
    private JPanel button_panel= new JPanel();
    private Label employeeLabel = new Label();

    private int employee_id;
    private int employer_id;
   private ArrayList<Payment> allpayments;

    private ArrayList<String> adding_list= new ArrayList<>();
    private PaymentHistoryPresenter presenter= new PaymentHistoryPresenter();

    /**
     * Payment history will do the basic of creating a frame and adding panels to it and
     * initializing the attributes
     * @param employee is the employee that using this page
     * @param employer is the employer that is using this page
     *
     */
    public PaymentHistory(int employee, int employer){

        this.employee_id=employee;
        this.employer_id=employer;
        this.addTitle();
        this.addContent();
        JButton homebutton= new JButton("Home");
        homebutton.setActionCommand("home");
        homebutton.addActionListener(this);
        this.addHomeButton();
        button_panel.add(homebutton);
        history_frame.setSize(500, 500);
        history_panel.setLayout(new BorderLayout());
        history_frame.add(history_panel,BorderLayout.CENTER);
        history_frame.add(info_panel,BorderLayout.PAGE_START);
        history_frame.add(button_panel,BorderLayout.PAGE_END);
        history_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUser(employee_id);
        setUser(employer_id);
        history_frame.setVisible(true);




    }

    /**
     * Add the title to the page
     */
    @Override
    public void addTitle() {
    history_frame.setTitle("Payment History");

    }

    /**
     * Add te content page such as the list of payments, the scrollbar...etc
     */
    @Override
    public void addContent() {
        employeeLabel.setText(presenter.getLabel(employee_id));
        info_panel.add(employeeLabel);
        DefaultListModel<String> mylist = new DefaultListModel<>();
        JList<String> paymentlist = new JList<>(mylist);// creating a list to be shown
        history_panel.add(paymentlist);
        paymentlist.setBounds(0,0,500,500);
        paymentlist.setBackground(Color.gray);// having the ist to be gray so that it is more visible
        // below are codes related to then scoll bar and the list
        paymentlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        paymentlist.setLayoutOrientation(JList.VERTICAL);
        paymentlist.setVisibleRowCount(-1);
        //JScrollPane scroller = new JScrollPane(paymentlist);
        //scroller.setPreferredSize(new Dimension(225, 100));
        //scroller.setAlignmentX(LEFT_ALIGNMENT);
       // history_panel.add(scroller, BorderLayout.CENTER);

        adding_list= presenter.PaymentHistoryPresenter(employee_id);
        System.out.println(adding_list);

        for(int i=0; i<adding_list.size();i++){
            mylist.addElement(adding_list.get(i));
        }







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
            new HomePage(employer_id);
            history_frame.dispose();
        }
    }





}









