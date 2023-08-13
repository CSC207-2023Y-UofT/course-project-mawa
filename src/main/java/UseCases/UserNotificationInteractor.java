package UseCases;

import java.io.*;
import java.util.ArrayList;
import Entities.*;

public class UserNotificationInteractor implements Interactor<UserNotification> {
    private String fileName;
    public UserNotificationInteractor(){ this.fileName = FileNameConstants.NOTIFICATION_FILE_NAME;}
    public UserNotificationInteractor(String isTest){this.fileName = "testNotifications.ser";}

   public ArrayList<UserNotification> readData(){

       ArrayList<UserNotification> notifList = new ArrayList<>();

       try{
           FileInputStream file = new FileInputStream(fileName);
           ObjectInputStream input = new ObjectInputStream(file);
           notifList.addAll ((ArrayList<UserNotification>) input.readObject()) ;

       } catch (IOException | ClassNotFoundException e){
           System.out.println(e);
       }
       return notifList;


  }

    public void update(UserNotification n){
       ArrayList<UserNotification> notifs = this.readData();
       notifs.removeIf(notif -> n.getNotifId() == notif.getNotifId());
       notifs.add(n);
      try {
          FileOutputStream file = new FileOutputStream(fileName);
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(notifs);
          output.close();
          NotificationFileReader.getInstance().update();
      } catch (IOException e) {
          System.out.println(e);
      }
  }

  public void writeData(UserNotification notification){

      ArrayList<UserNotification> notifList = this.readData();
      notifList.add(notification);
      try {
          FileOutputStream file = new FileOutputStream(fileName);
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(notifList);
          output.close();
          NotificationFileReader.getInstance().update();
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

