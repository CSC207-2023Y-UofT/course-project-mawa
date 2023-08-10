package FrameworksAndDrivers;

import InterfaceAdapters.*;
import javax.swing.*;
import java.util.ArrayList;

public class Login implements Page{
    private JLabel labelEmpNum = new JLabel("Enter Employee Number: ");
    private JLabel labelPwd = new JLabel("Enter Password: ");
    private JTextField empNumIn = new JTextField(7);
    private JPasswordField pwdIn = new JPasswordField(7);
    private ArrayList<Object> options = new ArrayList<Object>();
    private LoginPresenter presenter;

    private int result;

    public Login(){
        presenter = new LoginPresenter(this);
        addTitle();
        addContent();
    }

    public Login(String msg){
        addTitle();
        options.add(msg);
        addContent();
    }

    public static void main(String[] args){

        new Login();

    }

    @Override
    public void addTitle() {
        options.add("Enter Credentials");
        options.add(labelEmpNum);
        options.add(empNumIn);
        options.add(labelPwd);
        options.add(pwdIn);
    }

    @Override
    public void addContent() {
        JOptionPane pane = new JOptionPane(options.toArray(new Object[0]), JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Login");

        dialog.setResizable(true);
        dialog.setVisible(true);
        result = presenter.handleUser(Integer.parseInt(empNumIn.getText()), pwdIn.getPassword());
    }

    @Override
    public void setUser(int user) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void addHomeButton() {

    }

    @Override
    public void update() {
        if (result == -1){
            new Login("Incorrect/non-existent credentials");
        }else{
            new HomePage(result);
        }

    }
}