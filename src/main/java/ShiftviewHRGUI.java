import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ShiftviewHRGUI extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private final JButton removeButton = new JButton("Remove");
    private final JButton addButton = new JButton("add");
    private final JLabel shiftDateLabel = new JLabel("Date: Monday, July, 31st");
    private final JLabel shiftTimeLabel = new JLabel("Shift Time: 6:00pm - 8:00pm");
    private JList<String> employeesOnShiftList;
    private JList<String> employeesNotOnShiftList;
    private JScrollPane employeesOnShiftScroller;
    private JScrollPane employeesNotOnShiftScroller;
    private String[] employeesOnShift;
    private String[] employeesNotOnShift;


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
        populateLists(new String[]{"yes"});
        createUserList(employeeListPanel,"Employees On Shift", employeesOnShiftList, employeesOnShiftScroller, removeButton, "Remove");
        createUserList(employeeListPanel,"Employees Not On Shift", employeesNotOnShiftList, employeesNotOnShiftScroller, addButton, "Add");
        this.frame.add(employeeListPanel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("ShiftView");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void populateLists(String[] notifications) {

        String[] unresolvedNotifications = new String[]{"hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello",};
        String[] resolvedNotifications = new String[]{"bye", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello",};
        employeesOnShiftList = new JList<String>(unresolvedNotifications);
        employeesNotOnShiftList = new JList<String>(resolvedNotifications);
        employeesOnShiftScroller = new JScrollPane(employeesOnShiftList);
        employeesNotOnShiftScroller = new JScrollPane(employeesNotOnShiftList );

    }

    private void createUserList(JPanel mainPanel, String listLabel,
                                JList<String> list, JScrollPane scroller, JButton button, String command) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(listLabel);
        JPanel listPanel = new JPanel();
        ListSetter(list, panel, label);
        if (Objects.equals(label.getText(), "Employees On Shift")) {
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        removeButton.doClick(); //emulate button click
                    }
                }
            });
        }
        else{
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        addButton.doClick(); //emulate button click
                    }
                }
            });
        }
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scroller, BorderLayout.CENTER);
        listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        panel.add(listPanel, BorderLayout.CENTER);
        button.setActionCommand(command);
        button.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        button.setHorizontalAlignment(JLabel.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        mainPanel.add(panel);
    }

    static void ListSetter(JList<String> list, JPanel panel, JLabel label) {
        panel.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(label, BorderLayout.PAGE_START);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Add".equals(e.getActionCommand())) {
            this.revalidate();
            new ShiftviewHRGUI();
            this.frame.dispose();
        }

    }

}
