package UseCases;// This file belongs to the UseCases package/Folder
import Entities.Payment;
import Entities.Shift;
import Entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Above are all the imports for this file
//Formatter used for this file that formats the current date into teh desired fromat
//and store the year, the month and the day in separate variables to be used later.
/**
 * The payment maker is used to make payments and it should be automatically ran
 * because it runs on system's time and date.
 */
public class PaymentMaker{

    public PaymentInteractor pi;
    private ArrayList<Payment> paylist;

    private ArrayList<User> userlist;



    private int employeeID;
    protected UserFileReader reader=UserFileReader.getInstance();
    private String employeeName;
    private float payAmount;
    private String empType;

    private LocalDateTime date= LocalDateTime.now();

    ShiftInteractor si = new ShiftInteractor();
    private ArrayList<Shift> shifts;
    private int id;
    private boolean isTest;

    private UserInteractor ui;



    static int numberOfPayments;


    //Formatter used for this file that formats the current date into teh desired fromat
    //and store the year, the month and the day in separate variables to be used later.

    /**
     * The payment maker creates and instance of the payment interactor and stores
     * the size of the payment list in a variable called numberOfpayments
     * this version of the constructor will be used for regular used
     * @param employeeId is the id of the employee
     *
     */
    public PaymentMaker(int employeeId){
        reader = UserFileReader.getInstance();
        ui = new UserInteractor();
        si = new ShiftInteractor();
        pi = new PaymentInteractor();

        this.employeeID = employeeId;
        PaymentInteractor pi = new PaymentInteractor();
        numberOfPayments = pi.readData().size();
        this.employeeName = reader.getFirstName(employeeId)+reader.getSurname(employeeId);
        this.empType =reader.getType(employeeId);
        this.id= numberOfPayments + 1;

        if (this.empType.equals("Salary Worker")){
            this.payAmount = reader.getPay(employeeId)/12;
        } else if (this.empType.equals("Wage Worker") ){
            shifts=si.readData();
            this.payAmount = WageWorkerPayment(employeeId,shifts);

        }
        isTest = false;//this constructor is not used for testing


    }

    /**
     *  This is the constructor that is modified so that it could be used for testing environment
     * @param employeeID is the id of the employee
     * @param test
     */
    public PaymentMaker(int employeeID, String test){

        reader = new UserFileReader(".");
        ui = new UserInteractor(".");
        si = new ShiftInteractor(".");
        pi = new PaymentInteractor(".");
        this.employeeID= this.employeeID;
        numberOfPayments = pi.readData().size();
        this.employeeName= reader.getFirstName(employeeID)+reader.getSurname(employeeID);
        this.empType=reader.getType(employeeID);
        this.id= numberOfPayments + 1;

        if (this.empType.equals("Salary Worker")){
            this.payAmount= reader.getPay(employeeID)/12;
        } else if (this.empType.equals("Wage Worker") ){
            shifts=si.readData();
            this.payAmount=WageWorkerPayment(employeeID,shifts);

        }
        isTest = true;//This version of the constructor is used for testing.

    }

    /**
     *
     * @param employeeID is the ID of the employee
     * @param shiftArray is the  array of the shifts
     * @return the payment of the shift worker
     */
    public  float WageWorkerPayment(int employeeID, ArrayList<Shift> shiftArray){

        UserFileReader reader;
        if (isTest){
            reader = new UserFileReader(".");
        } else {
            reader = UserFileReader.getInstance();
        }

        float hours=0;
        for(int i = 0; i <shiftArray.size();i++){
            if (shiftArray.get(i).getCoworkers().contains(employeeID) ){
                if (shiftArray.get(i).getTime().getYear()==date.getYear()) {
                    if (shiftArray.get(i).getTime().getMonth()==date.getMonth()) {
                        hours += shiftArray.get(i).getDuration();
                    }
                }
            }
        }
        return hours * reader.getPay(employeeID);

    }

    public void makePayment(){
        pi.writeData(new Payment(this.employeeID,this.payAmount,this.date,this.id));
    }



}
