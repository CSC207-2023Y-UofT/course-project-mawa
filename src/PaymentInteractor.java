import java.io.*;
import java.util.ArrayList;
public class PaymentInteractor implements Interactor{

  public ArrayList<Payment> readData() throws IOException, ClassNotFoundException {
      ArrayList<Payment> payList = new ArrayList<Payment>;
      FileInputStream file = new FileInputStream("payments.ser");
      ObjectInputStream input = new ObjectInputStream(file);
      try {
          while (true){
              payList.add((Payment) input.readObject());
          }
      } catch (OptionalDataException e){
          if (!e.eof){
              throw e;
          }
      } finally {
          input.close();
      }
      return payList;


  }

  public void writeData(Payment payment){
      FileOutputStream file = new FileOutputStream("payments.ser");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(payment);
      output.close();

    }

}
