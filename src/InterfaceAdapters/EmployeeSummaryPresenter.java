package InterfaceAdapters;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class EmployeeSummaryPresenter{

    //Each button must correspond to a different employee, so this maps makes that association.
    private HashMap<JButton, Integer> payHistButtonsToIDs = new HashMap<JButton, Integer>();

    private UserFileReader fr = new UserFileReader();

    public JPanel makeEmployeePanel(int id) {
        //Make a panel containing all of an employee's information, with a button at the end that
        //Corresponds to that employee.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 13));
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
        JButton d = new JButton("View Payment History");
        payHistButtonsToIDs.put(d, id);
        panel.add(d);
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

}
