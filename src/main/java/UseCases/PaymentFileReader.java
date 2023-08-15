package UseCases;

import Entities.Payment;

import java.time.LocalDateTime;
import java.util.*;
/**
 * The PaymentFileReader class provides methods to read payment records from a data source.
 */
public class PaymentFileReader{
    private static PaymentFileReader instance;
    private Payment payment;
    private PaymentInteractor interactor;
    private ArrayList<Payment> list;

    private PaymentFileReader() {
        payment = new Payment(-21, 15.56F, LocalDateTime.now(), -71);
        interactor = new PaymentInteractor();
        list = interactor.readData();
    }
    /**
     * Get the singleton instance of PaymentFileReader.
     *
     * @return The instance of PaymentFileReader.
     */

    public PaymentFileReader(String isTest) {
        payment = new Payment(-21, 15.56F, LocalDateTime.now(), -71);
        interactor = new PaymentInteractor("test");
        list = interactor.readData();
    }
    public static PaymentFileReader getInstance(){
        if (instance == null) {
            instance = new PaymentFileReader();
        }
        return instance;
    }
    /**
     * Check if the specified payment ID matches the currently loaded payment record, and update if necessary.
     *
     * @param id The ID of the payment.
     */
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

    /**
     * Update the list of payment records from the data source.
     */
    public void update() {
        list = interactor.readData();
    }

    /**
     * Get a list of payment IDs for payments made on the specified date.
     *
     * @param date The date for which to retrieve payment IDs.
     * @return A list of payment IDs.
     */
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
        return payment.getPaymentAmount();
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (Payment u:list){
            ids.add(u.getPaymentId());
        }
        return ids;
    }
    /**
     * Get the Payment object for the specified payment ID.
     *
     * @param id The ID of the payment.
     * @return The Payment object.
     */
    public Payment getPayment(int id){
        checkPayment(id);
        return payment;
    }


}
