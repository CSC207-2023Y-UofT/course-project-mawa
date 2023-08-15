package UseCases;

import Entities.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import Entities.Shift;
import Entities.User;
import InterfaceAdapters.CalendarModel;
import UseCases.ShiftFileReader;
import UseCases.ShiftInteractor;
import UseCases.UserFileReader;
import UseCases.UserInteractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PaymentHistoryModelTest {

    @BeforeAll
     static void setup() throws IOException {
        UserInteractor userInteractor = new UserInteractor();
        userInteractor.update(new User("first", "last", "", "", "",
                400, 123, "2005-01-07", null, "Salary Worker", 12));
        PaymentInteractor paymentInteractor= new PaymentInteractor();
        paymentInteractor.update(new Payment(400,12,LocalDateTime.now(),400));


    }

    @Test
    void paymentHistoryModel() {
       // new FileWriter("Users.ser", false).close();
        PaymentHistoryModel paymentHistoryModel=new PaymentHistoryModel();

        ArrayList arrayList = new ArrayList<>(Collections.singleton(" This Employee has been paid " +
                12.0 + " on " + "2023" + " / " + "08" + " / " + "15"));

        assertEquals( arrayList,paymentHistoryModel.PaymentHistoryModel(400));

    }

    @Test
    void label() {
        PaymentHistoryModel paymentHistoryModel=new PaymentHistoryModel();
        assertEquals("last first",paymentHistoryModel.label(400));

    }



}