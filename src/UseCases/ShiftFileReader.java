package UseCases;

import InterfaceAdapters.ShiftProcessorConstants;
import UseCases.PaymentFileProcessor;
import UseCases.ShiftFileProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ShiftFileReader{
    private ShiftFileProcessor processor = ShiftFileProcessor.getInstance();
    ArrayList listHM;

    public ShiftFileReader() {
        listHM = processor.getHMList();
    }

    public ArrayList<Integer> getIds(LocalDate date){
        HashMap hm = (HashMap) listHM.get(ShiftProcessorConstants.DATE);
        return (ArrayList<Integer>)hm.get(date);
    }

    public ArrayList<Integer> getIds(int empId){
        HashMap hm = (HashMap) listHM.get(ShiftProcessorConstants.EMPLOYEE_ID);
        return (ArrayList<Integer>)hm.get(empId);
    }
    public LocalDateTime getDate(int id){
        HashMap hm = (HashMap) listHM.get(ShiftProcessorConstants.ID);
        return (LocalDateTime) ((ArrayList)hm.get(id)).get(ShiftProcessorConstants.DATE);
    }

    public ArrayList<Integer> getEmployeeId(int id){
        HashMap hm = (HashMap) listHM.get(ShiftProcessorConstants.ID);
        return (ArrayList<Integer>)((ArrayList)hm.get(id)).get(ShiftProcessorConstants.EMPLOYEE_ID);
    }

    public float getDuration(int id){
        HashMap hm = (HashMap) listHM.get(ShiftProcessorConstants.ID);
        return (int)((ArrayList)hm.get(id)).get(ShiftProcessorConstants.DURATION);
    }

}
