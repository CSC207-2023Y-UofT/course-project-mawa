package UseCases;

import Entities.Payment;
import Entities.Shift;
import Entities.User;
import InterfaceAdapters.PaymentHistoryPresenter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import UseCases.ShiftFileReader;
import UseCases.ShiftInteractor;
import UseCases.UserInteractor;
import UseCases.PaymentFileReader;


import static org.junit.jupiter.api.Assertions.*;

class PaymentMakerTest {


    public ArrayList<Payment>payments;
    public ArrayList<User> users;
    public ArrayList<LocalDateTime> dateTimes;

    public ArrayList<Shift> shifts;



    @BeforeEach
     void setUp()  {



    }

    @Test
    void wageWorker_Payment() {
        UserInteractor userInteractor = new UserInteractor();
        ShiftInteractor shiftInteractor =new ShiftInteractor();
        users = new ArrayList<>();dateTimes = new ArrayList<>();payments = new ArrayList<>(); shifts=new ArrayList<>();
        User user1=new User("sur","first","Male","email",
                "testrole",200,11,"2022-12-12", "ali22".toCharArray(),"Wage Worker",20);
        users.add(user1);
        ArrayList a=new ArrayList<>();
        a.add(user1.getUserNum());
        dateTimes.add((LocalDateTime.of(2023, 8, 15, 5, 00)));
        Shift shift1=new Shift(dateTimes.get(0),a,3,2);
        shifts.add(shift1);
        userInteractor.update(user1);
        shiftInteractor.update(shift1);

        PaymentMaker paymentMaker=new PaymentMaker(200);
        assertEquals(60,paymentMaker.wageWorker_Payment(200,shifts));

    }


}