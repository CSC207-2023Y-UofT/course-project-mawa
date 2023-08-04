package InterfaceAdapters;

import Entities.Employee;
import Entities.SalaryWorker;
import Entities.WageWorker;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class EmployeeSummaryPresenter{

    //Each button must correspond to a different employee, so this maps makes that association.
    private HashMap<JButton, Integer> payHistButtonsToIDs = new HashMap<JButton, Integer>();

    public JPanel makeEmployeePanel(Employee employee) {
        //Make a panel containing all of an employee's information, with a button at the end that
        //Corresponds to that employee.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 13));
        panel.add(new JLabel(employee.getFirstname()));
        panel.add(new JLabel(employee.getSurname()));
        panel.add(new JLabel(employee.getGender()));
        panel.add(new JLabel(employee.getEmail()));
        panel.add(new JLabel(Long.toString(employee.getPhoneNum())));
        panel.add(new JLabel(employee.getRoleName()));
        panel.add(new JLabel(Integer.toString(employee.getUserNum())));
        panel.add(new JLabel(employee.getDob().toString()));
        String type = employee.getClass().getName();
        panel.add(new JLabel(type));
        if (type.equals("Entities.Volunteer")) {
            panel.add(new JLabel("0"));
        } else if (type.equals("Entities.WageWorker")) {
            panel.add(new JLabel(Float.toString(((WageWorker) employee).getHourlyWage())));
        } else {
            panel.add(new JLabel(Float.toString(((SalaryWorker) employee).getYearlySalary())));
        }
        JButton d = new JButton("View Payment History");
        payHistButtonsToIDs.put(d, employee.getUserNum());
        panel.add(d);
        return panel;

    }

    public ArrayList<JPanel> makeEmployeePanels(){
        //Get employees from the database, and if they are active, add their panel to a list, which
        //is returned.
        UserInteractor ui = new UserInteractor();
        ArrayList<Employee> employees = ui.getEmployeeList();
        ArrayList<JPanel> panels = new ArrayList<>();
        for (Employee employee: employees){
            if (employee.isActive()){
                panels.add(this.makeEmployeePanel(employee));
            }

        }
        return panels;
    }

    public HashMap<JButton, Integer> getMap(){
        return this.payHistButtonsToIDs;
    }

}
