import java.io.*;
import java.util.ArrayList;


public class ShiftInteractor implements Interactor<Shift>{
  
  public ArrayList<Shift> readData(){

      ArrayList<Shift> shiftList = new ArrayList<>();

      Object obj = null;

      boolean isExist = true;

      try{
          FileInputStream file = new FileInputStream("shifts.ser");
          ObjectInputStream input = new ObjectInputStream(file);
          while(isExist){
              if(file.available() != 0){
                  obj = input.readObject();
                  shiftList.add((Shift) obj);
              }
              else{
                  isExist =false;
              }
              input.close();
          }
      } catch (IOException | ClassNotFoundException e){
          System.out.println(e);
      }
        return shiftList;

  }

   public void writeData(Shift shift){

       try{
           FileOutputStream file = new FileOutputStream("shifts.ser");
           ObjectOutputStream output = new ObjectOutputStream(file);
           output.writeObject(shift);
           output.close();
       } catch (IOException e){
           System.out.println(e);
       }

    }
  
}
