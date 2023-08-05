package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class UserNotification implements Serializable {
    private int notifId;
    private String message;
    private int senderId;
    private int recipientId;
    private String shiftId;
    private LocalDateTime date;
    private Boolean resolved;
    private LocalDateTime resolvedAt;

    public UserNotification(int senderId, int recipientId, String shiftId, String message, LocalDateTime date){
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.shiftId = shiftId;
        this.date = date;
        this.resolved = false;
        UserNotificationInteractor ndb = new UserNotificationInteractor();
        ArrayList<UserNotification> l = ndb.readData();
        this.notifId = l.size() + 1;
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

    public int getSenderId(){
        return this.senderId;
    }

    public String getShiftId(){
        return this.shiftId;
    }

    public int getRecipientId(){
        return this.recipientId;
    }

    public LocalDateTime getDate(){
        return this.date;
    }
    public boolean getResolvedStatus() {return this.resolved;}

    @Override
    public String toString() {
        return "has requested time off on " + this.getDate().toString() + ", ";
    }

    public static UserNotification[] sortByCreatedDate(ArrayList<UserNotification> notifications){
        UserNotification[] sorted = new UserNotification[notifications.size()];
        sorted = notifications.toArray(sorted);
        int n = sorted.length;
        for (int i = 1; i < n; i++){
            UserNotification item = sorted[i];
            int j = i-1;
            while(j >= 0 && sorted[j].getDate().isBefore(item.getDate())){
                sorted[j+1] = sorted[j];
                j -= 1;
            }
            sorted[j+1] = item;
        }
        return sorted;
    }

}
