package Entities;

import UseCases.UserNotificationInteractor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class UserNotification implements Serializable {
    private final int notifId;
    private final String message;
    private final int senderId;
    private final int recipientId;
    private final int shiftId;
    private final LocalDateTime date;
    private Boolean resolved;
    private Boolean denied = false;
    private LocalDateTime resolvedAt;

    public UserNotification(int senderId, int recipientId, int shiftId, String message, LocalDateTime date){
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

    public int getShiftId(){
        return this.shiftId;
    }

    public int getRecipientId(){
        return this.recipientId;
    }

    public LocalDateTime getDate(){
        return this.date;
    }
    public boolean getResolvedStatus() {return this.resolved;}
    public boolean getDenyStatus(){return this.denied;}
    public LocalDateTime getResolvedAt(){return this.resolvedAt;}
    public void deny(){this.denied = true; this.resolve();}


    public static UserNotification[] sortByCreatedDate(ArrayList<UserNotification> notifications){
        /*
        Sorts ArrayList<UserNotification> in decreasing order of created dates. Returns sorted UserNotification[].
        Latest created UserNotification Will always be on the zeroth index.
         */
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
    public static UserNotification[] sortByResolvedDate(ArrayList<UserNotification> notifications){
        /*
        Sorts ArrayList<UserNotification> in decreasing order of resolved dates. Returns sorted UserNotification[].
        Latest resolved UserNotification Will always be on the zeroth index.
         */
        UserNotification[] sorted = new UserNotification[notifications.size()];
        sorted = notifications.toArray(sorted);
        int n = sorted.length;
        for (int i = 1; i < n; i++){
            UserNotification item = sorted[i];
            int j = i-1;
            while(j >= 0 && sorted[j].getResolvedAt().isBefore(item.getResolvedAt())){
                sorted[j+1] = sorted[j];
                j -= 1;
            }
            sorted[j+1] = item;
        }
        return sorted;
    }

}
