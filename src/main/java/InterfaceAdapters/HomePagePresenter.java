package InterfaceAdapters;
import UseCases.UserController;
import UseCases.UserFileReader;

import javax.swing.*;
import java.util.ArrayList;

public class HomePagePresenter {

    private UserFileReader ufr = new UserFileReader();

    public ArrayList<JButton> makeHomeButtons(int id){
        //Return a list of buttons which are displayed on the homepage, depending if the providec
        //id represents an HR or employee account.
        UserController uc = new UserController();
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(new JButton("Schedule"));
        buttons.add(new JButton("Notification Center"));
        if (ufr.getType(id).equals("HR")){
            buttons.add(new JButton("Manage Employees"));
        } else {
            buttons.add(new JButton("View Payment History"));
        }
        return buttons;
    }

}
