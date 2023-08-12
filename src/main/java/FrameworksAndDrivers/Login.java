package FrameworksAndDrivers;

import InterfaceAdapters.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 * The Login class represents a graphical user interface for user login.
 * It implements the Page interface.
 */
public class Login implements Page{
    private JLabel labelEmpNum = new JLabel("Enter Employee Number: ");
    private JLabel labelPwd = new JLabel("Enter Password: ");
    private JTextField empNumIn = new JTextField(7);
    private JPasswordField pwdIn = new JPasswordField(7);
    private ArrayList<Object> options = new ArrayList<Object>();
    private LoginPresenter presenter;

    private int result;
    /**
     * Constructs a Login object with default message.
     */
    public Login(){
        presenter = new LoginPresenter(this);
        addTitle();
        addContent();
    }
    /**
     * Constructs a Login object with a custom message.
     *
     * @param msg The custom message to display.
     */
    public Login(String msg){
        presenter = new LoginPresenter(this);
        addTitle();
        options.add(msg);
        addContent();
    }
    @Override
    public void addTitle() {
        options.add("Enter Credentials");
        options.add(labelEmpNum);
        options.add(empNumIn);
        options.add(labelPwd);
        options.add(pwdIn);
    }
    /**
     * Shows the content of the login dialog box and handles the user login process.
     * Displays a JOptionPane dialog with input fields for employee number and password.
     * After user input, checks credentials using the LoginPresenter and updates the result.
     */
    @Override
    public void addContent() {
        JOptionPane pane = new JOptionPane(options.toArray(new Object[0]), JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Login");

        dialog.setResizable(true);
        dialog.setVisible(true);
        result = presenter.handleUser(Integer.parseInt(empNumIn.getText()), pwdIn.getPassword());
        update();
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
    /**
     * Updates the user interface based on the login result.
     * If the login credentials are incorrect or non-existent, displays a new login dialog with an error message.
     * Otherwise, navigates to HomePage with the authenticated user's Id.
     */
    @Override
    public void update() {
        if (result == -1){
            new Login("Incorrect/non-existent credentials");
        }else{
            new HomePage(result);
        }

    }
}