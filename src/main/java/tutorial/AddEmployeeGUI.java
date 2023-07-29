package tutorial;

import org.hibernate.type.TrueFalseType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddEmployeeGUI implements ActionListener{

    private JFrame frame = new JFrame();
    private JLabel firstNameLab = new JLabel("Given Name:");

    private JLabel surnameLab = new JLabel("Surname:");

    private JLabel phoneLab = new JLabel("Phone Number (Only Integers Accepted):");

    private JLabel genderLab = new JLabel("Gender:");

    private JLabel emailLab = new JLabel("Email:");

    private JLabel roleLab = new JLabel("Role:");

    private JLabel pwdLab = new JLabel("Password:");

    private JLabel typeLab = new JLabel("Employee Type:");

    private JLabel payLab = new JLabel("Hourly wage");

    private JLabel dobyrLab = new JLabel("Birth Year (YYYY)");

    private JLabel dobmthLab = new JLabel("Birth Month (MM)");

    private JLabel dobdayLab = new JLabel("Birth Date (DD)");

    private JTextField firstNameEnter = new JTextField(12);

    private JTextField surnameEnter = new JTextField(12);

    private JTextField phoneEnter = new JTextField(12);

    private JTextField genderEnter = new JTextField(12);

    private JTextField emailEnter = new JTextField(12);

    private JTextField roleEnter = new JTextField(12);

    private JTextField pwdEnter = new JTextField(12);

    private JTextField dobyrEnter = new JTextField(12);

    private JTextField dobmthEnter = new JTextField(12);

    private JTextField dobdayEnter = new JTextField(12);

    private JComboBox typeSelect = new JComboBox(new String[]{"Wage Worker", "Salaried Employee", "Volunteer"});

    private JTextField payEnter = new JTextField(12);

    private JPanel questionPanel = new JPanel();

    private JPanel submitPanel = new JPanel();

    private JPanel dobPanel = new JPanel();

    private JPanel firstNamePanel = new JPanel();
    private JButton submitButton = new JButton("Enter");

    private Container contentPane = frame.getContentPane();

    private JButton back = new JButton("Back");

    private  JPanel backPanel = new JPanel();



    public AddEmployeeGUI(){
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setTitle("AddEmployee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(submitButton)){
            String phnum = phoneEnter.getText();
            boolean phnum_numeric = phnum.matches("\\d+");
            if (payLab.isVisible()){
                boolean pay_numeric = true;
                try {
                    Double num = Double.parseDouble(payEnter.getText());
                } catch (NumberFormatException g) {
                    pay_numeric = false;
                }
            }

        }

        if (s.equals(typeSelect)){
            if (typeSelect.getSelectedItem() == "Wage Worker"){
                payLab.setVisible(true);
                payEnter.setVisible(true);
                payLab.setText("Hourly Wage ($), Numbers Only:");
            } else if (typeSelect.getSelectedItem() == "Salaried Employee"){
                payLab.setVisible(true);
                payEnter.setVisible(true);
                payLab.setText("Yearly Salary ($), Numbers Only:");
            } else if (typeSelect.getSelectedItem() == "Volunteer"){
                payLab.setVisible(false);
                payEnter.setVisible(false);
            }
        }
        if (s.equals(back)){
            new ManageEmployeesGUI();
            frame.dispose();
        }
    }



}
