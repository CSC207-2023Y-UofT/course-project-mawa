package Entities;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {
    //Below are the attributes of the payment class
    private int employee;

    private  String payment_message;

    private  float payment_amount;

    private LocalDateTime date;

    private int paymentId;

    /**
     * the contructor of the payment class
     * @param employee is the employee id.
     * @param payment_amount is the amount of this payment.
     * @param date is the date of this payment.
     * @param id is the ID of this payment.
     *           The payment class stores attributes related to payments and payment
     *           objects could be used to be stored in the database.
     */

    public Payment(int employee,float payment_amount, LocalDateTime date, int id ) {
        this.employee = employee;
        this.date=date;
        this.payment_amount = payment_amount;
        //this.payment_message=employee.getEmployeeName()+employee.getSurname()+"Has been paid the amount of"+payment_amount+"At"+date;
        this.paymentId = id;

    }


    /*public String getPayment_message(){

        if (employee.isActive()==true){

            return payment_message;
        }
        else {
            return "The chosen employee is not currently Active";
        }
    }*/ //please move this to a presenter, it should not be in the entity class

    /**
     *
     * @return the id of this payment
     */
    public int getPaymentId(){return this.paymentId;}

    /**
     * set the payment message to the message that is passed on
     * @param message is the payment message that could be used
     */
    public void setPayment_message(String message){
        this.payment_message=message;
    }

    /**
     * getter for the ID of the employee that is being paid
     * @return The ID of the employee that is being paid.
     */
    public int getEmployee(){
        return this.employee;
    }

    /**
     *
     * sets the employee being paid to the passed on employee
     * @param employee is the employee being paid if it needs to be changed
     */
    public  void setEmployee(int employee){

        this.employee=employee;
    }

    /**
     *
     *  getter for payment amount
     * @return The payment amount of this payment
     */
    public float getPayment_amount(){
        return this.payment_amount;
    }

    /**
     *
     * sets the payment amount of this payment.
     * @param amount is the amount of this payment if needed to be changed
     */
    public void setPayment_amount(int amount){
        this.payment_amount=amount;
    }

    /**
     *
     * getter for payment date
     * @return the date of payment
     */
    public LocalDateTime getDate(){
        return this.date;
    }

}
