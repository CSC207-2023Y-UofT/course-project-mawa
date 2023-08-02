import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompleteUserListGUI implements ActionListener, Page{

    private JFrame frame = new JFrame();

    private HashMap<JButton, Integer> buttonsToIDs = new HashMap<JButton, Integer>();

    private JPanel titlePanel = new JPanel();


    public CompleteUserListGUI(){

        frame.setSize(600, 600);
        frame.setVisible(true);
        this.addTitle();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addContent();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (buttonsToIDs.containsKey(source)){
            UserFactory uf = new UserFactory();
            uf.changeActivation(buttonsToIDs.get(source));
        }
    }

    @Override
    public void addTitle() {
        frame.setTitle("Complete User List");
    }

    public void makeHeader(){
        titlePanel.setLayout(new GridLayout(1, 12));
        titlePanel.add(new JLabel("First Name:"));
        titlePanel.add(new JLabel("Surname:"));
        titlePanel.add(new JLabel("Gender:"));
        titlePanel.add(new JLabel("Email:"));
        titlePanel.add(new JLabel("Phone Number:"));
        titlePanel.add(new JLabel("Role:"));
        titlePanel.add(new JLabel("ID Number:"));
        titlePanel.add(new JLabel("Birthday:"));
        titlePanel.add(new JLabel("Type:"));
        titlePanel.add(new JLabel("Salary/Wage:"));
        titlePanel.add(new JLabel("Active:"));
        titlePanel.add(new JLabel("Change Status:"));
    }

    public JPanel makeUserPanel(User user){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 12));
        panel.add(new JLabel(user.getFirstname()));
        panel.add(new JLabel(user.getSurname()));
        panel.add(new JLabel(user.getGender()));
        panel.add(new JLabel(user.getEmail()));
        panel.add(new JLabel(Long.toString(user.getPhoneNum())));
        panel.add(new JLabel(user.getRoleName()));
        panel.add(new JLabel(Integer.toString(user.getUserNum())));
        panel.add(new JLabel(user.getDob().toString()));
        String type = user.getClass().getName();
        panel.add(new JLabel(type));
        if (type.equals("Volunteer")){
            panel.add(new JLabel("0"));
        } else if (type.equals("WageWorker")){
            panel.add(new JLabel(Float.toString(((WageWorker)user).getHourlyWage())));
        } else{
            panel.add(new JLabel(Float.toString(((SalaryWorker)user).getYearlySalary())));
        }
        if (user.isActive()){
            panel.add(new JLabel("Yes"));
            JButton b = new JButton("Deactivate");
            buttonsToIDs.put(b, user.getUserNum());
            panel.add(b);
        } else{
            panel.add(new JLabel("No"));
            JButton b = new JButton("Re-Activate");
            buttonsToIDs.put(b, user.getUserNum());
            panel.add(b);
        }
        return panel;
    }

    @Override
    public void addContent() {
        JPanel all_panels = new JPanel();
        all_panels.setLayout(new BoxLayout(all_panels, BoxLayout.Y_AXIS));
        this.makeHeader();
        all_panels.add(titlePanel);

        UserInteractor ui = new UserInteractor();

        ArrayList<User> users = ui.readData();

        for (User user: users){
            all_panels.add(this.makeUserPanel(user));
        }

        JScrollPane sp = new JScrollPane(all_panels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp, BorderLayout.CENTER);
    }
}
