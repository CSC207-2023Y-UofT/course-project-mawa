package Entities;

import java.time.LocalDateTime;

public class UserNotificationResponse extends UserNotification {

    public UserNotificationResponse(int senderId, int recipientId, String shiftId, String message, LocalDateTime date) {
        super(senderId, recipientId, shiftId, message, date);
    }
}
