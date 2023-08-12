package FrameworksAndDrivers;

import FrameworksAndDrivers.HomeButton;
import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShiftView extends JFrame implements Page {
    private JPanel panel, titlePanel, contentPanel;
    private int shift;
    private TimeOffButton timeOffButton;
    private int employee;
    private ShiftPresenter presenter;
    private LocalDateTime date;
    private CloseButton closeButton;
    private float duration;

    public ShiftView(int shift, int employee) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width / 2.5;
        double height = screenSize.height / 2;
        setSize(new Dimension((int) width, (int) height));
        this.shift = shift;
        setUser(employee);
        this.closeButton = new CloseButton(this, "Close");
        panel = new JPanel(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        this.presenter = new ShiftPresenter(shift, this, closeButton, employee);
        this.date = presenter.getDate();
        this.duration = presenter.getDuration();
        addTitle();
        panel.add(titlePanel, BorderLayout.PAGE_START);
        addContent();
        panel.add(contentPanel, BorderLayout.CENTER);
        setContentPane(panel);
        closeButton.addActionListener(presenter);
        this.setVisible(true);
        repaint();
    }

    @Override
    public void addTitle() {

        JLabel title = new JLabel(date.getDayOfWeek() + ", "
                + date.getMonth().toString() + " " + date.getDayOfMonth());
        title.setFont(new Font("Serif", Font.PLAIN, (int) (title.getHeight() * 1.5)));
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(title);
    }

    @Override
    public void addContent() {
        JLabel time = new JLabel("Start Time: " + date.format(DateTimeFormatter.ofPattern("HH:mm")));
        JLabel durationLabel = new JLabel("Duration (hours): " + duration);
        JLabel coworkersLabelTitle = new JLabel("Shift Coworkers: ");
        JLabel coworkersLabel = new JLabel(presenter.getCoworkerString());
        timeOffButton = new TimeOffButton(shift, employee);
        contentPanel.add(time);
        contentPanel.add(durationLabel);
        contentPanel.add(coworkersLabelTitle);
        contentPanel.add(coworkersLabel);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(closeButton);
        bottomPanel.add(timeOffButton);
        panel.add(bottomPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void setUser(int user) {
        this.employee = user;
    }

    @Override
    public void addHomeButton() {
        HomeButton hb = new HomeButton(this, employee);
        titlePanel.add(hb);
    }

    @Override
    public void update() {
        new RequestForm(date, date.plusHours((long) presenter.getDuration()), employee, shift);
    }

}