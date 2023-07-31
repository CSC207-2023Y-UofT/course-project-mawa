import java.io.*;
import java.util.ArrayList;


public class ShiftInteractor implements Interactor<Shift>{
  
  public ArrayList<Shift> readData() throws IOException, ClassNotFoundException{
        ArrayList<Shift> shiftList = new ArrayList<Shift>();
        FileInputStream file = new FileInputStream("shifts.ser");
        ObjectInputStream input = new ObjectInputStream(file);
        try {
            while (true){
                shiftList.add((Shift) input.readObject());
            }
        } catch (OptionalDataException e){
            if (!e.eof){
                throw e;
            }
        } finally {
            input.close();
        }
        return shiftList;

  }

   public void writeData(Shift shift) throws IOException{

       FileOutputStream file = new FileOutputStream("shifts.ser");
       ObjectOutputStream output = new ObjectOutputStream(file);
       output.writeObject(shift);
       output.close();

    }
  
}
