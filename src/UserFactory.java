import Entities.*;
import InterfaceAdapters.ShiftInteractor;
import InterfaceAdapters.UserInteractor;

import java.io.IOException;
import java.util.ArrayList;

public class UserFactory{
    //As the creation of several children of user is delegated to this class, this is a
    //use of the factory design pattern.

    static int numUsers;

    public UserFactory(){
        //Initialize the numUsers property, which will be needed to generate correct ID numbers.
        UserInteractor ui = new UserInteractor();
        numUsers = ui.readData().size();
    }



    public void makeUser(String surname, String firstname, String gender, String birthYear, String birthMonth,
                             String birthDate, long phoneNumber,
                              String email, String role, String type, String password, float pay){
        //Create a new user, and then write it to the database.

        String bday = birthYear + "-" + birthMonth + "-" + birthDate;
        UserInteractor uInt = new UserInteractor();
        switch (type) {

            case "Wage Worker":
                uInt.writeData(new WageWorker(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, pay, password.toCharArray()));
                break;
            case "Salary Worker":
                uInt.writeData(new SalaryWorker(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, pay, password.toCharArray()));
                break;
            case "Entities.Volunteer":
                uInt.writeData(new Volunteer(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, password.toCharArray()));
                break;
            case "Entities.HR":
                uInt.writeData(new HR(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, password.toCharArray()));
                break;
        }

        /*Although the same UserFactory doesn't create more than a single user in this program,
        it's good for extensibility to increase the number of users, so that a UserFactory can create
        several users, and give them each the appropriate ID number.*/
        numUsers += 1;
    }



}
