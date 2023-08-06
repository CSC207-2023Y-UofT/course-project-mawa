package UseCases;

import java.io.*;
import java.util.ArrayList;
import UseCases.*;
import Entities.*;

public class UserNotificationInteractor implements Interactor<UserNotification> {

   public ArrayList<UserNotification> readData(){

       ArrayList<UserNotification> notifList = new ArrayList<>();

       try{
           FileInputStream file = new FileInputStream("notifications.ser");
           ObjectInputStream input = new ObjectInputStream(file);
           notifList.addAll ((ArrayList<UserNotification>) input.readObject()) ;

       } catch (IOException | ClassNotFoundException e){
           System.out.println(e);
       }
       return notifList;


  }

    public void update(UserNotification n){
       ArrayList<UserNotification> notifs = this.readData();
       for (UserNotification noti: notifs){
           notifs.removeIf(notif -> n.getNotifId() == noti.getNotifId());
       }
       notifs.add(n);
      try {
          FileOutputStream file = new FileOutputStream("notifications.ser");
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(notifs);
          output.close();
      } catch (IOException e) {
          System.out.println(e);
      }
  }

  public void writeData(UserNotification notification){

      ArrayList<UserNotification> notifList = this.readData();
      notifList.add(notification);
      try {
          FileOutputStream file = new FileOutputStream("notifications.ser");
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(notifList);
          output.close();
      } catch (IOException e) {
          System.out.println(e);
      }

    }

    public ArrayList<UserNotification> getNotificationByUserID(int userID){
        ArrayList<UserNotification> notifications = this.readData();
        ArrayList<UserNotification> userNotifications = new ArrayList<UserNotification>();
        for (UserNotification n: notifications){
            if (n.getSenderId() == userID || n.getRecipientId() == userID){
                userNotifications.add(n);
            }
        }
        return userNotifications;
    }
  
}

