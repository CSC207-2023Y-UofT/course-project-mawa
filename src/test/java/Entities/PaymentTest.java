package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentTest {
    public ArrayList<Payment>payments;
    public ArrayList<User> users;
    public ArrayList<LocalDateTime> dateTimes;
    @BeforeEach
    public void setUp() {
        users = new ArrayList<>();dateTimes = new ArrayList<>();payments = new ArrayList<>();
        User user1=new User("sur","first","Male","email",
                "testrole",1,11,"2022-12-12", "ali22".toCharArray(),"Salary Worker",22);
        users.add(user1);
        dateTimes.add((LocalDateTime.of(2022, 2, 10, 1, 10)));
        Payment pay1=new Payment(1,12, dateTimes.get(0),2);
        payments.add(pay1);

    }

    @Test
    public void getPaymentId() {
        assertEquals(2,payments.get(0).getPaymentId());
    }

    @Test
    public void getEmployee() {
        assertEquals(1,payments.get(0).getEmployee());
    }

    @Test
    public void getPaymentAmount() {
        assertEquals(12,payments.get(0).getPaymentAmount());
    }
    @Test
    public void getDate(){

    assertEquals(dateTimes.get(0),payments.get(0).getDate());
    }

}