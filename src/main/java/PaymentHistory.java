import java.time.LocalDateTime;
import java.util.*;
public class PaymentHistory implements Page {

    private User employee;
    private ArrayList<User> employee_list;

    private  String payment_message;

    private  int payment_amount;

    private LocalDateTime date;


    Public payment(User employee, ArrayList employee_list,int payment_amount, LocalDateTime date ) {
        this.employee = employee;
        this.date=date;
        this.employee_list = employee_list;
        this.payment_message=employee.name+"Has been paid the amount of"+payment_amount+"At"+date;

    }

    public String getPayment_message(){

        if (employee.status==true){

            return payment_message;
        }
        else {
            return "The chosen employee is not currently Active";
        }
    }

    public int getPayment_amount(){
        return payment_amount;
    }

}







