package UseCases;

import Entities.Shift;
import Entities.User;


import java.util.ArrayList;

public class UserController {

    private UserFileReader ufr = new UserFileReader();

    public void changeActivation(int IDNum){
        //Obtain the user object corresponding to the given ID, make the desired modification,
        //And then update the object in the file, as well as the shift objects which pay change
        //as a result of the user no longer working them due to their inactivity.
        UserInteractor ui = new UserInteractor();
        ShiftInteractor si = new ShiftInteractor();
        ArrayList<Shift> shifts = si.readData();
        if (ufr.getActive(IDNum)){
            for (Shift shift: shifts){
                shift.removeCoworker(IDNum); //May need to use a shift factory here.
                si.update(shift);
            }
            idToUser(IDNum).setActive(false);
        } else{
            idToUser(IDNum).setActive(true);
        }
        ui.update(idToUser(IDNum));


    }

    public User idToUser(int idNum){
        //Obtain the user object associated with this id, which is needed to change its activation.
        ArrayList<User> users = new ArrayList();
        for (User user: users){
            if (user.getUserNum() == idNum){
                return user;
            }
        }

        return null;
    }


}
