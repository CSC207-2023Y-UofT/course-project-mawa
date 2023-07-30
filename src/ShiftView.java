
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ShiftView extends JFrame implements ActionListener, Page {
    private JPanel panel;
    private Shift shift;
    private JButton timeOffButton;
    private Employee employee;
    public ShiftView(Shift shift, Employee employee){
        this.shift = shift;
        this.employee = employee;
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
        JLabel coworkers = new JLabel(shift.getCoworkers());
        timeOffButton = new JButton("Request Shift Off");
        timeOffButton.addActionListener(this);
        panel.add(time);
        panel.add(coworkers);
        panel.add(timeOffButton);
        panel.setVisible(true);
    }
}
