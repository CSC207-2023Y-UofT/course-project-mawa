package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceAdapters.*;
import FrameworksAndDrivers.*;

public class ManageEmployeesGUI implements ActionListener, Page {


  private JFrame frame = new JFrame();
  private JButton addEmployee = new JButton("Add Entities.Employee");

  private JButton completeEmployeeList = new JButton("Complete Entities.Employee List");
  private JButton employeeSummary = new JButton("Entities.Employee Summary");
  private JPanel buttonsPanel = new JPanel();


public ManageEmployeesGUI(){

  frame.setSize(600, 600);
  frame.setVisible(true);
  this.addTitle();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.addContent();
  
}


@Override
public void actionPerformed(ActionEvent e){
  Object source = e.getSource();
    if (source.equals(addEmployee)) {
      new AddEmployeeGUI();
      frame.dispose();
    } else if (source.equals(completeEmployeeList)){
      new CompleteUserListGUI();
      frame.dispose();
    } else if (source.equals(employeeSummary)) {
      new EmployeeSummaryGUI();
      frame.dispose();
    }

}

  @Override
  public void addTitle() {
    frame.setTitle("Manage Employees");
  }

  @Override
  public void addContent() {
    this.buttonsPanel.setLayout(new GridLayout(3, 1));
    addEmployee.addActionListener(this);
    completeEmployeeList.addActionListener(this);
    employeeSummary.addActionListener(this);
    this.buttonsPanel.add(addEmployee);
    this.buttonsPanel.add(completeEmployeeList);
    this.buttonsPanel.add(employeeSummary);
    this.buttonsPanel.setSize(100,300);
    frame.add(buttonsPanel);
  }
}


