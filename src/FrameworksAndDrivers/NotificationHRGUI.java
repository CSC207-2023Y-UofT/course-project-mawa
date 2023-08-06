package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

import InterfaceAdapters.Page;
import UseCases.UserNotificationInteractor;
import UseCases.NotificationHRListModel;
import InterfaceAdapters.UserNotificationPresenter;
import UseCases.NotificationStatusTrackerUpdater;
import UseCases.NotificationListPanelBuilder;

public class NotificationHRGUI extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    private final JLabel unresolvedNotificationLabel = new JLabel("Unresolved Notifications");
    private final JLabel resolvedNotificationLabel = new JLabel("Resolved Notifications");
    private final JButton denyRequestButton = new JButton("Deny Request");
    private final JButton rescheduleShiftButton = new JButton("Reschedule Shift");
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;
    public NotificationHRListModel unresolvedNotificationListModel;
    public NotificationHRListModel resolvedNotificationListModel;
    public UserNotificationPresenter presenter;
    public NotificationHRGUI(int userID) {
        this.unresolvedNotificationListModel = new NotificationHRListModel(userID, false);
        this.resolvedNotificationListModel = new NotificationHRListModel(userID, true);
        this.frame.setLayout(new GridLayout(1, 2));
        denyRequestButton.setActionCommand("Deny");
        denyRequestButton.addActionListener(this);
        rescheduleShiftButton.setActionCommand("Reschedule");
        rescheduleShiftButton.addActionListener(this);
        this.unresolvedNotificationList = new JList<String>(unresolvedNotificationListModel.listModel);
        this.resolvedNotificationList = new JList<String>(resolvedNotificationListModel.listModel);
        this.unresolvedNotificationListScroller = new JScrollPane(this.unresolvedNotificationList);
        this.resolvedNotificationListScroller = new JScrollPane(this.resolvedNotificationList);
        this.presenter = new UserNotificationPresenter(userID, unresolvedNotificationListModel, resolvedNotificationListModel);
        this.frame.add(new NotificationListPanelBuilder(frame, unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller, rescheduleShiftButton, denyRequestButton).panel);
        this.frame.add(new NotificationListPanelBuilder(frame, resolvedNotificationLabel, resolvedNotificationList,
                resolvedNotificationListScroller, true).panel);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reschedule".equals(e.getActionCommand())) {
            presenter.rescheduleUpdateListModel(unresolvedNotificationList.getSelectedValue());
        }
        if ("Deny".equals(e.getActionCommand())) {
            presenter.denyUpdateListModel(unresolvedNotificationList.getSelectedValue());
        }

    }
}
