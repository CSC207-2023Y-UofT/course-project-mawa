import java.io.*;
import java.util.ArrayList;
public class PaymentInteractor implements Interactor<Payment>{

  public ArrayList<Payment> readData() {
      ArrayList<Payment> payList = new ArrayList<>();
      Object obj = null;

      boolean isExist = true;

      try{
          FileInputStream file = new FileInputStream("payments.ser");
          ObjectInputStream input = new ObjectInputStream(file);
          while(isExist){
              if(file.available() != 0){
                  obj = input.readObject();
                  payList.add((Payment) obj);
              }
              else{
                  isExist =false;
              }
              input.close();
          }
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
          output.writeObject(userList);
          output.close();
      } catch (IOException e){
          System.out.println(e);
      }

    }

}
