import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeSummaryGUI implements ActionListener{

    private JFrame frame = new JFrame();

    private HashMap<JButton, Integer> buttons_to_ids = new HashMap<JButton, Integer>();

    private JPanel titlePanel = new JPanel();

    public EmployeeSummaryGUI(){

        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Active Employee Summary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        titlePanel.setLayout(new GridLayout(1, 12));
        titlePanel.add(new JLabel("First Name:"));
        titlePanel.add(new JLabel("Surname:"));
        titlePanel.add(new JLabel("Gender:"));
        titlePanel.add(new JLabel("Email:"));
        titlePanel.add(new JLabel("Phone Number:"));
        titlePanel.add(new JLabel("Role:"));
        titlePanel.add(new JLabel("ID Number:"));
        titlePanel.add(new JLabel("Birthday:"));
        titlePanel.add(new JLabel("Type:"));
        titlePanel.add(new JLabel("Salary/Wage:"));
        titlePanel.add(new JLabel("Pay Employee:"));
        titlePanel.add(new JLabel("View/Edit Schedule:"));
        all_panels.add(titlePanel);

        UserInteractor ui = new UserInteractor();

        ArrayList<Employee> employees = ui.getEmployeeList();

        for (Employee employee: employees){
            if (employee.isActive()){
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 12));
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
                if (type.equals("Volunteer")){
                    panel.add(new JLabel("0"));
                } else if (type.equals("WageWorker")){
                    panel.add(new JLabel(Float.toString(((WageWorker)employee).getHourlyWage())));
                } else{
                    panel.add(new JLabel(Float.toString(((SalaryWorker)employee).getYearlySalary())));
                }

                all_panels.add(panel);
            }


        }

        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
