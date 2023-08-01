import javax.management.Notification;
import java.io.*;
import java.util.ArrayList;

public class NotificationInteractor implements Interactor<Notification>{

   public ArrayList<Notification> readData() throws IOException, ClassNotFoundException{

       ArrayList<Notification> notifList = new ArrayList<Notification>();
       FileInputStream file = new FileInputStream("notifications.ser");
       ObjectInputStream input = new ObjectInputStream(file);
       try {
           while (true){
               notifList.add((Notification) input.readObject());
           }
       } catch (OptionalDataException e){
           if (!e.eof){
               throw e;
           }
       } finally {
           input.close();
       }
       return notifList;


  }



  public void writeData(Notification notification) throws IOException{

      FileOutputStream file = new FileOutputStream("notifications.ser");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(notifications);
      output.close();

    }
  
}
