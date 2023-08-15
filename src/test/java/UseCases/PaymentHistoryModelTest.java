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
import java.util.List;

class PaymentHistoryModelTest {

    @BeforeAll
     static void setup() throws IOException {
        new FileWriter("testPayment.ser", false).close();
        UserInteractor userInteractor = new UserInteractor("test");
        userInteractor.writeData(new User("", "", "", "", "",
                3, 123, "2005-01-07", null, "Salary Worker", 12));
        userInteractor.writeData(new User("", "", "", "", "",
                4, 123, "2005-01-07", null, "Salary Worker", 12));
        userInteractor.writeData(new User("", "", "", "", "",
                1, 123, "2005-01-07", null, "HR", 12));
        new FileWriter("testPayment.ser", false).close();
        PaymentInteractor paymentInteractor= new PaymentInteractor("test");
        //paymentInteractor.writeData(new Payment(4,12,,1));

    }

    @Test
    void paymentHistoryModel() {
    }

    @Test
    void label() {
    }

    @Test
    void user_paymentlist() {
    }

    @Test
    void paylistTolist() {
    }
}