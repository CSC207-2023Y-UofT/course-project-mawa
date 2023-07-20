import java.time.LocalDateTime;
public class NotificationRequest extends Notification {
    private String message;
    private Boolean isResolved = false;
    private String senderId;
    private String recipientId;
    private String shiftId;
    private LocalDateTime date;

    public NotificationRequest(String senderId, String recipientId, String shiftId, String message, LocalDateTime date){
        super(senderId, recipientId, shiftId, message, date);
    }

    public Boolean resolvedStatus(){
        return this.isResolved;
    }




}
