package UseCases;

import Entities.Payment;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Unit test for PaymentFileReader class.
 */
public class PaymentFileReaderTest {
    private PaymentFileReader reader;
    private PaymentInteractor interactor;
    private List<Payment> list;
    private List<Integer> idList;
    @BeforeEach
    public void setUp() throws IOException {
        new FileWriter("testPayments.ser", false).close();//clear test payment file
        reader = new PaymentFileReader("test");
        interactor = new PaymentInteractor("test");
        list = Instancio.ofList(Payment.class).size(10).create();
        idList = new ArrayList<>();
        for (Payment s:list){
            interactor.writeData(s);
            idList.add(s.getPaymentId());
        }
        reader.update();
    }
    @Test
    public void testUpdate(){
        reader.update();
        assertEquals(reader.getIds().size(), list.size(), "update should provide new data.");
    }

    @Test
    public void testCheckPayment(){
        reader.checkPayment(idList.get(0));
        assertEquals(list.get(0).getPaymentId(), reader.getPayment(idList.get(0)).getPaymentId(),
                "checkPayment should produce the same Payment as indicated by the id inputted.");
    }
    @Test
    public void testGetAmount(){
        assertEquals(list.get(0).getPaymentAmount(), reader.getAmount(idList.get(0)),
                "The payment amount fetched from PaymentFileReader should be the same" +
                        "as the object's payment amount attribute.");
    }
    @Test
    public void testGetAllIds(){
        assertEquals(idList, reader.getIds(),
                "The id lst retrieved from PaymentFileReader should be the same as a list " +
                        "of ids from the complete Payment list.");
    }

    @Test
    public void testGetIdsByEmpId(){
        int empId = 444;
        list.get(1).setEmployee(empId);
        list.get(3).setEmployee(empId);
        list.get(9).setEmployee(empId);
        for (Payment s:list){
            interactor.update(s);
        }
        reader.update();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(list.get(1).getPaymentId());
        expected.add(list.get(3).getPaymentId());
        expected.add(list.get(9).getPaymentId());
        assertTrue(new HashSet<>(expected).equals(new HashSet<>(reader.getIds(empId))));
    }

}