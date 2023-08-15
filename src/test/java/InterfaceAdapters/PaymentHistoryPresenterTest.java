package InterfaceAdapters;

import Entities.Payment;
import Entities.User;
import UseCases.PaymentInteractor;
import UseCases.PaymentMaker;
import UseCases.UserInteractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PaymentHistoryPresenterTest {
    public ArrayList<Payment> payments;
    public ArrayList<User> users;
    public ArrayList<LocalDateTime> dateTimes;
    public PaymentHistoryPresenter presenter;
    public PaymentMaker paymentMaker;
    @BeforeAll
    void setUp() throws IOException {
        new FileWriter("testPayment.ser", false).close();
            PaymentInteractor paymentInteractor = new PaymentInteractor("test");
        presenter=new PaymentHistoryPresenter();
        users = new ArrayList<>();dateTimes = new ArrayList<>();payments = new ArrayList<>();
        User user1=new User("sur","first","Male","email",
                "testrole",1,11,"2022-12-12", "ali22".toCharArray(),"Salary Worker",22);
        users.add(user1);
        dateTimes.add((LocalDateTime.of(2022, 2, 10, 1, 10)));
        Payment pay1=new Payment(1,12, dateTimes.get(0),2);
        payments.add(pay1);

    }

    @Test
    void paymentHistoryPresenter() {
        paymentMaker=new PaymentMaker(1);
        ArrayList<String> pay_array= new ArrayList<>();
        pay_array.add("This Employee has been paid the amount of 12 on 2022 / 02 / 10");
        assertEquals(pay_array,presenter.PaymentHistoryPresenter(1));
    }

    @Test
    void getLabel() {
    }
}