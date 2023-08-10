package UseCases;

import UseCases.*;
import Entities.*;

import java.io.*;
import java.util.ArrayList;

public class ShiftInteractor implements Interactor<Shift> {
  
  public ArrayList<Shift> readData(){

      ArrayList<Shift> shiftList = new ArrayList<>();

      try{
          FileInputStream file = new FileInputStream(FileNameConstants.SHIFT_FILE_NAME);
          ObjectInputStream input = new ObjectInputStream(file);
          shiftList.addAll ((ArrayList<Shift>) input.readObject()) ;

      } catch (IOException | ClassNotFoundException e){
          System.out.println(e);
      }
      return shiftList;

  }

  public Shift getShiftByID(int shiftID){
      ArrayList<Shift> shifts = this.readData();
      for (Shift shift: shifts){
          if(shift.getShiftId() == shiftID){
              return shift;
          }
      }
      return null;
  }

    public void update(Shift s){
        ArrayList<Shift> shifts = this.readData();
        shifts.removeIf(shift -> s.getShiftId() == shift.getShiftId());
        shifts.add(s);
        try{
            FileOutputStream file = new FileOutputStream(FileNameConstants.SHIFT_FILE_NAME);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(shifts);
            output.close();
            ShiftFileReader.getInstance().update();
        } catch (IOException e){
            System.out.println(e);
        }

    }

   public void writeData(Shift shift){

       ArrayList<Shift> shiftList = this.readData();
      shiftList.add(shift);
       try{
           FileOutputStream file = new FileOutputStream(FileNameConstants.SHIFT_FILE_NAME);
           ObjectOutputStream output = new ObjectOutputStream(file);
           output.writeObject(shiftList);
           output.close();
           ShiftFileReader.getInstance().update();
       } catch (IOException e){
           System.out.println(e);
       }

    }
  
}
