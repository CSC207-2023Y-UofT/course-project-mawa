package UseCases;

import Entities.Payment;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PaymentHistoryModel {

     private ArrayList<Payment> emp_payments = new ArrayList<>();
     private PaymentInteractor interactor = new PaymentInteractor();
    private UserFileReader reader=UserFileReader.getInstance();
     private ArrayList<Payment> allpayments;
    private ArrayList<String> string_list = new ArrayList<>();
    private int emp;
    private ArrayList<Payment> first_list;/*This list is used to store the list of payments that
    includes the given employee
    */
    private ArrayList<String> second_list;/*  This list is used to store
    a sting of payments that includes the given employee after transferring list 1 to strings*/
    private DateTimeFormatter get_month= DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter get_year= DateTimeFormatter.ofPattern("yyy");
    private DateTimeFormatter get_day= DateTimeFormatter.ofPattern("dd");




    public ArrayList<String> PaymentHistoryModel(int employee_id){
        this.emp=employee_id;

        first_list=user_paymentlist(this.emp);
        second_list= paylistTolist(first_list);
        return second_list;
    }
    public String label(int employee_id){
        return reader.getFirstName(employee_id)+" "+ reader.getSurname(employee_id);

    }



    /**
     *
     * @param employee_id The ID of the employee that we want to find the list of payments for
     * @return A list of payments including all the payments of that employee.
     */
    public ArrayList<Payment> user_paymentlist(int employee_id){

        allpayments= interactor.readData();
        for (Payment allpayment : allpayments) {
            if (allpayment.getEmployee() == employee_id) {

                emp_payments.add(allpayment);
            }

        }

        return emp_payments;
    }

    /**
     *
     * @param payments  Alist of payments that include the employee given to use_payments
     * @return A list of strings of the payments so that they could be added to the page
     */
    public ArrayList<String> paylistTolist( ArrayList<Payment> payments){

        for (Payment payment : payments) {
            String this_month=payment.getDate().format(get_month);//stores  month as a string
            String this_day=payment.getDate().format(get_day);//stores  day as a string
            String this_year=payment.getDate().format(get_year);//stores  year as a string
            String temp = " This Employee has been paid " +
                    payment.getPaymentAmount() + " on " +this_year+" / "+this_month+" / "+this_day ;
            string_list.add(temp);
            temp = "";
        }
        return string_list;
    }

}



