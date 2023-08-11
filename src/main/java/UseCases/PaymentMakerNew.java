package UseCases;// This file belongs to the UseCases package/Folder
import Entities.Payment;
import Entities.Shift;
import Entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

// Above are all the imports for this file

/**
 * The payment maker is used to make payments and it should be automatically ran
 * because it runs on system's time and date.
 */
public class PaymentMakerNew{

    private ArrayList<Payment> paylist;

    private ArrayList<User> userlist;



    private int employee_id;
    private UserFileReader reader=UserFileReader.getInstance();
    private String employee_name;
    private float pay_amount;
    private String emp_type;

    private LocalDateTime date= LocalDateTime.now();

    private PaymentInteractor p;

    private ShiftInteractor si = new ShiftInteractor();
    private PaymentInteractor pi = new PaymentInteractor();
    private ArrayList<Shift> shifts;
    private int id;



    static int numberOfPayments;


    //Formatter used for this file that formats the current date into teh desired fromat
    //and store the year, the month and the day in separate variables to be used later.

    /**
     * The payment maker creates and instance of the payment interactor and stores
     * the size of the payment list in a variable called numberOfpayments
     */
    public PaymentMakerNew(int employee_id,int id){

        this.employee_id=employee_id;
        numberOfPayments = p.readData().size();
        this.employee_name= reader.getFirstName(employee_id)+reader.getSurname(employee_id);
        this.emp_type=reader.getType(employee_id);
        this.id= id;

        if (this.emp_type.equals("Salary Worker")){
            this.pay_amount= reader.getPay(employee_id)/12;
        } else if (this.emp_type.equals("Wage Worker") ){
            shifts=si.readData();
            this.pay_amount=WageWorker_Payment(employee_id,shifts);

        }
        pi.writeData(new Payment(this.employee_id,this.pay_amount,this.date,this.id));









    }
    public  float WageWorker_Payment(int employee_id, ArrayList<Shift> shiftArray){
        float hours=0;
        for(int i = 0; i <shiftArray.size();i++){
            if (shiftArray.get(i).getCoworkers().contains(employee_id) ){
                if (shiftArray.get(i).getTime().getYear()==date.getYear()) {
                    if (shiftArray.get(i).getTime().getMonth()==date.getMonth()) {
                        hours += shiftArray.get(i).getDuration();
                    }
                }
            }
        }
        return hours;

    }



}


