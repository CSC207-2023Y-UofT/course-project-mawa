package Entities;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

public class Payment {

    private User employee;

    private  String payment_message;

    private  float payment_amount;

    private LocalDateTime date;

    private int paymentId;

    public Payment(User employee,float payment_amount, LocalDateTime date, int id ) {
        this.employee = employee;
        this.date=date;
        this.payment_message=employee.getEmployeeName()+employee.getSurname()+"Has been paid the amount of"+payment_amount+"At"+date;
        this.paymentId = id;

    }


    public String getPayment_message(){

        if (employee.isActive()==true){

            return payment_message;
        }
        else {
            return "The chosen employee is not currently Active";
        }
    }


    public int getPaymentId(){return this.paymentId;}
    public void setPayment_message(String message){
        this.payment_message=message;
    }
    public User getEmployee(){
        return this.employee;
    }
    public  void setEmployee(User employee){

        this.employee=employee;
    }

    public float getPayment_amount(){
        return this.payment_amount;
    }

    public void setPayment_amount(){
        this.payment_amount=payment_amount;
    }


    public String getpayment_date(){
        return this.date.toString();
    }

}
