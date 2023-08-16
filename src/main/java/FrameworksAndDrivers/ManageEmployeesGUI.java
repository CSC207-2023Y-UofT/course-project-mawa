package FrameworksAndDrivers;

import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ManageEmployeesGUI class provides a user interface for managing employees.
 * Users can navigate to different employee-related pages from this interface.
 */
public class ManageEmployeesGUI implements ActionListener, Page {

  private int viewerID;
  private JFrame frame = new JFrame();

  private JButton back = new JButton("Back");
  private JButton addEmployee = new JButton("Add Employee");

  private JButton completeEmployeeList = new JButton("Complete User List");
  private JButton employeeSummary = new JButton("Employee Summary");
  private JPanel buttonsPanel = new JPanel();


  /**
   * Creates an instance of the ManageEmployeesGUI class.
   *
   * @param id The ID of the user viewing the page.
   */
  public ManageEmployeesGUI(int id) {
    frame.setSize(600, 600);
    frame.setVisible(true);
    this.addTitle();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.addContent();
    this.viewerID = id;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //Open the appropriate new page based on the button clicked.
    Object source = e.getSource();
    if (source.equals(addEmployee)) {
      new AddEmployeeGUI(viewerID);
      frame.dispose();
    } else if (source.equals(completeEmployeeList)) {
      new CompleteUserListGUI(viewerID);
      frame.dispose();
    } else if (source.equals(employeeSummary)) {
      new EmployeeSummaryGUI(viewerID);
      frame.dispose();
    } else if (source.equals(back)) {
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
    //Add the buttons to the frame.
    this.buttonsPanel.setLayout(new GridLayout(4, 1));
    addEmployee.addActionListener(this);
    completeEmployeeList.addActionListener(this);
    employeeSummary.addActionListener(this);
    back.addActionListener(this);
    this.buttonsPanel.add(back);
    this.buttonsPanel.add(addEmployee);
    this.buttonsPanel.add(completeEmployeeList);
    this.buttonsPanel.add(employeeSummary);
    this.buttonsPanel.setSize(100, 300);
    frame.add(buttonsPanel);
  }

  @Override
  public void setUser(int user) {
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
