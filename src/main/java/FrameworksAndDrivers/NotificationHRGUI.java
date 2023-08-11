package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UseCases.NotificationHRListModel;
import InterfaceAdapters.UserNotificationPresenter;

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
    public int user;
    public NotificationHRGUI(int userID) {
        user = userID;
        JPanel lowerPanel = new JPanel();
        JButton homeButton = new JButton("HomeButton");
        this.unresolvedNotificationListModel = new NotificationHRListModel(userID, false);
        this.resolvedNotificationListModel = new NotificationHRListModel(userID, true);
        lowerPanel.setLayout(new GridLayout(1, 2));
        this.frame.setLayout(new BorderLayout());

        denyRequestButton.setActionCommand("Deny");
        denyRequestButton.addActionListener(this);
        rescheduleShiftButton.setActionCommand("Reschedule");
        rescheduleShiftButton.addActionListener(this);
        homeButton.setActionCommand("home");
        homeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(homeButton, BorderLayout.WEST);
        this.frame.add(buttonPanel, BorderLayout.PAGE_START);
        this.unresolvedNotificationList = new JList<String>(unresolvedNotificationListModel.getListModel());
        this.resolvedNotificationList = new JList<String>(resolvedNotificationListModel.getListModel());
        this.unresolvedNotificationListScroller = new JScrollPane(this.unresolvedNotificationList);
        this.resolvedNotificationListScroller = new JScrollPane(this.resolvedNotificationList);
        this.presenter = new UserNotificationPresenter(userID, unresolvedNotificationListModel, resolvedNotificationListModel);
        lowerPanel.add(new NotificationListPanelBuilder(frame, unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller, rescheduleShiftButton, denyRequestButton).panel);
        lowerPanel.add(new NotificationListPanelBuilder(frame, resolvedNotificationLabel, resolvedNotificationList,
                resolvedNotificationListScroller, true).panel);
        this.frame.add(lowerPanel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reschedule".equals(e.getActionCommand())) {
            //presenter.rescheduleUpdateListModel(unresolvedNotificationList.getSelectedValue());
            new ShiftViewHRGUI(presenter.NotificationID(unresolvedNotificationList.getSelectedValue()), user);
            this.frame.dispose();
        }
        else if ("Deny".equals(e.getActionCommand())) {
            presenter.denyUpdateListModel(unresolvedNotificationList.getSelectedValue());
        }
        else if ("home".equals(e.getActionCommand())) {
            //presenter.rescheduleUpdateListModel(unresolvedNotificationList.getSelectedValue());
            new HomePage(user);
            this.frame.dispose();
        }
    }
}
