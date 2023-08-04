package InterfaceAdapters;

import Entities.Shift;

import java.io.*;
import java.util.ArrayList;
import Entities.*;
import InterfaceAdapters.*;

public class ShiftInteractor implements Interactor<Shift> {
  
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

    public void update(Shift s){
        ArrayList<Shift> shifts = this.readData();
        shifts.removeIf(shift -> s.getShiftId() == shift.getShiftId());
        shifts.add(s);
        try{
            FileOutputStream file = new FileOutputStream("shifts.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(shifts);
            output.close();
        } catch (IOException e){
            System.out.println(e);
        }

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
