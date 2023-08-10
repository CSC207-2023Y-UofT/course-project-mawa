package FrameworksAndDrivers;

import InterfaceAdapters.LoginValidator;
import InterfaceAdapters.UserFileReader;

import javax.swing.*;
import java.util.ArrayList;

public class Login{
    private JLabel labelEmpNum = new JLabel("Enter Employee Number: ");
    private JLabel labelPwd = new JLabel("Enter Password: ");
    private JTextField empNumIn = new JTextField(7);
    private JPasswordField pwdIn = new JPasswordField(7);
    private ArrayList<Object> options = new ArrayList<Object>();
    private UserFileReader reader;

    public Login(){
        System.out.println("Invalid File Name.");
        setUpOptions();
        setUp();
    }

    public Login(String msg){
        setUpOptions();
        options.add(msg);
        setUp();
    }

    private void setUpOptions() {
        options.add("Enter Credentials");
        options.add(labelEmpNum);
        options.add(empNumIn);
        options.add(labelPwd);
        options.add(pwdIn);
    }
    private void setUp(){
        JOptionPane pane = new JOptionPane(options.toArray(new Object[0]), JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Login");

        dialog.setResizable(true);
        dialog.setVisible(true);
        int user = new LoginValidator().validateCredentials(Integer.parseInt(empNumIn.getText()),
                pwdIn.getPassword());
        handleUser(user);
    }

    private void handleUser(int user){
        if(user <= 0){
            new Login("Incorrect/non-existent credentials");
        } else {
            new HomePage(user);
        }
    }
    public static void main(String[] args){
        new Login();
    }


}
