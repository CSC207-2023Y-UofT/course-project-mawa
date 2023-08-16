package UseCases;


import Entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentInteractorTest {

    PaymentInteractor pi = new PaymentInteractor("t");

    Payment p;

    int id;

    int payID;

    @BeforeEach
    public void Setup(){
        //Make a payment to read/write.
        UserFactory uf = new UserFactory("p");
        uf.makeUser("Smith", "Alan", "Male", "2003", "01",
                "06", 6475784453L, "alan.smith@gmail.com", "Janitor",
                "Salary Worker", "alan1234", 120000F);
        id = uf.getNumUsers();
        payID = pi.readData().size() + 1;
        p = new Payment(id, 10000, LocalDateTime.of(2023, 9, 8, 5, 55),
                payID);

    }

    @Test
    public void TestReadWrite(){
        //Add the payment and read it, and verify that the attributes match.
        pi.writeData(p);
        Payment pRead = pi.readData().get(pi.readData().size() - 1);
        assertEquals(p.getPaymentId(), pRead.getPaymentId());
        assertEquals(p.getEmployee(), pRead.getEmployee());
        assertEquals(p.getPaymentAmount(), pRead.getPaymentAmount());
        assertEquals(p.getDate(), pRead.getDate());
    }

    @Test
    public void TestUpdate(){
        //Modify the amount for p, and verify that it is modified in the file.
        pi.writeData(p);
        p = new Payment(id, 12000, LocalDateTime.of(2023, 9, 8, 5, 55),
                payID);
        pi.update(p);
        Payment pRead = pi.readData().get(pi.readData().size() - 1);
        assertEquals(pRead.getPaymentAmount(), 12000);
    }


}
