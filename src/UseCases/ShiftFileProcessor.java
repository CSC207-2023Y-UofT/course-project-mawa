package UseCases;

import Entities.Payment;
import Entities.Shift;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShiftFileProcessor implements FileProcessor<Shift> {
    private static ShiftFileProcessor instance;
    private ShiftInteractor interactor = new ShiftInteractor();
    private HashMap<Integer, ArrayList<Object>> idToList = new HashMap<Integer, ArrayList<Object>>();
    private HashMap<Integer, ArrayList<Integer>> empIdToId = new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<LocalDateTime, ArrayList<Integer>> dateToId = new HashMap<LocalDateTime, ArrayList<Integer>>();
    private ArrayList<HashMap> hmList = new ArrayList<>();

    private ShiftFileProcessor(){
        makeHM();
    }

    public static ShiftFileProcessor getInstance(){
        if (instance == null) {
            synchronized (ShiftFileProcessor.class) {
                if (instance == null) {
                    instance = new ShiftFileProcessor();
                }
            }
        }
        return instance;
    }

    @Override
    public void makeHM() {
        ArrayList<Shift> shiftList = interactor.readData();

        makeIdtoList(idToList, shiftList);
        hmList.add(idToList);
        makeDatetoId(dateToId, shiftList);
        hmList.add(dateToId);
        makeAmounttoId(empIdToId, shiftList);
        hmList.add(empIdToId);
    }

    public void makeAmounttoId(HashMap<Integer, ArrayList<Integer>> empIdToid,
                               ArrayList<Shift> shiftList){
        for (Shift n : shiftList){
            for (int i : n.getCoworkers()){
                if (empIdToid.containsKey(i)){
                    empIdToid.get(i).add(n.getShiftId());
                } else {
                    empIdToid.put(i, (ArrayList<Integer>) List.of(n.getShiftId()));
                }
            }
        }
    }

    public void makeEmpIdtoId(HashMap<Integer, ArrayList<Integer>> empIdToid,
                               ArrayList<Shift> shiftList, boolean append){
        if (!append){
            empIdToid.clear();
        }
        for (Shift n : shiftList){
            for (int i : n.getCoworkers()){
                if (empIdToid.containsKey(i)){
                    empIdToid.get(i).add(n.getShiftId());
                } else {
                    empIdToid.put(i, (ArrayList<Integer>) List.of(n.getShiftId()));
                }
            }
        }
    }

    static int round(double input) {
        return (int) ((input / 100) * 100);
    }

    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<Shift> shiftList){
        for (Shift n : shiftList){
            if (dateToid.containsKey(n.getTime())){
                dateToid.get(n.getTime()).add(n.getShiftId());
            } else {
                dateToid.put(n.getTime(), (ArrayList<Integer>) List.of(n.getShiftId()));
            }

        }
    }

    public void makeDatetoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<Shift> shiftList, boolean append){
        if (!append){
            dateToid.clear();
        }
        for (Shift n : shiftList){
            if (dateToid.containsKey(n.getTime())){
                dateToid.get(n.getTime()).add(n.getShiftId());
            } else {
                dateToid.put(n.getTime(), (ArrayList<Integer>) List.of(n.getShiftId()));
            }

        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<Shift> shiftList){
        for (Shift n : shiftList){
            idToList.put(n.getShiftId(), toList(n));
        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<Shift> shiftList, boolean append){
        if (!append){
            idToList.clear();
        }
        for (Shift n : shiftList){
            idToList.put(n.getShiftId(), toList(n));
        }
    }

    @Override
    public ArrayList<Object> toList(Shift shift) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(shift.getShiftId());
        list.add(shift.getCoworkers());
        list.add(shift.getTime());
        list.add(shift.getDuration());
        return list;
    }


}
