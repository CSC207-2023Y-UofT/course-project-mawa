package FrameworksAndDrivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import InterfaceAdapters.ShiftViewHRNotificationsPresenter;


import InterfaceAdapters.Page;

public class ShiftViewHRGUI extends JFrame implements ActionListener, Page {
    private final JFrame frame = new JFrame();
    private final JButton removeButton = new JButton("Remove");
    private final JButton addButton = new JButton("add");
    private final JList<String> employeesOnShiftList;
    private final JList<String> employeesNotOnShiftList;
    private final JScrollPane employeesOnShiftScroller;
    private final JScrollPane employeesNotOnShiftScroller;
    ShiftViewHRNotificationsPresenter presenter;
    public int userId;
    private CloseButton closeButton;
    public ShiftViewHRGUI(int notificationID, int userID){
        userId = userID;
        JButton homeButton = new JButton("Home");
        closeButton = new CloseButton(this, "Close");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        presenter = new ShiftViewHRNotificationsPresenter(notificationID, userID);
        addShiftLabels(panel);
        employeesOnShiftList = new JList<String>(presenter.employeesOnShiftList());
        employeesNotOnShiftList = new JList<String>(presenter.employeesNotOnShiftList());
        employeesOnShiftScroller = new JScrollPane(employeesOnShiftList);
        employeesNotOnShiftScroller = new JScrollPane(employeesNotOnShiftList );

        homeButton.setActionCommand("home");
        homeButton.addActionListener(this);
        closeButton.setActionCommand("close");
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(homeButton, BorderLayout.WEST);
        //buttonPanel.add(closeButton, BorderLayout.WEST);

        this.frame.add(buttonPanel, BorderLayout.PAGE_START);

        JPanel employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new GridLayout(1, 2));
        createUserList(employeeListPanel,"Employees on Shift", employeesOnShiftList, employeesOnShiftScroller, removeButton, "Remove");
        createUserList(employeeListPanel,"Employees not on Shift", employeesNotOnShiftList, employeesNotOnShiftScroller, addButton, "Add");
        panel.add(employeeListPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("ShiftView");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ShiftViewHRGUI(int shiftId){
        // Megan use this one
        userId = 1;

        //JButton homeButton = new JButton("Home");
        closeButton = new CloseButton(this, "Close");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        presenter = new ShiftViewHRNotificationsPresenter(shiftId);
        addShiftLabels(panel);
        employeesOnShiftList = new JList<String>(presenter.employeesOnShiftList());
        employeesNotOnShiftList = new JList<String>(presenter.employeesNotOnShiftList());
        employeesOnShiftScroller = new JScrollPane(employeesOnShiftList);
        employeesNotOnShiftScroller = new JScrollPane(employeesNotOnShiftList );
        //homeButton.setActionCommand("home1");
        //homeButton.addActionListener(this);
        closeButton.setActionCommand("close1");
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        //buttonPanel.add(homeButton, BorderLayout.WEST);
        buttonPanel.add(closeButton, BorderLayout.WEST);
        this.frame.add(buttonPanel, BorderLayout.PAGE_START);
        JPanel employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new GridLayout(1, 2));
        createUserList(employeeListPanel,"Employees on Shift", employeesOnShiftList, employeesOnShiftScroller, removeButton, "Remove");
        createUserList(employeeListPanel,"Employees not on Shift", employeesNotOnShiftList, employeesNotOnShiftScroller, addButton, "Add");
        panel.add(employeeListPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        this.frame.setSize(600, 600);
        this.frame.setVisible(true);
        this.frame.setTitle("ShiftView");
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

    public void addShiftLabels(JPanel panel){
        JLabel shiftDateLabel = presenter.getDateLabel();
        JLabel shiftTimeLabel = presenter.getTimeLabel();
        JPanel shiftTitlePanel = new JPanel();
        shiftTitlePanel.setLayout(new GridLayout(2,1));
        shiftTitlePanel.add(shiftDateLabel);
        shiftTitlePanel.add(shiftTimeLabel);
        shiftDateLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        shiftDateLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 20));
        shiftTimeLabel.setFont(new Font(shiftDateLabel.getFont().getName(), shiftDateLabel.getFont().getStyle(), 15));
        panel.add(shiftTitlePanel, BorderLayout.PAGE_START);
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
        else if ("home".equals(e.getActionCommand())) {
            new HomePage(userId);
            presenter.updateShiftEmployeesAndNotification();
            this.frame.dispose();
        }else if ("home1".equals(e.getActionCommand())) {
            new HomePage(1);
            presenter.updateShiftEmployees();
            this.frame.dispose();
        }else if ("close1".equals(e.getActionCommand())){
            presenter.updateShiftEmployees();
            this.frame.dispose();
        }else if ("close".equals(e.getActionCommand())){
            presenter.updateShiftEmployeesAndNotification();
            this.frame.dispose();
        }

    }

    @Override
    public void addTitle() {

    }

    @Override
    public void addContent() {

    }

    @Override
    public void setUser(int user) {

    }

    @Override
    public void addHomeButton() {

    }

    @Override
    public void update() {

    }
}
