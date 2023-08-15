package UseCases;

import Entities.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The PaymentInteractor class handles reading, writing, and updating payment-related data.
 * It implements the Interactor interface for payment objects.
 */
public class PaymentInteractor implements Interactor<Payment> {

    private String fileName; // Stores the name of the payment data file.

    /**
     * Default constructor that initializes the PaymentInteractor with the default payment data file name.
     */
    public PaymentInteractor() {
        this.fileName = FileNameConstants.PAYMENT_FILE_NAME;
    }

    /**
     * Constructor that specifies the file name to be that of testing data.
     *
     * @param isTest A string identifier indicating the use of a test payment data file.
     */
    public PaymentInteractor(String isTest) {
        this.fileName = "testPayments.ser";
    }

    /**
     * Reads payment data from the payment data file.
     *
     * @return An ArrayList of Payment objects read from the file.
     */
    public ArrayList<Payment> readData() {
        ArrayList<Payment> payList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            payList.addAll((ArrayList<Payment>) input.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return payList;
    }

    /**
     * Writes a new payment object to the payment data file.
     *
     * @param payment The Payment object to be written to the file.
     */
    public void writeData(Payment payment) {
        ArrayList<Payment> paymentList = this.readData();
        paymentList.add(payment);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(paymentList);
            output.close();
            PaymentFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Updates an existing payment object in the payment data file.
     *
     * @param p The updated Payment object.
     */
    @Override
    public void update(Payment p) {
        ArrayList<Payment> paymentList = this.readData();

        // Remove the existing payment with the same paymentId
        paymentList.removeIf(payment -> p.getPaymentId() == payment.getPaymentId());
        paymentList.add(p);

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(paymentList);
            output.close();
            PaymentFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
