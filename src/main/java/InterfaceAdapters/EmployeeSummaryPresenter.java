package InterfaceAdapters;
import UseCases.PaymentMaker;
import UseCases.UserFileReader;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class EmployeeSummaryPresenter{

    //Each button must correspond to a different employee, so this maps makes that association.
    private HashMap<JButton, Integer> payHistButtonsToIDs = new HashMap<JButton, Integer>();

    private HashMap<JButton, Integer> payButtonsToIDs = new HashMap<JButton, Integer>();

    private UserFileReader fr = UserFileReader.getInstance();

    public JPanel makeEmployeePanel(int id) {
        //Make a panel containing all of an employee's information, with a button at the end that
        //Corresponds to that employee.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 14));
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
        panel.add(new JLabel(Float.toString(fr.getPay(id))));
        JButton d = new JButton("View Payment History");
        payHistButtonsToIDs.put(d, id);
        JButton c = new JButton("Pay For the Month");
        payButtonsToIDs.put(c, id);
        panel.add(d);
        panel.add(c);
        return panel;

    }

    public ArrayList<JPanel> makeEmployeePanels(){
        //Get employees from the database, and if they are active, add their panel to a list, which
        //is returned.
        ArrayList<JPanel> panels = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();
        for (Integer user: users){
            if (!fr.getType(user).equals("HR") && fr.getActive(user)){
                panels.add(this.makeEmployeePanel(user));
            }

        }
        return panels;
    }

    public HashMap<JButton, Integer> getMap(){
        return this.payHistButtonsToIDs;
    }

    public HashMap<JButton, Integer> getMap2() {return this.payButtonsToIDs;}

    public void makePayment(int id){
        PaymentMaker p = new PaymentMaker(id);
        p.makePayment();
    }

    public String getName(int id){
        UserFileReader ufr = UserFileReader.getInstance();
        return ufr.getFirstName(id) + " " + ufr.getSurname(id);
    }

}
