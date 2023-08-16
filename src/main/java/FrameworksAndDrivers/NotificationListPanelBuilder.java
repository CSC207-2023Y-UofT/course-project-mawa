package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class NotificationListPanelBuilder extends JPanel {
    public NotificationListPanelBuilder(JLabel label, JList<String> list, JScrollPane scroller, Boolean hr){
        /*
        Creates a List Panel, with a centered label above the list, which occupies the entirety of the panel.
         */
        super();
        this.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        this.add(label, BorderLayout.PAGE_START);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JPanel listPanel = new JPanel();
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scroller, BorderLayout.CENTER);
        if (hr.equals(false) || Objects.equals(label.getText(), "Unresolved Notifications")) {
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        } else if (hr){
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 45, 10));
        }
        this.add(listPanel, BorderLayout.CENTER);
    }
    public NotificationListPanelBuilder(JLabel label, JList<String> list, JScrollPane scroller, JButton rescheduleShiftButton, JButton denyRequestButton){
        /*
        Creates a List Panel, with a centered label above a list, which occupies the majority of the panel,
        and two buttons centered underneath the list. Double-clicking on list entry clicks Reschedule Button.
         */
        super();
        this.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        this.add(label, BorderLayout.PAGE_START);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JPanel listPanel = new JPanel();
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    rescheduleShiftButton.doClick();
                }
            }
        });
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
        this.add(listPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rescheduleShiftButton);
        buttonPanel.add(denyRequestButton);
        denyRequestButton.setHorizontalAlignment(JLabel.CENTER);
        rescheduleShiftButton.setHorizontalAlignment(JLabel.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }
}