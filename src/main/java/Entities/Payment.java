package Entities;
import java.time.LocalDateTime;

public class Payment {

    private int employee;

    private  String payment_message;

    private  float payment_amount;

    private LocalDateTime date;

    private int paymentId;

    public Payment(int employee,float payment_amount, LocalDateTime date, int id ) {
        this.employee = employee;
        this.date=date;
        this.payment_amount = payment_amount;
        //this.payment_message=employee.getEmployeeName()+employee.getSurname()+"Has been paid the amount of"+payment_amount+"At"+date;
        this.paymentId = id;

    }

    public int getPaymentId(){return this.paymentId;}
    public void setPayment_message(String message){
        this.payment_message=message;
    }
    public int getEmployee(){
        return this.employee;
    }
    public  void setEmployee(int employee){

        this.employee=employee;
    }

    public float getPayment_amount(){
        return this.payment_amount;
    }

    public void setPayment_amount(){
        this.payment_amount=payment_amount;
    }


    public LocalDateTime getDate(){
        return this.date;
    }

}
