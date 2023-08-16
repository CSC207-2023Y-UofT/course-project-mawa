package FrameworksAndDrivers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;
import InterfaceAdapters.UserNotificationEmployeePresenter;

public class NotificationEmployeeGUI extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;
    public UserNotificationEmployeePresenter presenter;
    public int user;

    public NotificationEmployeeGUI(int userID) {
        user = userID;
        this.presenter = new UserNotificationEmployeePresenter(user);
        JButton homeButton = new JButton("Home Button");
        JPanel panel = new JPanel();
        this.frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1, 2));
        homeButton.setActionCommand("home");
        homeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(homeButton, BorderLayout.WEST);
        this.frame.add(buttonPanel, BorderLayout.PAGE_START);
        this.unresolvedNotificationList = new JList<String>(presenter.getUnresolvedDefaultList());
        this.resolvedNotificationList = new JList<String>(presenter.getResolvedDefaultList());
        this.unresolvedNotificationListScroller = new JScrollPane(this.unresolvedNotificationList);
        this.resolvedNotificationListScroller = new JScrollPane(this.resolvedNotificationList);
        JLabel unresolvedNotificationLabel = new JLabel("Unresolved Notifications");
        panel.add(new NotificationListPanelBuilder(unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller, false));
        JLabel resolvedNotificationLabel = new JLabel("Resolved Notifications");
        panel.add(new NotificationListPanelBuilder(resolvedNotificationLabel, resolvedNotificationList,
                resolvedNotificationListScroller, false));
        this.frame.add(panel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        If home button is clicked user is taken to the home page
         */
        if ("home".equals(e.getActionCommand())) {
            new HomePage(user);
            this.frame.dispose();
        }
    }
}