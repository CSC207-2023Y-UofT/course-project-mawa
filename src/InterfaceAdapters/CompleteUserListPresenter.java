package InterfaceAdapters;


import Entities.User;
import UseCases.UserController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class CompleteUserListPresenter{

    private HashMap<JButton, Integer> buttonsToIDs = new HashMap<JButton, Integer>();

    private UserFileReader fr = new UserFileReader();



    public JPanel makeUserPanel(int id){
        //Create a single panel which displays a users information, and has a button which
        //is to serve the function of activating/deactivating the employee depending on their
        //existing status.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 12));
        panel.add(new JLabel(fr.getFirstName(id)));
        panel.add(new JLabel(fr.getSurname(id)));
        panel.add(new JLabel(fr.getGender(id)));
        panel.add(new JLabel(fr.getEmail(id)));
        panel.add(new JLabel(Long.toString(fr.getPhoneNumber(id))));
        panel.add(new JLabel(fr.getRole(id)));
        panel.add(new JLabel(Integer.toString(id)));
        panel.add(new JLabel(fr.getDob(id).toString()));
        String type = fr.getType(id);
        panel.add(new JLabel(type));
        panel.add(new JLabel(Float.toString(fr.getPay(id)));
        //Depending on whether the user is currently active, the end of the panel will differ.
        if (fr.getActive(id)){
            panel.add(new JLabel("Yes"));
            JButton b = new JButton("Deactivate");
            buttonsToIDs.put(b, id);
            panel.add(b);
        } else{
            panel.add(new JLabel("No"));
            JButton b = new JButton("Re-Activate");
            buttonsToIDs.put(b, id);
            panel.add(b);
        }
        return panel;
    }

    public ArrayList<JPanel> makeUserPanels(){
        //Get the users from the file and create a panel for each of them, and add them to a list.
        ArrayList<JPanel> panels = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();
        for (Integer user: users){
            panels.add(this.makeUserPanel(user));
        }
        return panels;
    }

    public HashMap<JButton, Integer> getMap(){
        return buttonsToIDs;
    }

    public void changeActivation (int IDnum){
        UserController uc = new UserController();
        uc.changeActivation(IDnum);
    }
}
