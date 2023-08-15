package FrameworksAndDrivers;

import InterfaceAdapters.Page;
import InterfaceAdapters.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The AddEmployeeGUI class represents a graphical user interface for adding employees.
 * It allows HR users to input employee information and add them to the system.
 */
public class AddEmployeeGUI implements ActionListener, Page {

    private int viewerID;

    private UserController uc = new UserController();

    private JFrame frame = new JFrame();
    private JLabel firstNameLab = new JLabel("Given Name:");

    private JLabel surnameLab = new JLabel("Surname:");

    private JLabel phoneLab = new JLabel("Phone Number (Only Integers Accepted):");


    private JLabel genderLab = new JLabel("Gender:");

    private JLabel emailLab = new JLabel("Email:");

    private JLabel roleLab = new JLabel("Role Description:");

    private JLabel pwdLab = new JLabel("Password:");

    private JLabel typeLab = new JLabel("Employee Type:");

    private JLabel payLab = new JLabel("Hourly wage");

    private JLabel dobyrLab = new JLabel("Birth Year (YYYY)");

    private JLabel dobmthLab = new JLabel("Birth Month (MM)");

    private JLabel dobdayLab = new JLabel("Birth Date (DD)");

    private JTextField firstNameEnter = new JTextField(12);

    private JTextField surnameEnter = new JTextField(12);

    private JTextField phoneEnter = new JTextField(12);

    private JTextField roleEnter = new JTextField(12);

    private JTextField genderEnter = new JTextField(12);

    private JTextField emailEnter = new JTextField(12);


    private JTextField pwdEnter = new JTextField(12);

    private JTextField dobyrEnter = new JTextField(12);

    private JTextField dobmthEnter = new JTextField(12);

    private JTextField dobdayEnter = new JTextField(12);

    private JComboBox<String> typeSelect = new JComboBox<>(new String[]{"Wage Worker", "Salary Worker", "Volunteer"});

    private JTextField payEnter = new JTextField(12);

    private JPanel questionPanel = new JPanel();

    private JPanel submitPanel = new JPanel();

    private JPanel dobPanel = new JPanel();

    private JButton submitButton = new JButton("Enter");

    private Container contentPane = frame.getContentPane();

    private JButton back = new JButton("Back");

    private JPanel backPanel = new JPanel();




    /**
     * Constructs the AddEmployeeGUI instance and initializes the GUI components.
     *
     * @param id The ID of the user accessing the GUI.
     */
    public AddEmployeeGUI(int id){
        this.setUser(id);
        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks and user interactions here

        Object s = e.getSource();
        //Verify that the salary entered is a valid number.
        boolean pay_numeric = true;
        if (payLab.isVisible()){
            try {
                Double num = Double.parseDouble(payEnter.getText());
            } catch (NumberFormatException g) {
                pay_numeric = false;
            }
        }
        if (s.equals(submitButton)){
            String phnum = phoneEnter.getText();
            String byr = dobyrEnter.getText();
            String bmth = dobmthEnter.getText();
            String bd = dobdayEnter.getText();
            //Verify that the given phone number, and birthday information is only made of integers.
            if(phnum.matches("\\d+") && byr.matches("\\d+") && bmth.matches("\\d+")
                    && bd.matches("\\d+") && pay_numeric && typeSelect.getSelectedItem() != null){

                //Make the user, given that the input is valid.
                uc.userFromInput(surnameEnter.getText(), firstNameEnter.getText(), genderEnter.getText(), byr, bmth,
                            bd, Long.parseLong(phnum.trim()), emailEnter.getText(), roleEnter.getText(),
                            (String) typeSelect.getSelectedItem(), pwdEnter.getText(),
                            Float.parseFloat(payEnter.getText()));


                //Go back to employee management afterward.
                new ManageEmployeesGUI(viewerID);
                frame.dispose();
                //Confirmation message.
                JOptionPane.showMessageDialog(null, "Employee has been added.", "", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        //Here is the handling of the drop-down menu for the type selection.
        if (s.equals(typeSelect)){
            if (typeSelect.getSelectedItem() == "Wage Worker"){
                payLab.setVisible(true);
                payEnter.setVisible(true);
                payLab.setText("Hourly Wage ($), Numbers Only:");
            } else if (typeSelect.getSelectedItem() == "Salary Worker"){
                payLab.setVisible(true);
                payEnter.setVisible(true);
                payLab.setText("Yearly Salary ($), Numbers Only:");
            } else if (typeSelect.getSelectedItem() == "Volunteer"){
                payLab.setVisible(false);
                payEnter.setVisible(false);
            }
        }
        //The handling of the back button
        if (s.equals(back)){
            new ManageEmployeesGUI(viewerID);
            frame.dispose();
        }
    }


    @Override
    public void addTitle() {
        frame.setTitle("Add Employee");
    }

    @Override
    public void addContent() {
        // Set layout and add UI components to panels
        this.questionPanel.setLayout(new GridLayout(0, 2));
        this.dobPanel.setLayout(new GridLayout(1, 6));
        dobPanel.add(dobyrLab);
        dobPanel.add(dobyrEnter);
        dobPanel.add(dobmthLab);
        dobPanel.add(dobmthEnter);
        dobPanel.add(dobdayLab);
        dobPanel.add(dobdayEnter);
        backPanel.setLayout(new GridLayout(1, 1));
        backPanel.add(back);
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.LINE_AXIS));
        submitPanel.add(Box.createHorizontalGlue());

        this.questionPanel.add(firstNameLab);
        this.questionPanel.add(firstNameEnter);
        this.questionPanel.add(surnameLab);
        this.questionPanel.add(surnameEnter);
        this.questionPanel.add(phoneLab);
        this.questionPanel.add(phoneEnter);
        this.questionPanel.add(genderLab);
        this.questionPanel.add(genderEnter);
        this.questionPanel.add(emailLab);
        this.questionPanel.add(emailEnter);
        this.questionPanel.add(roleLab);
        this.questionPanel.add(roleEnter);
        this.questionPanel.add(pwdLab);
        this.questionPanel.add(pwdEnter);
        this.questionPanel.add(typeLab);
        this.questionPanel.add(typeSelect);
        this.questionPanel.add(payLab);
        this.questionPanel.add(payEnter);
        typeSelect.addActionListener(this);
        submitButton.addActionListener(this);
        back.addActionListener(this);
        this.submitPanel.add(submitButton);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(backPanel);
        contentPane.add(questionPanel);
        contentPane.add(dobPanel);
        contentPane.add(submitPanel);
    }

    @Override
    public void setUser(int user) {
        this.viewerID = user;
    }

    @Override
    public void dispose() {
        // Not used in this context
    }

    @Override
    public void addHomeButton() {
        // Not used in this context
    }

    @Override
    public void update() {
        // Not used in this context
    }
}


