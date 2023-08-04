package Entities;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;

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
    public boolean getResolvedStatus() {return this.resolved;}

    static Notification[] sortByCreatedDate(ArrayList<Notification> notifications){
        Notification[] sorted = new Notification[notifications.size()];
        sorted = notifications.toArray(sorted);
        int n = sorted.length;
        for (int i = 1; i < n; i++){
            Notification item = sorted[i];
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
