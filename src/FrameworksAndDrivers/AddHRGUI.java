package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceAdapters.*;
import FrameworksAndDrivers.*;


public class AddHRGUI implements ActionListener, Page {


    private int viewerID;

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




    public AddHRGUI(int id){
        this.viewerID = id;
        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        UserFactory uf = new UserFactory();
        Object s = e.getSource();
        if (s.equals(submitButton)){
            String phnum = phoneEnter.getText();
            String byr = dobyrEnter.getText();
            String bmth = dobmthEnter.getText();
            String bd = dobdayEnter.getText();
            if(phnum.matches("\\d+") && byr.matches("\\d+") && bmth.matches("\\d+")
                    && bd.matches("\\d+")){
                uf.makeUser(surnameEnter.getText(), firstNameEnter.getText(), genderEnter.getText(), byr, bmth, bd,
                        Long.parseLong(phnum.trim()), emailEnter.getText(), "", "HR",
                        pwdEnter.getText(), 0);

                //Go back to HR home page.
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Employee has been added.", "", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        if (s.equals(Page.back)){
            //Go back to HR home page.
            frame.dispose();
        }
    }


    @Override
    public void addTitle() {
        frame.setTitle("Add HR Account");
    }

    @Override
    public void addContent() {
        this.questionPanel.setLayout(new GridLayout(0, 2));
        this.dobPanel.setLayout(new GridLayout(1, 6));
        dobPanel.add(dobyrLab);
        dobPanel.add(dobyrEnter);
        dobPanel.add(dobmthLab);
        dobPanel.add(dobmthEnter);
        dobPanel.add(dobdayLab);
        dobPanel.add(dobdayEnter);
        Page.backPanel.setLayout(new GridLayout(1, 1));
        Page.backPanel.add(Page.back);
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
        submitButton.addActionListener(this);
        Page.back.addActionListener(this);
        this.submitPanel.add(submitButton);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Page.backPanel);
        contentPane.add(questionPanel);
        contentPane.add(dobPanel);
        contentPane.add(submitPanel);
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

    }
}

