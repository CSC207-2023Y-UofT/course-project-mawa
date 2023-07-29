package tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.EmployeeDataBaseInteractor;

public class completeEmployeeListGUI implements ActionListener{

    private JFrame frame = new JFrame();

    private JPanel titlePanel = new JPanel();

    private Container contentPane = frame.getContentPane();

    public completeEmployeeListGUI(){

        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("Complete Employee List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        titlePanel.setLayout(new GridLayout(1, 11));
        titlePanel.add(new JLabel("First Name:"));
        titlePanel.add(new JLabel("Surname:"));
        titlePanel.add(new JLabel("Gender:"));
        titlePanel.add(new JLabel("Email:"));
        titlePanel.add(new JLabel("Role:"));
        titlePanel.add(new JLabel("ID Number:"));
        titlePanel.add(new JLabel("Birthday:"));
        titlePanel.add(new JLabel("Type:"));
        titlePanel.add(new JLabel("Pay:"));
        titlePanel.add(new JLabel("Active:"));
        titlePanel.add(new JLabel("Change Status:"));
        all_panels.add(titlePanel);
        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);

        //edp = new EmployeeDataBaseInteractor();
        //employees = edb.readData();
        //ArrayList<JPanel> employeePanels = new ArrayList<>();
        //for (Employee employee: employees){
        //    panel = new JPanel();

        //}



    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
