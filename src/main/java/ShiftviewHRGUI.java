import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ShiftviewHRGUI extends JFrame {
    private JFrame frame = new JFrame();
    private final JButton removeButton = new JButton("Remove");
    private final JButton add = new JButton("add");
    private final JLabel shiftDateLabel = new JLabel("Date: Monday, July, 31st");
    private final JLabel shiftTimeLabel = new JLabel("Shift Time: 6:00pm - 8:00pm");
    public ShiftviewHRGUI(){
        this.frame.setLayout(new BorderLayout());
        JPanel shiftTitlePanel = new JPanel();
        shiftTitlePanel.setLayout(new GridLayout(2,1));
        shiftTitlePanel.add(shiftDateLabel);
        shiftTitlePanel.add(shiftTimeLabel);
        shiftDateLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftDateLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 20));
        shiftTimeLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 15));
        frame.add(shiftTitlePanel, BorderLayout.PAGE_START);

        JPanel employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new GridLayout(1, 2));

        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("ShiftView");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
