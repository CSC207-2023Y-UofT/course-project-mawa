package UseCases;

/**
 * The FileNameConstants class provides constant file names used for data storage and serialization.
 */
public final class FileNameConstants {
    private FileNameConstants() {
        // Restrict instantiation
    }

    /** The filename for storing notification data. */
    public static final String NOTIFICATION_FILE_NAME = "notifications.ser";

    /** The filename for storing payment data. */
    public static final String PAYMENT_FILE_NAME = "payments.ser";

    /** The filename for storing user data. */
    public static final String USER_FILE_NAME = "users.ser";

    /** The filename for storing shift data. */
    public static final String SHIFT_FILE_NAME = "shifts.ser";
}
