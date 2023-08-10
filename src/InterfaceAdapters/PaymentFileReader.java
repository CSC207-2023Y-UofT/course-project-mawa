package InterfaceAdapters;

import UseCases.PaymentFileProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class PaymentFileReader{
    private ArrayList listHM;
    private PaymentFileProcessor processor = PaymentFileProcessor.getInstance();

    public PaymentFileReader() {
        listHM = processor.getHMList();
    }

    public ArrayList<Integer> getIds(LocalDateTime date){
        HashMap hm = (HashMap) listHM.get(PaymentProcessorConstants.DATE);
        return (ArrayList<Integer>)hm.get(date);
    }

    public ArrayList<Integer> getIds(int empId){
        HashMap hm = (HashMap) listHM.get(PaymentProcessorConstants.EMPLOYEE_ID);
        return (ArrayList<Integer>)hm.get(empId);
    }
    public LocalDateTime getDate(int id){
        HashMap hm = (HashMap) listHM.get(PaymentProcessorConstants.ID);
        return (LocalDateTime) ((ArrayList)hm.get(id)).get(PaymentProcessorConstants.DATE);
    }

    public int getEmployeeId(int id){
        HashMap hm = (HashMap) listHM.get(PaymentProcessorConstants.ID);
        return (int)((ArrayList)hm.get(id)).get(PaymentProcessorConstants.EMPLOYEE_ID);
    }

    public float getAmount(int id){
        HashMap hm = (HashMap) listHM.get(PaymentProcessorConstants.ID);
        return (int)((ArrayList)hm.get(id)).get(PaymentProcessorConstants.AMOUNT);
    }


}
