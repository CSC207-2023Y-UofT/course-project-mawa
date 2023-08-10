package UseCases;

import Entities.*;

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
        char[] p = new char[password.length()];
        for (int i = 0; i < password.length(); i++){
            p[i] = password.charAt(i);
        }
        uInt.writeData(new User(surname, firstname, gender, email, role, numUsers + 1, phoneNumber, bday, p, type,
                pay));

        /*Although the same UserFactory doesn't create more than a single user in this program,
        it's good for extensibility to increase the number of users, so that a UserFactory can create
        several users, and give them each the appropriate ID number.*/
        numUsers += 1;
    }



}
