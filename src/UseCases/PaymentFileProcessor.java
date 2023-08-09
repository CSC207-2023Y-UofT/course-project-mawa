package UseCases;

import Entities.Payment;

import java.time.LocalDateTime;;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentFileProcessor implements FileProcessor<Payment> {
    private static PaymentFileProcessor instance;
    private PaymentInteractor interactor = new PaymentInteractor();
    private HashMap<Integer, ArrayList<Object>> idToList = new HashMap<Integer, ArrayList<Object>>();
    private HashMap<Integer, ArrayList<Integer>> amountToId = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<LocalDateTime, ArrayList<Integer>> dateToId = new HashMap<LocalDateTime, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<Integer>> empIdToId = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<Integer, Payment> idToPayment = new HashMap<>();
    private ArrayList<HashMap> hmList = new ArrayList<>();

    private PaymentFileProcessor(){
        makeHM();
    }

    public static PaymentFileProcessor getInstance(){
        if (instance == null) {
            synchronized (PaymentFileProcessor.class) {
                if (instance == null) {
                    instance = new PaymentFileProcessor();
                }
            }
        }
        return instance;
    }
    @Override
    public Interactor getInteractor() {
        return interactor;
    }
    @Override
    public ArrayList<HashMap> getHMList() {
        return hmList;
    }
    @Override
    public void makeHM() {
        ArrayList<Payment> payList = interactor.readData();

        makeIdtoList(idToList, payList);
        hmList.add(idToList);
        makeDatetoId(dateToId, payList);
        hmList.add(dateToId);
        makeEmpIdtoId(empIdToId, payList);
        hmList.add(empIdToId);
        makeAmounttoId(amountToId, payList);
        hmList.add(amountToId);

    }

    public HashMap<Integer, Payment> getIdToPayment(){
        return idToPayment;
    }

    public void makeEmpIdtoId(HashMap<Integer, ArrayList<Integer>> empIdToid,
                               ArrayList<Payment> payList){
        for (Payment n : payList){
            if (empIdToid.containsKey(n.getEmployee())){
                empIdToid.get(n.getEmployee()).add(n.getId());
            } else {
                empIdToid.put(n.getEmployee(), (ArrayList<Integer>) List.of(n.getId()));
            }

        }
    }

    public void makeEmpIdtoId(HashMap<Integer, ArrayList<Integer>> empIdToid,
                               ArrayList<Payment> payList, boolean append){
        if (!append){
            empIdToid.clear();
        }
        for (Payment n : payList){
            if (empIdToid.containsKey(n.getEmployee())){
                empIdToid.get(n.getEmployee()).add(n.getId());
            } else {
                empIdToid.put(n.getEmployee(), (ArrayList<Integer>) List.of(n.getId()));
            }
        }
    }
    public void makeAmounttoId(HashMap<Integer, ArrayList<Integer>> amountToid,
                             ArrayList<Payment> payList){
        for (Payment n : payList){
            int i = round(n.getPayment_amount());
            if (amountToid.containsKey(i)){
                amountToid.get(i).add(n.getId());
            } else {
                amountToid.put(i, (ArrayList<Integer>) List.of(n.getId()));
            }

        }
    }

    public void makeAmounttoId(HashMap<Integer, ArrayList<Integer>> amountToid,
                               ArrayList<Payment> payList, boolean append){
        if (!append){
            amountToid.clear();
        }
        for (Payment n : payList){
            int i = round(n.getPayment_amount());
            if (amountToid.containsKey(i)){
                amountToid.get(i).add(n.getId());
            } else {
                amountToid.put(i, (ArrayList<Integer>) List.of(n.getId()));
            }

        }
    }

    static int round(double input) {
        return ((int)(input / 100) * 100);
    }

    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<Payment> payList){
        for (Payment n : payList){
            if (dateToid.containsKey(n.getDate())){
                dateToid.get(n.getDate()).add(n.getId());
            } else {
                dateToid.put(n.getDate(), (ArrayList<Integer>) List.of(n.getId()));
            }

        }
    }

    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<Payment> payList, boolean append){
        if (!append){
            dateToid.clear();
        }
        for (Payment n : payList){
            if (dateToid.containsKey(n.getDate())){
                dateToid.get(n.getDate()).add(n.getId());
            } else {
                dateToid.put(n.getDate(), (ArrayList<Integer>) List.of(n.getId()));
            }

        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<Payment> payList){
        for (Payment n : payList){
            idToList.put(n.getId(), toList(n));
            idToPayment.put(n.getId(), n);
        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<Payment> payList, boolean append){
        if (!append){
            idToList.clear();
            idToPayment.clear();
        }
        for (Payment n : payList){
            idToList.put(n.getId(), toList(n));
            idToPayment.put(n.getId(), n);
        }
    }
    @Override
    public ArrayList<Object> toList(Payment pay) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(pay.getEmployeeId());
        list.add(pay.getPayment_amount());
        list.add(pay.getDate());

        return list
    }


}
