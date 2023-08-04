import java.time.format.DateTimeFormatter;

public final class CalendarConstants {

    private CalendarConstants() {
        // restrict instantiation
    }

    public static final String[] months = new String[]{"January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October", "November",
            "December"};
    public static final String[] days = new String[]{"Mon.", "Tues.", "Wed.", "Thur.",
            "Fri.", "Sat.", "Sun."};
    
    public static final int dayInfoPayDay = 0;
    public static final int dayInfoShifts = 1;
    public static final int[] dayFormat = new int[]{dayInfoPayDay, dayInfoShifts};
    public static final String yearSelector = "year";
    public static final String monthSelector = "month";
    public final static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
}