import Entities.Payment;
import UseCases.PaymentFileReader;
import UseCases.PaymentInteractor;
import org.instancio.Instancio;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaymentFileReader {
    private PaymentFileReader reader;
    private PaymentInteractor interactor;
    private List<Payment> list;
    private List<Integer> idList;
    @BeforeAll
    void setUp(){
        reader = PaymentFileReader.getInstance();
        interactor = new PaymentInteractor();
        list = Instancio.ofList(Payment.class).size(10).create();
        idList = new ArrayList<>();
        for (Payment s:list){
            interactor.writeData(s);
            idList.add(s.getPaymentId());
        }
    }
    @Test
    void testUpdate(){
        reader.update();
        assertEquals(reader.getIds().size(), list.size(), "update should provide new data.");
    }

    @Test
    void testCheckPayment(){
        reader.checkPayment(idList.get(0));
        assertEquals(list.get(0), reader.getPayment(idList.get(0)),
                "checkPayment should produce the same Payment as indicated by the id inputted.");
    }
    @Test
    void testGetAmount(){
        assertEquals(list.get(0).getPayment_amount(), reader.getAmount(idList.get(0)),
                "The payment amount fetched from PaymentFileReader should be the same" +
                        "as the object's payment amount attribute.");
    }
    @Test
    void testGetAllIds(){
        assertEquals(idList, reader.getIds(),
                "The id lst retrieved from PaymentFileReader should be the same as a list " +
                        "of ids from the complete Payment list.");
    }

    @Test
    void testGetIdsByEmpId(){
        int empId = 444;
        list.get(1).setEmployee(empId);
        list.get(3).setEmployee(empId);
        list.get(9).setEmployee(empId);
        for (Payment s:list){
            interactor.update(s);
        }
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(list.get(1).getPaymentId());
        expected.add(list.get(3).getPaymentId());
        expected.add(list.get(9).getPaymentId());
        assertEquals(expected, reader.getIds(empId));
    }

}