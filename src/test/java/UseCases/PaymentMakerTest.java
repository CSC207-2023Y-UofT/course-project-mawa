package UseCases;

import Entities.Payment;
import Entities.Shift;
import Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


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
    void wageWorker_Payment() throws IOException {
        new FileWriter("testPayments.ser", false).close();
        UserInteractor userInteractor = new UserInteractor();
        ShiftInteractor shiftInteractor =new ShiftInteractor();
        users = new ArrayList<>();dateTimes = new ArrayList<>();payments = new ArrayList<>(); shifts=new ArrayList<>();
        User user1=new User("sur","first","Male","email",
                "testrole",45890,11,"2022-12-12", "ali22".toCharArray(),"Wage Worker",20);
        users.add(user1);
        ArrayList a=new ArrayList<>();
        a.add(user1.getUserNum());
        dateTimes.add((LocalDateTime.of(2023, 8, 15, 5, 00)));
        Shift shift1=new Shift(dateTimes.get(0),a,3,2);
        shifts.add(shift1);
        userInteractor.update(user1);
        shiftInteractor.update(shift1);

        PaymentMaker paymentMaker=new PaymentMaker(45890,"test");
        paymentMaker.reader.update();

        assertEquals(60,paymentMaker.WageWorkerPayment(45890,shifts));

    }


}