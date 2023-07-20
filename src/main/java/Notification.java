import java.time.LocalDateTime;

abstract class Notification {
    private String message;
    private String senderId;
    private String recipientId;
    private String shiftId;
    private LocalDateTime date;

    public Notification(String senderId, String recipientId, String shiftId, String message, LocalDateTime date){
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.shiftId = shiftId;
        this.date = date;
    }




}
