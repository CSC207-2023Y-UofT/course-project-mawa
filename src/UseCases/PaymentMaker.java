package UseCases;
import Entities.Payment;
import Entities.Shift;
import Entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PaymentMaker{

    private ArrayList<Payment> paylist;

    private ArrayList<User> userlist;

    private UserInteractor ui = new UserInteractor();

    private ShiftInteractor si = new ShiftInteractor();

    private PaymentInteractor pi = new PaymentInteractor();

    static int numberOfPayments;

    private LocalDateTime currentDate= LocalDateTime.now();
    private DateTimeFormatter get_month= DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter get_year= DateTimeFormatter.ofPattern("yyy");
    private DateTimeFormatter get_day= DateTimeFormatter.ofPattern("dd");

    private ArrayList<User> emplpoyee_list = ui.readData();
    private ArrayList<User> salary_worker = new ArrayList<>();
    private ArrayList<User> wage_worker = new ArrayList<>();


   public PaymentMaker(){

        PaymentInteractor p = new PaymentInteractor();
        numberOfPayments = p.readData().size();
   }

   public void makePayment(){

       String this_month=currentDate.format(get_month);
       String this_day=currentDate.format(get_day);

       for (User user : emplpoyee_list) {
           if (user.getRole().equals("Salary Worker")) {
               salary_worker.add(user);
           }
       }
       for (User user : emplpoyee_list) {
           if (user.getRole().equals("Wage Worker")) {
               wage_worker.add(user);
           }
       }




       try {

           if (this_month.equals("1") || this_month.equals("3") || this_month.equals("5")
                   || this_month.equals("7") || this_month.equals("8") ||this_month=="10"||this_month=="12") {
               if ( this_day.equals("Friday") &Integer.parseInt(this_day)>=24){

                   for (int i=0; i< salary_worker.size();i++){

                       float pay_amount=salary_worker.get(i).getPay()/12;
                       pi.writeData(new Payment(salary_worker.get(i), pay_amount ,currentDate, numberOfPayments + 1));
                       numberOfPayments += 1;

                   }

               }
           } else if (this_month.equals("4") || this_month.equals("6") || this_month.equals("9")
                   || this_month.equals("11")) {
               if (this_day.equals("Friday") &Integer.parseInt(this_day)>=23){

                   for (int i=0; i< salary_worker.size();i++){

                       float pay_amount=salary_worker.get(i).getPay()/12;
                       pi.writeData(new Payment(salary_worker.get(i),pay_amount,currentDate, numberOfPayments + 1));
                       numberOfPayments += 1;

                   }
                   for (int i=0; i< wage_worker.size();i++){

                       float pay_amount=wage_Worker_monthly_hour_calculator(si.readData(), wage_worker.get(i)) * wage_worker.get(i).getPay();
                       pi.writeData(new Payment(wage_worker.get(i),pay_amount,currentDate, numberOfPayments + 1));
                       numberOfPayments += 1;

                   }
               }

           }
           else {
               if (this_day.equals("Friday") &Integer.parseInt(this_day)>=21){
                   for (int i=0; i< salary_worker.size();i++){

                       float pay_amount=salary_worker.get(i).getPay()/12;
                      pi.writeData(new Payment(salary_worker.get(i),pay_amount,currentDate, numberOfPayments + 1));
                      numberOfPayments += 1;

                   }
                   for (int i=0; i< wage_worker.size();i++){

                       int pay_amount=wage_Worker_monthly_hour_calculator(si.readData(),wage_worker.get(i));
                       pi.writeData(new Payment(wage_worker.get(i),pay_amount,currentDate, numberOfPayments + 1));
                       numberOfPayments += 1;

                   }
               }
           }

       } catch (Exception e) {
           throw new RuntimeException(e);
       }


   }
   public int wage_Worker_monthly_hour_calculator(ArrayList<Shift> shiftArray, User worker){
       int monthly_hours=0;
       for(int i = 0; i <shiftArray.size();i++){
           if (shiftArray.get(i).getCoworkers().contains(worker)){
               monthly_hours+=shiftArray.get(i).getDuration();
           }
       }
       return monthly_hours;
   }

   }


