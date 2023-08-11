package FrameworksAndDrivers;

import InterfaceAdapters.MakeShiftFormPresenter;
import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MakeShiftForm extends JFrame implements Page {
    private LocalDate date;
    private JPanel mainPanel, contentPanel, titlePanel, footerPanel;
    private CloseButton submitButton, cancelButton;
    private CustomTextField timeField, durationField;
    private MakeShiftFormPresenter presenter;
    private Page gui;
    public MakeShiftForm(LocalDate date, Page gui){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.date = date;
        this.gui = gui;
        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        titlePanel = new JPanel(new FlowLayout());
        footerPanel = new JPanel(new FlowLayout());
        this.submitButton = new CloseButton(this, "Schedule Shift");
        this.cancelButton = new CloseButton(this, "Cancel");
        timeField = new CustomTextField();
        durationField = new CustomTextField();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width/2.5;
        double height = screenSize.height/2;
        setSize(new Dimension((int) width, (int) height));
        presenter = new MakeShiftFormPresenter(timeField, durationField, submitButton, cancelButton,
                date, gui);
        submitButton.addActionListener(presenter);
        cancelButton.addActionListener(presenter);
        addTitle();
        addContent();
        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void addTitle() {
        JLabel title = new JLabel(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        title.setFont(new Font("Arial", Font.PLAIN, 24));
        titlePanel.add(title);

        footerPanel.add(submitButton);
        footerPanel.add(cancelButton);

        mainPanel.add(titlePanel, BorderLayout.PAGE_START);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void addContent() {
        contentPanel.add(new JLabel("Start Time (HH:mm):"));
        contentPanel.add(timeField);
        contentPanel.add(new JLabel("Duration (in hours):"));
        contentPanel.add(durationField);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
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
