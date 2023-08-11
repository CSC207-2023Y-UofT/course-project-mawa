package FrameworksAndDrivers;

import UseCases.NotificationHRListModel;
import UseCases.NotificationListPanelBuilder;

import javax.swing.*;
import java.awt.*;;

public class NotificationEmployeeGUI extends JFrame {
    private JFrame frame = new JFrame();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;
    public NotificationHRListModel unresolvedNotificationListModel;
    public NotificationHRListModel resolvedNotificationListModel;

    public NotificationEmployeeGUI(int userID) {
        this.frame.setLayout(new GridLayout(1, 2));
        unresolvedNotificationListModel = new NotificationHRListModel(userID, false);
        unresolvedNotificationListModel.populateList();
        resolvedNotificationListModel = new NotificationHRListModel(userID, true);
        resolvedNotificationListModel.populateList();
        this.unresolvedNotificationList = new JList<String>(unresolvedNotificationListModel.getListModel());
        this.resolvedNotificationList = new JList<String>(resolvedNotificationListModel.getListModel());
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