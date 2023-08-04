package Entities;

import java.time.LocalDateTime;

public class NotificationResponse extends Notification{

    public NotificationResponse(String senderId, String recipientId, String shiftId, String message, LocalDateTime date) {
        super(senderId, recipientId, shiftId, message, date);
    }
}
