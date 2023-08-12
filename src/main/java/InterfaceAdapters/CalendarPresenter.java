package InterfaceAdapters;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.Objects;

/**
 * The CalendarPresenter class represents the presenter component for the calendar display.
 * It handles user interactions and updates the calendar's year and month based on user selections.
 */
public class CalendarPresenter implements ActionListener {
    private int year, month, user;
    private String[] yearRange;
    private Page gui;
    private CalendarModel model;
    private GUIElement yearSelector, monthSelector;
    /**
     * Constructs a CalendarPresenter object with the specified parameters.
     *
     * @param year The initial year value.
     * @param month The initial month value.
     * @param user The user associated with the calendar.
     * @param gui The GUI element representing the calendar.
     * @param model The model component for the calendar.
     * @param yearSelector The year selector GUI element.
     * @param monthSelector The month selector GUI element.
     */
    public CalendarPresenter(int year, int month, int user, Page gui, CalendarModel model,
                             GUIElement yearSelector, GUIElement monthSelector){
        this.year = year;
        this.month = month;
        this.user = user;
        this.gui = gui;
        this.model = model;
        this.monthSelector = monthSelector;
        this.yearSelector = yearSelector;
        yearRange = new String[]{String.valueOf(year - 3), String.valueOf(year - 2),
                String.valueOf(year - 1), String.valueOf(year),
                String.valueOf(year + 1)};
    }
    /**
     * Retrieves the range of years available for selection.
     *
     * @return An array of year values.
     */
    public String[] getYearRange(){
        return yearRange;
    }
    /**
     * Updates the calendar's year and month based on the user's selections and triggers a GUI update.
     *
     * @param e The ActionEvent associated with the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yearSelector){
            model.setYear(Integer.parseInt(Objects.requireNonNull(yearSelector.getContent())));
            gui.update();
            year = model.getYear();
        } else if (e.getSource() == monthSelector){
            model.setMonth(Month.valueOf(Objects.requireNonNull(monthSelector.getContent())).getValue());
            gui.update();
            month = model.getMonth();
        }
    }

}
