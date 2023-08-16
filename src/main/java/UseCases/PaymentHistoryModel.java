package UseCases;

import Entities.Payment;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PaymentHistoryModel {

     private ArrayList<Payment> empPayments = new ArrayList<>();
     private PaymentInteractor interactor = new PaymentInteractor();
    private UserFileReader reader=UserFileReader.getInstance();
     private ArrayList<Payment> allpayments;
    private ArrayList<String> stringList = new ArrayList<>();
    private int emp;
    private ArrayList<Payment> firstList;/*This list is used to store the list of payments that
    includes the given employee
    */
    private ArrayList<String> secondList;/*  This list is used to store
    a sting of payments that includes the given employee after transferring list 1 to strings*/
    private DateTimeFormatter getMonth = DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter getYear = DateTimeFormatter.ofPattern("yyy");
    private DateTimeFormatter getDay = DateTimeFormatter.ofPattern("dd");




    public ArrayList<String> PaymentHistoryModel(int employeeID){
        this.emp= employeeID;

        firstList =user_paymentlist(this.emp);
        secondList = paylistTolist(firstList);
        return secondList;
    }
    public String label(int employeeID){
        return reader.getFirstName(employeeID)+" "+ reader.getSurname(employeeID);

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

                empPayments.add(allpayment);
            }

        }

        return empPayments;
    }

    /**
     *
     * @param payments  Alist of payments that include the employee given to use_payments
     * @return A list of strings of the payments so that they could be added to the page
     */
    public ArrayList<String> paylistTolist( ArrayList<Payment> payments){

        for (Payment payment : payments) {
            String this_month=payment.getDate().format(getMonth);//stores  month as a string
            String this_day=payment.getDate().format(getDay);//stores  day as a string
            String this_year=payment.getDate().format(getYear);//stores  year as a string
            String temp = " This Employee has been paid " +
                    payment.getPaymentAmount() + " on " +this_year+" / "+this_month+" / "+this_day ;
            stringList.add(temp);
            temp = "";
        }
        return stringList;
    }

}



