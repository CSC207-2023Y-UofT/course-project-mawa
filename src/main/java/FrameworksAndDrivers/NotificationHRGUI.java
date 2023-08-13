package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InterfaceAdapters.UserNotificationHRPresenter;

public class NotificationHRGUI extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame();
    public JScrollPane unresolvedNotificationListScroller;
    public JScrollPane resolvedNotificationListScroller;
    public JList<String> unresolvedNotificationList;
    public JList<String> resolvedNotificationList;
    public UserNotificationHRPresenter presenter;
    public int user;
    public NotificationHRGUI(int userID) {
        user = userID;
        JPanel lowerPanel = new JPanel();
        JButton homeButton = new JButton("Home");
        this.presenter = new UserNotificationHRPresenter(userID);
        lowerPanel.setLayout(new GridLayout(1, 2));
        this.frame.setLayout(new BorderLayout());
        JButton denyRequestButton = new JButton("Deny Request");
        denyRequestButton.setActionCommand("Deny");
        denyRequestButton.addActionListener(this);
        JButton rescheduleShiftButton = new JButton("Reschedule Shift");
        rescheduleShiftButton.setActionCommand("Reschedule");
        rescheduleShiftButton.addActionListener(this);
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
        lowerPanel.add(new NotificationListPanelBuilder(unresolvedNotificationLabel, unresolvedNotificationList,
                unresolvedNotificationListScroller, rescheduleShiftButton, denyRequestButton));
        JLabel resolvedNotificationLabel = new JLabel("Resolved Notifications");
        lowerPanel.add(new NotificationListPanelBuilder(resolvedNotificationLabel, resolvedNotificationList,
                resolvedNotificationListScroller, true));
        this.frame.add(lowerPanel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("Notifications");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reschedule".equals(e.getActionCommand())) {
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
