import javax.management.Notification;
import java.io.*;
import java.util.ArrayList;

public class NotificationInteractor implements Interactor<Notification>{

   public ArrayList<Notification> readData(){

       ArrayList<Notification> notifList = new ArrayList<>();

       try{
           FileInputStream file = new FileInputStream("notifications.ser");
           ObjectInputStream input = new ObjectInputStream(file);
           notifList.addAll ((ArrayList<Notification>) input.readObject()) ;

       } catch (IOException | ClassNotFoundException e){
           System.out.println(e);
       }
       return notifList;


  }

  public void update(Notification n){
       ArrayList<Notification> notifs = this.readData();
       notifs.removeIf(notif -> n.getNotifId() == notifs.getNotifId());
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



  public void writeData(Notification notification){

      ArrayList<Notification> notifList = this.readData();
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
  
}
