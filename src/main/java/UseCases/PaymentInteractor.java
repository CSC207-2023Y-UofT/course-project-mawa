package UseCases;

import Entities.*;

import java.io.*;
import java.util.ArrayList;
public class PaymentInteractor implements Interactor<Payment> {
    private String fileName;
    public PaymentInteractor(){this.fileName = FileNameConstants.PAYMENT_FILE_NAME;}
    public PaymentInteractor(String isTest){this.fileName = "testPayments.ser";}

  public ArrayList<Payment> readData() {

      ArrayList<Payment> payList = new ArrayList<>();

      try{
          FileInputStream file = new FileInputStream(fileName);
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
          FileOutputStream file = new FileOutputStream(fileName);
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(paymentList);
          output.close();
          PaymentFileReader.getInstance().update();
      } catch (IOException e){
          System.out.println(e);
      }

    }

    @Override
    public void update(Payment p) {
        ArrayList<Payment> paymentList = this.readData();
        paymentList.removeIf(payment -> p.getPaymentId() == payment.getPaymentId());
        paymentList.add(p);
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(paymentList);
            output.close();
            PaymentFileReader.getInstance().update();
        } catch (IOException e){
            System.out.println(e);
        }
    }

}
