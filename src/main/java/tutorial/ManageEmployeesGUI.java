package tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManageEmployeesGUI implements ActionListener{


  private JFrame frame = new JFrame();
  private JButton addEmployee = new JButton("Add Employee");

  private JButton completeEmployeeList = new JButton("Complete Employee List");
  private JButton employeeSummary = new JButton("Employee Summary");
  private JPanel buttonsPanel = new JPanel();


public ManageEmployeesGUI(){

  frame.setSize(600, 600);
  frame.setVisible(true);
  frame.setTitle("Manage Employees");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.buttonsPanel.setLayout(new GridLayout(3, 1));
  addEmployee.addActionListener(this);
  completeEmployeeList.addActionListener(this);
  employeeSummary.addActionListener(this);
  this.buttonsPanel.add(addEmployee);
  this.buttonsPanel.add(completeEmployeeList);
  this.buttonsPanel.add(employeeSummary);
  frame.add(buttonsPanel);
  
}


@Override
public void actionPerformed(ActionEvent e){
  Object source = e.getSource();
  if (source instanceof JButton){
    if (source.equals(addEmployee)) {
      new AddEmployeeGUI();
    } else if (source.equals(completeEmployeeList)){
      new completeEmployeeListGUI();
    } else {
      new employeeSummaryGUI();
    }

    frame.dispose();

  }
}


}


