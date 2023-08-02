import javax.management.Notification;
import java.io.*;
import java.util.ArrayList;


public class ShiftInteractor implements Interactor<Shift>{
  
  public ArrayList<Shift> readData(){

      ArrayList<Shift> shiftList = new ArrayList<>();

      try{
          FileInputStream file = new FileInputStream("notifications.ser");
          ObjectInputStream input = new ObjectInputStream(file);
          shiftList.addAll ((ArrayList<Shift>) input.readObject()) ;

      } catch (IOException | ClassNotFoundException e){
          System.out.println(e);
      }
      return shiftList;

  }

   public void writeData(Shift shift){

       ArrayList<Shift> shiftList = this.readData();
      shiftList.add(shift);
       try{
           FileOutputStream file = new FileOutputStream("shifts.ser");
           ObjectOutputStream output = new ObjectOutputStream(file);
           output.writeObject(shiftList);
           output.close();
       } catch (IOException e){
           System.out.println(e);
       }

    }
  
}
