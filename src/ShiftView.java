
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ShiftView extends JFrame implements ActionListener, Page {
    private JPanel panel;
    private Shift shift;
    private JButton timeOffButton;
    private int employee;

    private EmployeeDataBaseInteractor empDB;
    public ShiftView(Shift shift, int employee){
        this.shift = shift;
        this.employee = employee;
        this.empDB = new EmployeeDataBaseInteractor();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        addTitle();
        addContent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timeOffButton){
            new RequestForm(shift.getTime(), shift.getTime().plusHours((long) shift.getDuration()), employee);
            this.dispose();
        }
    }

    @Override
    public void addTitle() {
        JLabel title = new JLabel(shift.getTime().getDayOfWeek()+", "
                +shift.getTime().getMonth()+" "+shift.getTime().getDayOfMonth());
        title.setFont(new Font("Serif", Font.PLAIN, getHeight()/8));
        panel.add(title);
    }

    @Override
    public void addContent() {
        JLabel time = new JLabel(shift.getTime().getHour() +":"+shift.getTime().getMinute());
        ArrayList<Employee> coworkers = new ArrayList<Employee>();
        for (int id : shift.getCoworkers()){
            coworkers.add((Employee)empDB.getEmployee(id));
        }
        JLabel coworkersLabel = new JLabel(coworkers.toString());
        timeOffButton = new JButton("Request Shift Off");
        timeOffButton.addActionListener(this);
        panel.add(time);
        panel.add(coworkersLabel);
        panel.add(timeOffButton);
        panel.setVisible(true);
    }
}
