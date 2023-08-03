import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Interactor;
import java.util.NotificationDatabaseInteractor;

abstract class Notification {
    private int notifId;
    private String message;
    private String senderId;
    private String recipientId;
    private String shiftId;
    private LocalDateTime date;
    private Boolean resolved;
    private LocalDateTime resolvedAt;

    public Notification(String senderId, String recipientId, String shiftId, String message, LocalDateTime date){
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.shiftId = shiftId;
        this.date = date;
        this.resolved = false;
        ndb = new NotificationDatabseInteractor();
        l = ndb.readData();
        if (len(l) == 0){
            this.notifId = 1;
        } else{
            this.notifId = len(l) + 1;
        }
    }

    public void resolve(){
        this.resolved = true; this.resolvedAt = LocalDateTime.now();
    }
    
    public int getNotifId(){
        return this.notifId;
    }
    
    public String getMessage(){
        return this.message;
    }

    public String getSenderId(){
        return this.senderId;
    }

    public String getShiftId(){
        return this.shiftId;
    }

    public String getRecipientId(){
        return this.recipientId;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public ArrayList<Notification> sortByCreatedDate(ArrayList<Notification> notifications){
        ArrayList<Notification> sorted = new ArrayList<Notification>();
        sorted.add(notifications.remove(0));


    }

}
