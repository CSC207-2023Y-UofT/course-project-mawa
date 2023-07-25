import java.time.LocalDateTime;

abstract class Notification {
    private int notifId;
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

    public int getNotifId(){
        return this.notifId   
    }
    
    public String getMessage(){
        return this.message;
    }

    public String getSenderId(){
        return this.senderId;
    }

    public String getShiftId(){
        return this.senderId;
    }

    public String getRecipientId(){
        return this.recipientId;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

}
