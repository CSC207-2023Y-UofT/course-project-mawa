package FrameworksAndDrivers;

import InterfaceAdapters.MakeShiftFormPresenter;
import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The MakeShiftForm class represents a graphical user interface for scheduling a new shift.
 * It extends JFrame and implements the Page interface.
 */
public class MakeShiftForm extends JFrame implements Page {
    private LocalDate date;
    private JPanel mainPanel, contentPanel, titlePanel, footerPanel;
    private CloseButton submitButton, cancelButton;
    private CustomTextField timeField, durationField;
    private MakeShiftFormPresenter presenter;

    /**
     * Constructs a MakeShiftForm object.
     *
     * @param date The date for which the shift is being scheduled.
     * @param gui The user interface associated with the form.
     */
    public MakeShiftForm(LocalDate date, Page gui){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.date = date;
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
                date, gui, this);
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
    /**
     * Updates the user interface to display an error message indicating that shifts can only span within one day.
     * This method uses a JOptionPane to show the error message as a dialog box.
     */
    @Override
    public void update() {
        JOptionPane.showMessageDialog (null,
                "Shifts can only span within one day.",
                "alert", JOptionPane.ERROR_MESSAGE);
    }
}
