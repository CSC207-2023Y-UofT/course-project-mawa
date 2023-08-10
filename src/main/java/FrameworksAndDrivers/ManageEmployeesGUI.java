package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceAdapters.*;
import FrameworksAndDrivers.*;

public class ManageEmployeesGUI implements ActionListener, Page {

  private int viewerID;
  private JFrame frame = new JFrame();

  private JButton back = new JButton("Back");
  private JButton addEmployee = new JButton("Add Employee");

  private JButton completeEmployeeList = new JButton("Complete Employee List");
  private JButton employeeSummary = new JButton("Employee Summary");
  private JPanel buttonsPanel = new JPanel();


public ManageEmployeesGUI(int id){
  //Create the UI by combining the title and button components
  this.viewerID = id;
  frame.setSize(600, 600);
  frame.setVisible(true);
  this.addTitle();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.addContent();
  
}


@Override
public void actionPerformed(ActionEvent e){
  //Based on which button is clicked, we wish to redirect to a particular page.
  Object source = e.getSource();
    if (source.equals(addEmployee)) {
      new AddEmployeeGUI(viewerID);
      frame.dispose();
    } else if (source.equals(completeEmployeeList)){
      new CompleteUserListGUI(viewerID);
      frame.dispose();
    } else if (source.equals(employeeSummary)) {
      new EmployeeSummaryGUI(viewerID);
      frame.dispose();
    } else if (source.equals(back)){
      new HomePage(viewerID);
      frame.dispose();
    }

}

  @Override
  public void addTitle() {
    frame.setTitle("Manage Employees");
  }

  @Override
  public void addContent() {
    //Make the buttons respond to a click, and add them to the button panel.
    this.buttonsPanel.setLayout(new GridLayout(4, 1));
    addEmployee.addActionListener(this);
    completeEmployeeList.addActionListener(this);
    employeeSummary.addActionListener(this);
    back.addActionListener(this);
    this.buttonsPanel.add(back);
    this.buttonsPanel.add(addEmployee);
    this.buttonsPanel.add(completeEmployeeList);
    this.buttonsPanel.add(employeeSummary);
    this.buttonsPanel.setSize(100,300);
    frame.add(buttonsPanel);
  }

  @Override
  public void setUser(int user) {
  /*We need this to keep track of who is viewing the page.*/
    this.viewerID = user;
  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  @Override
  public void addHomeButton() {

  }

  @Override
  public void update() {

  }
}


