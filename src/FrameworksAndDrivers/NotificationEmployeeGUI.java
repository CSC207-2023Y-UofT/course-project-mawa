package FrameworksAndDrivers;

import UseCases.NotificationListPanelBuilder;

import javax.swing.*;
import java.awt.*;;

public class NotificationEmployeeGUI extends JFrame {
    private JFrame frame = new JFrame();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;
    public DefaultListModel<String> unresolvedNotificationListModel = new DefaultListModel<String>();
    public DefaultListModel<String> resolvedNotificationListModel = new DefaultListModel<String>();

    public NotificationEmployeeGUI(int userID) {
        this.frame.setLayout(new GridLayout(1, 2));
        for (int i = 0; i < 100; i++) {
            unresolvedNotificationListModel.add(i, "i");
        }
        resolvedNotificationListModel.add(0, "bye");
        this.unresolvedNotificationList = new JList<String>(unresolvedNotificationListModel);
        this.resolvedNotificationList = new JList<String>(resolvedNotificationListModel);
        this.unresolvedNotificationListScroller = new JScrollPane(this.unresolvedNotificationList);
        this.resolvedNotificationListScroller = new JScrollPane(this.resolvedNotificationList);
        JLabel unresolvedNotificationLabel = new JLabel("Unresolved Notifications");
        this.frame.add(new NotificationListPanelBuilder(frame, unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller, false).panel);
        JLabel resolvedNotificationLabel = new JLabel("Resolved Notifications");
        this.frame.add(new NotificationListPanelBuilder(frame, resolvedNotificationLabel, resolvedNotificationList,
                resolvedNotificationListScroller, false).panel);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}