package InterfaceAdapters;
import UseCases.PaymentHistoryModel;

import java.util.ArrayList;

public class PaymentHistoryPresenter {
    private ArrayList<String>  thereturn_list;
    public ArrayList<String> PaymentHistoryPresenter(int emp_id){

        PaymentHistoryModel model= new PaymentHistoryModel();
        thereturn_list= model.PaymentHistoryModel(emp_id);
        return thereturn_list;


    }
    public String getLabel(int employee_id){
        PaymentHistoryModel mod=new PaymentHistoryModel();
        return mod.label(employee_id);

    }


}