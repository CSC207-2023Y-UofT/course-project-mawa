package UseCases;

import Entities.UserNotification;
import Entities.UserNotificationRequest;

import java.time.LocalDateTime;
import java.util.*;

public class NotificationFileReader{
    private static NotificationFileReader instance;
    private UserNotification userNotification;
    private UserNotificationInteractor interactor;
    private ArrayList<UserNotification> list;

    private NotificationFileReader() {
        userNotification = new UserNotificationRequest(-77,-78,45,"",LocalDateTime.now());
        interactor = new UserNotificationInteractor();
        list = interactor.readData();
    }
    public static NotificationFileReader getInstance(){
        if (instance == null) {
            synchronized (NotificationFileReader.class) {
                if (instance == null) {
                    instance = new NotificationFileReader();
                }
            }
        }
        return instance;
    }

    private void checkNotification(int id){
        if (userNotification.getNotifId() == id){
            return;
        }
        for (UserNotification u:list){
            if(u.getNotifId() == id){
                this.userNotification = u;
                return;
            }
        }
        System.out.println("Invalid Notification ID");
    }


    public ArrayList<Integer> getIds(LocalDateTime date){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getDate() == date){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIdsBySender(int id){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getSenderId() == id){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIdsByRecipient(int id){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getRecipientId() == id){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIds(boolean resolved){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getResolvedStatus() == resolved){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getDenyIds(boolean deny){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getDenyStatus() == deny){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIds(String type){
        ArrayList<Integer> ids = new ArrayList<>();
        for (UserNotification u:list){
            if(u.getClass().getSimpleName().equals(type)){
                ids.add(u.getNotifId());
            }
        }
        return ids;
    }

    public String getType(int id){
        checkNotification(id);
        return userNotification.getClass().getSimpleName();
    }

    public boolean getResolved(int id){
        checkNotification(id);
        return userNotification.getResolvedStatus();
    }
    public LocalDateTime getDateCreated(int id){
        checkNotification(id);
        return userNotification.getDate();
    }

    public int getRecipientId(int id){
        checkNotification(id);
        return userNotification.getRecipientId();
    }

    public int getSenderId(int id){
        checkNotification(id);
        return userNotification.getSenderId();
    }

    public boolean getDenyStatus(int id){
        checkNotification(id);
        return userNotification.getDenyStatus();
    }


}
