package UseCases;

import Entities.*;

import java.io.IOException;
import java.util.ArrayList;

public class UserFactory{


    static int numUsers;

    public UserFactory(){
        UserInteractor ui = new UserInteractor();
        numUsers = ui.readData().size();
    }

    public void changeActivation(int IDNum){
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


    public Boolean verifyCredentials(int userNum, String password) throws IOException, ClassNotFoundException {
        //Return true iff the provided userNum and passowrd are stored in the file, and the user it represents is active.

        UserInteractor ui = new UserInteractor();
        ArrayList<User> users = ui.readData();
        Boolean toReturn = false;
        for (User user: users){
            if (user.getUserNum() == userNum && user.isActive()){
                StringBuilder p = new StringBuilder();
                for (char c: user.getPassword()){
                    p.append(c);
                }
                if (p.toString().equals(password)){
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }

    public User idToUser(int id){
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

        numUsers += 1;
    }



}
