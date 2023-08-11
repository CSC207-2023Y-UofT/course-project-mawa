package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import InterfaceAdapters.ShiftViewHRNotificationsPresenter;
import UseCases.ShiftViewHRModel;

public class ShiftViewHRGUI extends JFrame implements ActionListener {
    private JFrame frame = new JFrame();
    private final JButton removeButton = new JButton("Remove");
    private final JButton addButton = new JButton("add");
    private JList<String> employeesOnShiftList;
    private JList<String> employeesNotOnShiftList;
    private JScrollPane employeesOnShiftScroller;
    private JScrollPane employeesNotOnShiftScroller;
    ShiftViewHRNotificationsPresenter presenter;
    ShiftViewHRModel model;


    public ShiftViewHRGUI(int notificationID, int userID){
        model = new ShiftViewHRModel(notificationID, userID);
        this.frame.setLayout(new BorderLayout());
        presenter = new ShiftViewHRNotificationsPresenter(frame, model);
        presenter.addShiftLabels();
        employeesOnShiftList = new JList<String>(model.getEmployeesOnShiftList());
        employeesNotOnShiftList = new JList<String>(model.getEmployeesNotOnShiftList());
        employeesOnShiftScroller = new JScrollPane(employeesOnShiftList);
        employeesNotOnShiftScroller = new JScrollPane(employeesNotOnShiftList );
        JPanel employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new GridLayout(1, 2));
        createUserList(employeeListPanel,"Employees on Shift", employeesOnShiftList, employeesOnShiftScroller, removeButton, "Remove");
        createUserList(employeeListPanel,"Employees not on Shift", employeesNotOnShiftList, employeesNotOnShiftScroller, addButton, "Add");
        this.frame.add(employeeListPanel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("FrameworksAndDrivers.ShiftView");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUserList(JPanel mainPanel, String listLabel,
                                JList<String> list, JScrollPane scroller, JButton button, String command) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(listLabel);
        JPanel listPanel = new JPanel();
        ListSetter(list, panel, label);
        if (Objects.equals(label.getText(), "Employees on Shift")) {
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        removeButton.doClick(); //emulate button click
                    }
                }
            });
        }
        else{
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        addButton.doClick(); //emulate button click
                    }
                }
            });
        }
        scroller.setPreferredSize(new Dimension(225, 100));
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        listPanel.add(scroller, BorderLayout.CENTER);
        listPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        panel.add(listPanel, BorderLayout.CENTER);
        button.setActionCommand(command);
        button.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        button.setHorizontalAlignment(JLabel.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        mainPanel.add(panel);
    }

    static void ListSetter(JList<String> list, JPanel panel, JLabel label) {
        panel.setLayout(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        panel.add(label, BorderLayout.PAGE_START);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Add".equals(e.getActionCommand())) {
            presenter.updateEmployeesOnShiftList(employeesNotOnShiftList.getSelectedValue());
            this.revalidate();
        }else if ("Remove".equals(e.getActionCommand())) {
            presenter.updateEmployeesNotOnShiftList(employeesOnShiftList.getSelectedValue());
            this.revalidate();
        }

    }

}
