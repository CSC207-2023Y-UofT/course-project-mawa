package Entities;

import java.time.LocalDateTime;

public class Payment {

    private User employee;

    private  String payment_message;

    private  int payment_amount;

    private LocalDateTime date;

    Payment(User employee,int payment_amount, LocalDateTime date ) {
        this.employee = employee;
        this.date=date;
        this.payment_message=employee.getFirstname() + employee.getSurname() +"Has been paid the amount of"+payment_amount+"At"+date;

    }


    public String getPayment_message(){

        if (employee.isActive()){

            return payment_message;
        }
        else {
            return "The chosen employee is not currently Active";
        }
    }

    public void setPayment_message(String message){
        this.payment_message=message;
    }
    public User getEmployee(){
        return this.employee;
    }
    public  void setEmployee(User employee){

        this.employee=employee;
    }

    public int getPayment_amount(){
        return this.payment_amount;
    }

    public void setPayment_amount(){
        this.payment_amount=payment_amount;
    }

}
