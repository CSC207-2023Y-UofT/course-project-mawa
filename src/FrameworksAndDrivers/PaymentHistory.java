package FrameworksAndDrivers;
import Entities.Payment;
import Entities.User;
import UseCases.PaymentInteractor;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class PaymentHistory extends JFrame implements InterfaceAdapters.Page {

    private JFrame history_frame= new JFrame();
    private JPanel history_panel = new JPanel();
    private PaymentInteractor interactor = new PaymentInteractor();

    private JPanel info_panel = new JPanel();
    private JPanel button_panel= new JPanel();
    private Label employeeLabel = new Label("Employee Name");

    private int employee_id;
    private int employer_id;
   private ArrayList<Payment> allpayments;
   private ArrayList<String> string_list = new ArrayList<>();

    private ArrayList<Payment> first_list;
    private ArrayList<String> second_list;






    public PaymentHistory(int employee, int employer){

        this.employee_id=employee;
        this.employer_id=employer;
        this.addTitle();
        history_frame.setVisible(true);
        this.addContent();
        this.addHomeButton();
        history_frame.setSize(500, 500);
        history_panel.setLayout(new BorderLayout());
        history_frame.add(history_panel,BorderLayout.CENTER);
        history_frame.add(info_panel,BorderLayout.PAGE_START);
        history_frame.add(button_panel,BorderLayout.PAGE_END);
        history_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUser(employee_id);
        setUser(employer_id);



    }

    @Override
    public void addTitle() {
    history_frame.setTitle("Payment History");

    }

    @Override
    public void addContent() {

        info_panel.add(employeeLabel);
        history_frame.setVisible(true);
        DefaultListModel<String> mylist = new DefaultListModel<>();
        JList<String> paymentlist = new JList<>(mylist);
        JScrollPane scroller = new JScrollPane(paymentlist);
        history_frame.getContentPane();
        history_panel.add(paymentlist);
        paymentlist.setBounds(100,200,200,200);
        paymentlist.setBackground(Color.gray);
        first_list=user_paymentlist(employee_id);
        second_list=paylistTolist(first_list);
        for(int i=0; i<second_list.size();i++){
            mylist.addElement(second_list.get(i));
        }




    }

    @Override
    public void setUser(int user) {

    }

    @Override
    public void addHomeButton() {
        button_panel.add(new Button("Home"));

    }

    @Override
    public void update() {

    }

   public ArrayList<Payment> user_paymentlist(int employee_id){

        allpayments= interactor.readData();
        ArrayList<Payment> emp_payments = new ArrayList<>();
        for (int i=0; i<allpayments.size(); i++){
        if (allpayments.get(i).getEmployee().getUserNum()==employee_id){

            emp_payments.add(allpayments.get(i));
        }

       }

       return emp_payments;
   }
   public ArrayList<String> paylistTolist( ArrayList<Payment> payments){

        for(int i=0; i<payments.size(); i++){
            String temp = "Employee " + payments.get(i).getEmployee().toString() + "Has been paid" +
                    payments.get(i).getPayment_amount() + "On" + payments.get(i).getpayment_date();
            string_list.add(temp);
            temp="";
        }
        return string_list;
   }
}









