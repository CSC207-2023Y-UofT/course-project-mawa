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

    public void changeActivation(int IDNum){
        //Obtain the user object corresponding to the given ID, make the desired modification,
        //And then update the object in the file, as well as the shift objects which pay change
        //as a result of the user no longer working them due to their inactivity.
        UserInteractor ui = new UserInteractor();
        ShiftInteractor si = new ShiftInteractor();
        ArrayList<Shift> shifts = si.readData();
        User user = this.idToUser(IDNum);
        if (user.isActive()){
            for (Shift shift: shifts){
                shift.removeCoworker(IDNum); //May need to use a shift factory here.
                si.update(shift);
            }
            user.setActive(false);
        } else{
            user.setActive(true);
        }
        ui.update(user);


    }


    public User idToUser(int id){
        //A useful helper function, as we often pass a users ID as an argument to a function, but
        //then require other information about the user.
        UserInteractor ui = new UserInteractor();
        ArrayList<User> users = ui.readData();
        for (User user: users){
            if (user.getUserNum() == id){
                return user;
            }
        }
        return null;
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
