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
        ArrayList<User> users = ui.readData();
        ShiftInteractor si = new ShiftInteractor();
        ArrayList<Shift> shifts = si.readData();
        for (User e: users){
            if (e.getUserNum() == IDNum){
                if (e.isActive()){
                    for (Shift shift: shifts){
                        shift.removeCoworker(IDNum); //May need to use a shift factory here.
                        si.update(shift);
                    }
                    e.setActive(false);
                } else{
                    e.setActive(true);
                }
                ui.update(e);
            }
        }
    }


    public Boolean[] verifyCredentials(int userNum, String password) throws IOException, ClassNotFoundException {
        //Return an array of two booleans, the first one is if the login info is correct, the second is whether
        //the account is an HR account.

        UserInteractor ui = new UserInteractor();
        ArrayList<User> users = ui.readData();
        Boolean[] toReturn = {false, false};
        for (User user: users){
            if (user.getUserNum() == userNum && user.isActive()){
                StringBuilder p = new StringBuilder();
                for (char c: user.getPassword()){
                    p.append(c);
                }
                if (p.toString().equals(password)){
                    toReturn[0] = true;
                } if (user instanceof HR){
                    toReturn[1] = true;
                }
            }
        }
        return toReturn;
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
            case "Volunteer":
                uInt.writeData(new Volunteer(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, password.toCharArray()));
                break;
            case "HR":
                uInt.writeData(new HR(surname, firstname, gender, email, role, numUsers + 1,
                        phoneNumber, bday, password.toCharArray()));
                break;
        }

        numUsers += 1;
    }



}
