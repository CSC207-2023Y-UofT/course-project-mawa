import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class NotificationGUI extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel unresolvedNotificationPanel = new JPanel();
    private JPanel resolvedNotificationPanel = new JPanel();
    private JPanel unresolvedNotificationListPanel = new JPanel();
    private JPanel resolvedNotificationListPanel = new JPanel();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    private final JLabel unresolvedNotificationLabel = new JLabel("Unresolved Notifications");
    private final JLabel resolvedNotificationLabel = new JLabel("Resolved Notifications");
    private final JButton denyRequestButton = new JButton("Deny Request");
    private final JButton rescheduleShiftButton = new JButton("Reschedule Shift");
    private String[] unresolvedNotifications = {};
    private String[] resolvedNotifications = {};
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;


    public NotificationGUI() {
        this.frame.setLayout(new GridLayout(1, 2));
        String[] mom = {"12"};
        populateLists(mom);
        createNotificationList(this, unresolvedNotificationPanel, unresolvedNotificationListPanel,
                unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller);
        addButtons(this, unresolvedNotificationPanel);
        createNotificationList(this, resolvedNotificationPanel, resolvedNotificationListPanel,
                resolvedNotificationLabel, unresolvedNotificationList,
                resolvedNotificationListScroller);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void populateLists(String[] notifications) {
        unresolvedNotifications = new String[]{"hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello",};
        resolvedNotifications = new String[]{"hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello", "hello",};
        unresolvedNotificationList = new JList<String>(unresolvedNotifications);
        resolvedNotificationList = new JList<String>(resolvedNotifications);
        unresolvedNotificationListScroller = new JScrollPane(unresolvedNotificationList);
        resolvedNotificationListScroller = new JScrollPane(resolvedNotificationList);

    }

    private void createNotificationList(NotificationGUI item, JPanel panel, JPanel listPanel, JLabel label, JList<String> list, JScrollPane scroller) {
        ShiftviewHRGUI.ListSetter(list, panel, label);
        if (Objects.equals(label.getText(), "Unresolved Notifications")) {
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        rescheduleShiftButton.doClick(); //emulate button click
                    }
                }
            });
        }
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scroller, BorderLayout.CENTER);
        if (Objects.equals(label.getText(), "Unresolved Notifications")) {
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        } else {
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 45, 10));
        }
        panel.add(listPanel, BorderLayout.CENTER);

        item.frame.add(panel);
    }

    public void addButtons(NotificationGUI item, JPanel panel) {
        denyRequestButton.setActionCommand("Deny");
        denyRequestButton.addActionListener(item);
        rescheduleShiftButton.setActionCommand("Reschedule");
        rescheduleShiftButton.addActionListener(item);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rescheduleShiftButton);
        buttonPanel.add(denyRequestButton);
        denyRequestButton.setHorizontalAlignment(JLabel.CENTER);
        rescheduleShiftButton.setHorizontalAlignment(JLabel.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        new NotificationGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reschedule".equals(e.getActionCommand())) {
            System.out.println(unresolvedNotificationList.getSelectedValue());
            this.revalidate();
        }

    }
}