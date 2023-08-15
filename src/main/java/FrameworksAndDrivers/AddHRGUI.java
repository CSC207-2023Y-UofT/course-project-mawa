package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceAdapters.*;


/**
 * The AddHRGUI class represents a graphical user interface for adding HR accounts.
 * It allows HR users to input employee information and add them to the system.
 */
public class AddHRGUI implements ActionListener, Page {


    private UserController uc = new UserController();

    private JFrame frame = new JFrame();
    private JLabel firstNameLab = new JLabel("Given Name:");

    private JLabel surnameLab = new JLabel("Surname:");

    private JLabel phoneLab = new JLabel("Phone Number (Only Integers Accepted):");


    private JLabel genderLab = new JLabel("Gender:");

    private JLabel emailLab = new JLabel("Email:");

    private JLabel pwdLab = new JLabel("Password:");

    private JLabel dobyrLab = new JLabel("Birth Year (YYYY)");

    private JLabel dobmthLab = new JLabel("Birth Month (MM)");

    private JLabel dobdayLab = new JLabel("Birth Date (DD)");

    private JTextField firstNameEnter = new JTextField(12);

    private JTextField surnameEnter = new JTextField(12);

    private JTextField phoneEnter = new JTextField(12);


    private JTextField genderEnter = new JTextField(12);

    private JTextField emailEnter = new JTextField(12);


    private JTextField pwdEnter = new JTextField(12);

    private JTextField dobyrEnter = new JTextField(12);

    private JTextField dobmthEnter = new JTextField(12);

    private JTextField dobdayEnter = new JTextField(12);


    private JPanel questionPanel = new JPanel();

    private JPanel submitPanel = new JPanel();

    private JPanel dobPanel = new JPanel();

    private JButton submitButton = new JButton("Enter");

    private Container contentPane = frame.getContentPane();



    /**
     * Constructs the AddHRGUI instance and initializes the GUI components.
     */
    public AddHRGUI(){
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
        if (s.equals(submitButton)){
            String phnum = phoneEnter.getText();
            String byr = dobyrEnter.getText();
            String bmth = dobmthEnter.getText();
            String bd = dobdayEnter.getText();
            //Same verifications as in the AddEmployeeGUI.
            if(phnum.matches("\\d+") && byr.matches("\\d+") && bmth.matches("\\d+")
                    && bd.matches("\\d+")){
                uc.userFromInput(surnameEnter.getText(), firstNameEnter.getText(), genderEnter.getText(), byr, bmth, bd,
                        Long.parseLong(phnum.trim()), emailEnter.getText(), "HR Worker", "HR",
                        pwdEnter.getText(), (float) 0);

                frame.dispose();
                new Login();
                JOptionPane.showMessageDialog(null, "HR account has been added.", "", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }


    @Override
    public void addTitle() {
        frame.setTitle("Add HR Account");
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
        this.questionPanel.add(pwdLab);
        this.questionPanel.add(pwdEnter);
        submitButton.addActionListener(this);;
        this.submitPanel.add(submitButton);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(questionPanel);
        contentPane.add(dobPanel);
        contentPane.add(submitPanel);
    }

    @Override
    public void setUser(int user) {
        // Not used in this context
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

