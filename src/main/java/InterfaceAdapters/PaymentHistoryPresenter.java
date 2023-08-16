package InterfaceAdapters;
import UseCases.PaymentHistoryModel;

import java.util.ArrayList;

public class PaymentHistoryPresenter {
    private ArrayList<String> thereturnList;

    /**
     *
     * @param empID is th id of the employee
     * @return will return the paymenthistory list as a list of strings
     */
    public ArrayList<String> PaymentHistoryPresenter(int empID){

        PaymentHistoryModel model= new PaymentHistoryModel();
        thereturnList = model.PaymentHistoryModel(empID);
        return thereturnList;


    }
    public String getLabel(int employeeID){
        PaymentHistoryModel mod=new PaymentHistoryModel();
        return mod.label(employeeID);

    }


}