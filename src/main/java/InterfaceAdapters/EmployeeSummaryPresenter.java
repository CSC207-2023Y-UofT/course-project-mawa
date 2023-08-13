package InterfaceAdapters;
import UseCases.PaymentMaker;
import UseCases.UserFileReader;

import java.util.ArrayList;

public class EmployeeSummaryPresenter{

    private UserFileReader fr = UserFileReader.getInstance();

    public Object[] makeEmployeePanel(int id) {
        //Make a panel containing all of an employee's information, with a button at the end that
        //Corresponds to that employee.

        Object[] employeePanelList = new Object[11];
        employeePanelList[0] = fr.getFirstName(id);
        employeePanelList[1] = fr.getSurname(id);
        employeePanelList[2] = fr.getGender(id);
        employeePanelList[3] = fr.getEmail(id);
        employeePanelList[4] = Long.toString(fr.getPhoneNumber(id));
        employeePanelList[5] = fr.getRole(id);
        employeePanelList[6] = Integer.toString(id);
        employeePanelList[7] = fr.getDob(id).toString();
        employeePanelList[8] = fr.getType(id);
        employeePanelList[9] = Float.toString(fr.getPay(id));
        if (fr.getType(id).equals("Volunteer")){
            employeePanelList[10] = 0;
        } else{
            employeePanelList[10] = id;
        }
        return employeePanelList;

        /*
        panel.setLayout(new GridLayout(1, 12));

        return panel;*/

    }

    public ArrayList<Object[]> makeEmployeePanels(){
        //Get employees from the database, and if they are active, add their panel to a list, which
        //is returned.
        ArrayList<Object[]> lists = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();
        for (Integer user: users){
            if (!fr.getType(user).equals("HR") && fr.getActive(user)){
                lists.add(this.makeEmployeePanel(user));
            }

        }
        return lists;
    }


    public void makePayment(int id){
        PaymentMaker p = new PaymentMaker(id);
        p.makePayment();
    }

    public String getName(int id){
        UserFileReader ufr = UserFileReader.getInstance();
        return ufr.getFirstName(id) + " " + ufr.getSurname(id);
    }

}
