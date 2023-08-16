package InterfaceAdapters;

import UseCases.PaymentMaker;
import UseCases.UserFileReader;

import java.util.ArrayList;

/**
 * The EmployeeSummaryPresenter class handles presenting employee summary data for display.
 * It creates arrays of employee information and lists of arrays for UI rendering.
 * This class is part of the MVP design pattern.
 */
public class EmployeeSummaryPresenter {

    private UserFileReader fr;

    public EmployeeSummaryPresenter(){
        fr = UserFileReader.getInstance();
    }

    public EmployeeSummaryPresenter(String test){
        fr = new UserFileReader(test);
    }


    /**
     * Creates an array of employee information for display.
     *
     * @param id The ID of the employee for whom to create the array.
     * @return An array of objects representing employee information for display.
     */
    public Object[] makeEmployeePanel(int id) {
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

        if (fr.getType(id).equals("Volunteer")) {
            employeePanelList[10] = 0;
        } else {
            employeePanelList[10] = id;
        }

        return employeePanelList;
    }

    /**
     * Creates arrays of employee information for active employees (excluding HR) for display.
     *
     * @return An ArrayList of arrays of objects, each representing employee information for display.
     */
    public ArrayList<Object[]> makeEmployeePanels() {
        ArrayList<Object[]> lists = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();

        for (Integer user : users) {
            if (!fr.getType(user).equals("HR") && fr.getActive(user)) {
                lists.add(this.makeEmployeePanel(user));
            }
        }

        return lists;
    }


    /**
     * Retrieves the name of an employee based on their ID.
     *
     * @param id The ID of the employee.
     * @return The full name of the employee.
     */
    public String getName(int id) {
        UserFileReader ufr = UserFileReader.getInstance();
        return ufr.getFirstName(id) + " " + ufr.getSurname(id);
    }

    public UserFileReader getFr(){return this.fr;}


}
