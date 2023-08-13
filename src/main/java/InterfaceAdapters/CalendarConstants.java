package InterfaceAdapters;

import java.time.format.DateTimeFormatter;
/**
 * The CalendarConstants class provides constants related to the calendar display.
 * It includes month names, day names, and a DateTimeFormatter for formatting dates and times.
 */
public final class CalendarConstants {

    private CalendarConstants() {
        // restrict instantiation
    }

    /**
     * An array of month names.
     */
    public static final String[] months = new String[]{"JANUARY", "FEBRUARY", "MARCH",
            "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER",
            "DECEMBER"};

    /**
     * An array of abbreviated day names.
     */
    public static final String[] days = new String[]{"Mon.", "Tues.", "Wed.", "Thur.",
            "Fri.", "Sat.", "Sun."};

    /**
     * A {@link DateTimeFormatter} for formatting dates and times in the pattern "yyyy-MM-dd_HH:mm:ss".
     */
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
}

