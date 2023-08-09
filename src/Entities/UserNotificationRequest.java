package Entities;

import java.time.LocalDateTime;
public class UserNotificationRequest extends UserNotification {
    private String message;
    private Boolean isResolved = false;
    private String senderId;
    private String recipientId;
    private String shiftId;
    private LocalDateTime date;

    public UserNotificationRequest(int senderId, int recipientId, String shiftId, String message, LocalDateTime date){
        super(senderId, recipientId, shiftId, message, date);
    }

    public Boolean resolvedStatus(){
        return this.isResolved;
    }

}
