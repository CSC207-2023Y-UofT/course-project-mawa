package UseCases;

import Entities.Shift;
import Entities.User;


import java.util.ArrayList;

public class UserController {

    private UserFileReader ufr = UserFileReader.getInstance();

    private UserInteractor ui = new UserInteractor();

    public void changeActivation(int IDNum){
        //Obtain the user object corresponding to the given ID, make the desired modification,
        //And then update the object in the file, as well as the shift objects which pay change
        //as a result of the user no longer working them due to their inactivity.
        UserInteractor ui = new UserInteractor();
        ShiftInteractor si = new ShiftInteractor();
        ArrayList<Shift> shifts = si.readData();
        if (ufr.getActive(IDNum)){
            User user = this.idToUser(IDNum);
            for (Shift shift: shifts){
                shift.removeCoworker(IDNum); //May need to use a shift factory here.
                si.update(shift);
            }
            user.setActive(false);
            ui.update(user);
        } else{
            User user = this.idToUser(IDNum);
            user.setActive(true);
            ui.update(user);
        }
        System.out.println(this.idToUser(IDNum).isActive());
        ui.update(this.idToUser(IDNum));
        for (User user: ui.readData()){
            System.out.println(user.isActive());
        }


    }

    public User idToUser(int idNum){
        //Obtain the user object associated with this id, which is needed to change its activation.
        ArrayList<User> users = ui.readData();
        for (User user: users){
            if (user.getUserNum() == idNum){
                return user;
            }
        }

        return null;
    }


}
