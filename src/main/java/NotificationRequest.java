import java.time.LocalDateTime;
public class NotificationRequest extends Notification {
    private String message;
    private Boolean isResolved = false;
    private User sender;
    private User recipient;
    private Shift shift;
    private LocalDateTime date;

    public Notification(User sender, User recipient, Shift shift, String message, LocalDateTime date){
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.shift = shift;
        this.date = date;
    }




}
