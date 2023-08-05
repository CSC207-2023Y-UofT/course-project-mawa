package InterfaceAdapters;

import Entities.Shift;
import Entities.User;

import java.util.ArrayList;

public class UserController {

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
}
