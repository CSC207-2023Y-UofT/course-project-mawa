package UseCases;

import UseCases.*;
import Entities.*;

import java.io.*;
import java.util.ArrayList;
public class PaymentInteractor implements Interactor<Payment> {

  public ArrayList<Payment> readData() {

      ArrayList<Payment> payList = new ArrayList<>();

      try{
          FileInputStream file = new FileInputStream("payments.ser");
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

}
