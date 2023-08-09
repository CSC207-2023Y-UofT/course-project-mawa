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

    public Notification[][] userNotifications(User user){
        NotificationDatabaseInteractor notificationDBInteractor = new NotificationDatabaseInteractor();
        ArrayList<Notification> notifications = notificationDBInteractor.readData();
        ArrayList<Notification> unresolvedNotifications = new ArrayList<Notification>();
        ArrayList<Notification> resolvedNotifications = new ArrayList<Notification>();
        for (Notification n: notifications){
            if (n.getResolvedStatus()){
                if (n.getSenderId() == user.getEmployeeNum() || n.getRecipientId() == user.getEmployeeNum()){
                    resolvedNotifications.add(n);
                }
            }
            else{
                if (n.getSenderId() == user.getEmployeeNum() || n.getRecipientId() == user.getEmployeeNum()){
                    unresolvedNotifications.add(n);
                }
            }
        }
        Notification[][] noti = new Notification[][] {Notification.sortByCreatedDate(unresolvedNotifications), Notification.sortByCreatedDate(resolvedNotifications)};

        return noti;
    }
    private String[] notificationArrayToStringArray(Notification[] notifications){
        String[] stringNotifications = new String[notifications.length];
        int i = 0;
        for (Notification n: notifications){
            String item = "User Id: " + notifications[i].getNotifId() + " has requested time off on shift: " + notifications[i].getShiftId();
            stringNotifications[i] = item;
            i+=1;
        }
        return stringNotifications;
    }

    private void populateLists(String[] resolvedNotifications,String[] unresolvedNotifications) {
        unresolvedNotificationList = new JList<String>(unresolvedNotifications);
        resolvedNotificationList = new JList<String>(resolvedNotifications);
        unresolvedNotificationListScroller = new JScrollPane(unresolvedNotificationList);
        resolvedNotificationListScroller = new JScrollPane(resolvedNotificationList);

    }

    private void createNotificationList(NotificationHRGUI item, JPanel panel, JPanel listPanel, JLabel label, JList<String> list, JScrollPane scroller) {
        ShiftViewHRGUI.ListSetter(list, panel, label);
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

    public void addButtons(NotificationHRGUI item, JPanel panel) {
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
