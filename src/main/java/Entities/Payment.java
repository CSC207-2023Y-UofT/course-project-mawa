package Entities;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {

    //Below are the attributes of the payment class
    private int employee;

    private  float paymentAmount;

    private LocalDateTime date;

    private int paymentId;

    /**
     * the contructor of the payment class
     * @param employee is the employee id.
     * @param paymentAmount is the amount of this payment.
     * @param date is the date of this payment.
     * @param id is the ID of this payment.
     *           The payment class stores attributes related to payments and payment
     *           objects could be used to be stored in the database.
     */

    public Payment(int employee,float paymentAmount, LocalDateTime date, int id ) {
        this.employee = employee;
        this.date=date;
        this.paymentAmount = paymentAmount;
        //this.payment_message=employee.getEmployeeName()+employee.getSurname()+"Has been paid the amount of"+payment_amount+"At"+date;
        this.paymentId = id;

    }

    /**
     *
     * @return the id of this payment
     */
    public int getPaymentId(){return this.paymentId;}


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
    public float getPaymentAmount(){
        return this.paymentAmount;
    }

    /**
     *
     * sets the payment amount of this payment.
     * @param amount is the amount of this payment if needed to be changed
     */
    public void setPaymentAmount(int amount){
        this.paymentAmount=amount;
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
