import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class PaymentMaker{

    private ArrayList<Payment> paylist;

    private ArrayList<User> userlist;

    private LocalDate currentDate= LocalDate.now();
    private DateTimeFormatter get_month= DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter get_year= DateTimeFormatter.ofPattern("yyy");
    private DateTimeFormatter get_day= DateTimeFormatter.ofPattern("dd");

    private ArrayList<User> emplpoyee_list = EmployeeDataBaseInteractor.readData();
    private ArrayList<User> salary_worker;
    private ArrayList<User> wage_worker;


   public PaymentInteractor(){

         String this_month=currentDate.format(get_month);
         String this_day=currentDate.format(get_day);

         for (int i = 0; i < emplpoyee_list.size(); i++){
             if(emplpoyee_list[i] instanceof SalaryWorker)
                 salary_worker.add(emplpoyee_list[i])
         }
         for (int i = 0; i < emplpoyee_list.size(); i++){
               if(emplpoyee_list[i] instanceof WageWorker )
                   wage_worker.add(emplpoyee_list[i])
           }




         try {

             if (this_month.equals("1") || this_month.equals("3") || this_month.equals("5")
                     || this_month.equals("7") || this_month.equals("8") ||this_month=="10"||this_month=="12") {
                 if ( this_day.equals("Friday") &Integer.parseInt(this_day)>=24){

                     //now Here I am supposed to be paying the employees

                 }
             } else if (this_month.equals("4") || this_month.equals("6") || this_month.equals("9")
                     || this_month.equals("11")) {
                 if (this_day.equals("Friday"x) &Integer.parseInt(this_day)>=23){

                     //now Here I am supposed to be paying the employees
                 }

             }
             else {
                 if (this_day.equals("Friday") &Integer.parseInt(this_day)>=21){
                     //now Here I am supposed to be paying the employees
                 }
             }

         } catch (Exception e) {
             throw new RuntimeException(e);
         }


   }

   }


