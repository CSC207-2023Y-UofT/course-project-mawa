package UseCases;

import Entities.Payment;
import Entities.Shift;
import Entities.User;

import java.time.LocalDateTime;
import java.util.*;

public class PaymentFileReader{
    private static PaymentFileReader instance;
    private Payment payment;
    private PaymentInteractor interactor;
    private ArrayList<Payment> list;

    public PaymentFileReader() {
        payment = new Payment(-21, 15.56F, LocalDateTime.now(), -71);
        interactor = new PaymentInteractor();
        list = interactor.readData();
    }

    public static PaymentFileReader getInstance(){
        if (instance == null) {
            synchronized (PaymentFileReader.class) {
                if (instance == null) {
                    instance = new PaymentFileReader();
                }
            }
        }
        return instance;
    }
    public void checkPayment(int id){
        if (payment.getPaymentId() == id){
            return;
        }
        for (Payment p:list){
            if(p.getPaymentId() == id){
                this.payment = p;
                return;
            }
        }
        System.out.println("Invalid Payment ID");
    }

    public void update(){
        list = interactor.readData();
    }

    public ArrayList<Integer> getIds(LocalDateTime date){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Payment p:list){
            if (p.getDate() == date){
                ids.add(p.getPaymentId());
            }
        }
        return ids;
    }

    public ArrayList<Integer> getIds(int empId){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Payment p:list){
            if (p.getEmployee() == empId){
                ids.add(p.getPaymentId());
            }
        }
        return ids;
    }
    public LocalDateTime getDate(int id){
        checkPayment(id);
        return payment.getDate();
    }

    public int getEmployeeId(int id){
        checkPayment(id);
        return payment.getEmployee();
    }

    public float getAmount(int id){
        checkPayment(id);
        return payment.getPayment_amount();
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Payment u:list){
            ids.add(u.getPaymentId());
        }
        return ids;
    }

    public Payment getPayment(int id){
        checkPayment(id);
        return payment;
    }


}
