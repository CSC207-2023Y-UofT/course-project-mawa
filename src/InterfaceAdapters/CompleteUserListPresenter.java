package InterfaceAdapters;

import Entities.SalaryWorker;
import Entities.User;
import Entities.WageWorker;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class CompleteUserListPresenter{

    private HashMap<JButton, Integer> buttonsToIDs = new HashMap<JButton, Integer>();

    public JPanel makeUserPanel(User user){
        //Create a single panel which displays a users information, and has a button which
        //is to serve the function of activating/deactivating the employee depending on their
        //existing status.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 12));
        panel.add(new JLabel(user.getFirstname()));
        panel.add(new JLabel(user.getSurname()));
        panel.add(new JLabel(user.getGender()));
        panel.add(new JLabel(user.getEmail()));
        panel.add(new JLabel(Long.toString(user.getPhoneNum())));
        panel.add(new JLabel(user.getRoleName()));
        panel.add(new JLabel(Integer.toString(user.getUserNum())));
        panel.add(new JLabel(user.getDob().toString()));
        String type = user.getClass().getName();
        panel.add(new JLabel(type));
        if (type.equals("Volunteer") || type.equals("HR")){
            //In order to make the information to all users line up nicely, we just display
            panel.add(new JLabel("0"));
        } else if (type.equals("WageWorker")){
            panel.add(new JLabel(Float.toString(((WageWorker)user).getHourlyWage())));
        } else{
            panel.add(new JLabel(Float.toString(((SalaryWorker)user).getYearlySalary())));
        }
        //Depending on whether the user is currently active, the end of the panel will differ.
        if (user.isActive()){
            panel.add(new JLabel("Yes"));
            JButton b = new JButton("Deactivate");
            buttonsToIDs.put(b, user.getUserNum());
            panel.add(b);
        } else{
            panel.add(new JLabel("No"));
            JButton b = new JButton("Re-Activate");
            buttonsToIDs.put(b, user.getUserNum());
            panel.add(b);
        }
        return panel;
    }

    public ArrayList<JPanel> makeUserPanels(){
        //Get the users from the file and create a panel for each of them, and add them to a list.
        UserInteractor ui = new UserInteractor();
        ArrayList<JPanel> panels = new ArrayList<>();
        ArrayList<User> users = ui.readData();
        for (User user: users){
            panels.add(this.makeUserPanel(user));
        }
        return panels;
    }

    public HashMap<JButton, Integer> getMap(){
        return buttonsToIDs;
    }
}
