package FrameworksAndDrivers;


import FrameworksAndDrivers.HomeButton;
import InterfaceAdapters.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class RequestForm extends JFrame implements Page {
    private int employee, shift;
    private LocalDateTime time1, time2;
    private JLabel startField, endField;
    private CustomTextField reasonField;
    private CloseButton submitButton, cancelButton;
    private JPanel panel, titlePanel;
    private RequestFormPresenter presenter;

    public RequestForm(LocalDateTime t1, LocalDateTime t2, int employee, int shift){
        this.time1 = t1;
        this.time2 = t2;
        this.submitButton = new CloseButton(this, "Submit Time Off Request");
        this.cancelButton = new CloseButton(this, "Cancel");
        this.shift = shift;
        setUser(employee);
        panel = new JPanel(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2.5;
        double height = screenSize.getHeight()/2;
        setPreferredSize(new Dimension((int) width, (int) height));
        addTitle();
        addHomeButton();
        addContent();
        presenter = new RequestFormPresenter( submitButton, cancelButton,
                shift, reasonField, employee);
        this.submitButton.addActionListener(presenter);
        this.cancelButton.addActionListener(presenter);
        setVisible(true);


    }

    @Override
    public void addTitle() {
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(new JLabel("Request Time Off"));
    }

    @Override
    public void addContent() {
        JPanel startTimeOff = new JPanel(new FlowLayout());
        JPanel endTimeOff = new JPanel(new FlowLayout());
        JPanel reason = new JPanel(new FlowLayout());
        startField = new JLabel(time1.format(CalendarConstants.dateTimeFormatter));
        endField = new JLabel(time2.format(CalendarConstants.dateTimeFormatter));
        reasonField = new CustomTextField();
        reasonField.setPreferredSize( new Dimension( getWidth()/2, getHeight()/5));
        startTimeOff.add(new JLabel("Start Time Off (yyyy-MM-dd_HH:mm:ss) "));
        endTimeOff.add(new JLabel("End Time Off (yyyy-MM-dd_HH:mm:ss) "));
        reason.add(new JLabel("Reason for Time Off "));
        startTimeOff.add(startField);
        endTimeOff.add(endField);
        reason.add(reasonField);
        panel.add(startTimeOff);
        panel.add(endTimeOff);
        panel.add(reasonField);
        panel.add(cancelButton);
        panel.add(submitButton);
    }
    @Override
    public void setUser(int user) {
        this.employee = user;
    }

    @Override
    public void addHomeButton() {
        titlePanel.add(new HomeButton(this, employee));
        panel.add(titlePanel);
    }

    @Override
    public void update() {

    }
}
