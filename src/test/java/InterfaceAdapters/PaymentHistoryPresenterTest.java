package InterfaceAdapters;

import Entities.Payment;
import Entities.User;
import UseCases.PaymentHistoryModel;
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
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PaymentHistoryPresenterTest {
    public ArrayList<Payment> payments;
    public ArrayList<User> users;
    public ArrayList<LocalDateTime> dateTimes;
    public PaymentHistoryPresenter presenter;
    public PaymentMaker paymentMaker;
    @BeforeAll
    static void setUp() {
        UserInteractor userInteractor = new UserInteractor();
        userInteractor.update(new User("first", "last", "", "", "",
                400, 123, "2005-01-07", null, "Salary Worker", 12));
        PaymentInteractor paymentInteractor= new PaymentInteractor();
        paymentInteractor.update(new Payment(400,12,LocalDateTime.now(),400));

    }

    @Test
    void paymentHistoryPresenter() {
       PaymentHistoryPresenter paymentHistoryPresenter=new PaymentHistoryPresenter();

        ArrayList arrayList = new ArrayList<>(Collections.singleton(" This Employee has been paid " +
                12.0 + " on " + "2023" + " / " + "08" + " / " + "15"));

        assertEquals( arrayList,paymentHistoryPresenter.PaymentHistoryPresenter(400));

    }

    @Test
    void getLabel() {
        PaymentHistoryPresenter paymentHistoryPresenter=new PaymentHistoryPresenter();


        assertEquals( "last first",paymentHistoryPresenter.getLabel(400));

    }
}