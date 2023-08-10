package UseCases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class NotificationListPanelBuilder extends JPanel {
    public JPanel panel = new JPanel();
    public NotificationListPanelBuilder(JFrame frame, JLabel label, JList<String> list, JScrollPane scroller, Boolean hr){
        panel.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(label, BorderLayout.PAGE_START);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JPanel listPanel = new JPanel();
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scroller, BorderLayout.CENTER);
        if (Objects.equals(label.getText(), "Unresolved Notifications") || hr.equals(false)) {
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        } else if (hr){
            listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 45, 10));
        }
        panel.add(listPanel, BorderLayout.CENTER);
    }
    public NotificationListPanelBuilder(JFrame frame, JLabel label, JList<String> list, JScrollPane scroller, JButton rescheduleShiftButton, JButton denyRequestButton){
        panel.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(label, BorderLayout.PAGE_START);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JPanel listPanel = new JPanel();
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    rescheduleShiftButton.doClick(); //emulate button click
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
        panel.add(listPanel, BorderLayout.CENTER);
        frame.add(panel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rescheduleShiftButton);
        buttonPanel.add(denyRequestButton);
        denyRequestButton.setHorizontalAlignment(JLabel.CENTER);
        rescheduleShiftButton.setHorizontalAlignment(JLabel.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
