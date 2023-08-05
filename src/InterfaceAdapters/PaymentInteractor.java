package InterfaceAdapters;

import Entities.Payment;

import java.io.*;
import java.util.ArrayList;
public class PaymentInteractor implements Interactor<Payment> {

  public ArrayList<Payment> readData() {

      ArrayList<Payment> payList = new ArrayList<>();

      try{
          FileInputStream file = new FileInputStream("notifications.ser");
          ObjectInputStream input = new ObjectInputStream(file);
          payList.addAll ((ArrayList<Payment>) input.readObject()) ;

      } catch (IOException | ClassNotFoundException e){
          System.out.println(e);
      }
      return payList;


  }

  public void writeData(Payment payment){

      ArrayList<Payment> paymentList = this.readData();
      paymentList.add(payment);
      try{
          FileOutputStream file = new FileOutputStream("payments.ser");
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(payment);
          output.close();
      } catch (IOException e){
          System.out.println(e);
      }

    }

    @Override
    public void update(Payment p) {
        ArrayList<Payment> paymentList = this.readData();
        paymentList.removeIf(payment -> p.get)//Remove if payment id's are the same - need the feature.
        paymentList.add(payment);
        try{
            FileOutputStream file = new FileOutputStream("payments.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(payment);
            output.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

}
