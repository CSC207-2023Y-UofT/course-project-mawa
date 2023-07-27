import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.AddEmployee;

public class ManageEmployeesGUI extends Jframe implements ActionListener{
  private JButton addEmployee = new JButton("Add Employee")  
  private JButton completeEmployeeList = new JButton("Complete Employee List")
  private JButton employeeSummary = new JButton("Employee Summary")
  private JPanel buttonsPanel = new JPanel();


public static void main(String args[]){

  this.setVisible(True);
  
}


public ManageEmployeesGUI(){
  
  super();
  this.setSize(600, 600);
  this.setVisible(true);
  this.setTitle("Notifications");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  buttonsPanel.setLayout(new Gridlayout(3, 1));
  this.buttonsPanel.add(addEmployee);
  this.buttonsPanel.add(completeEmployeeList);
  this.buttonsPanel.add(employeeSummary);
  this.add(buttonsPanel);
  
}

private void addEmployeeActionPeformed(ActionEvent e){
  if (e instanceOf MouseEvent){
    if (e.getButton() = MouseEvent.BUTTON1){
      AddEmployee.main(null)
      this.dispose;
    }
  }
}


}


