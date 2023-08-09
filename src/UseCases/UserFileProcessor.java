package UseCases;

import Entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserFileProcessor implements FileProcessor<User>{
    private static UserFileProcessor instance;
    private UserInteractor interactor = new UserInteractor();
    private HashMap<Integer, ArrayList<Object>> idToList = new HashMap<Integer, ArrayList<Object>>();
    private HashMap<Long, ArrayList<Integer>> phoneToId = new HashMap<>();
    private HashMap<char[], ArrayList<Integer>> pwdToId = new HashMap<>();
    private HashMap<LocalDate, ArrayList<Integer>> dobToId = new HashMap<>();
    private HashMap<Boolean, ArrayList<Integer>> activeToId = new HashMap<Boolean, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> typeToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> surnameToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> firstNameToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> genderToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> emailToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, ArrayList<Integer>> roleNameToId = new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, User> idToUser = new HashMap<>();
    private ArrayList<HashMap> hmList = new ArrayList<>();

    private UserFileProcessor(){
        makeHM();
    }

    public static UserFileProcessor getInstance(){
        if (instance == null) {
            synchronized (UserFileProcessor.class) {
                if (instance == null) {
                    instance = new UserFileProcessor();
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
        ArrayList<User> userList = interactor.readData();
        makeIdtoList(idToList, userList);
        hmList.add(idToList);
        makeTypetoId(typeToId, userList);
        hmList.add(typeToId);
        makeActivetoId(activeToId, userList);
        hmList.add(activeToId);
        makeRoleNametoId(roleNameToId, userList);
        hmList.add(roleNameToId);
        makeLastNametoId(surnameToId, userList);
        hmList.add(surnameToId);
        makeFirstNametoId(firstNameToId, userList);
        hmList.add(firstNameToId);
        makeDobtoId(dobToId, userList);
        hmList.add(dobToId);
        makeGendertoId(genderToId, userList);
        hmList.add(genderToId);
        makePhoneNumtoId(phoneToId, userList);
        hmList.add(phoneToId);
        makeEmailtoId(emailToId, userList);
        hmList.add(emailToId);
        makePwdtoId(pwdToId, userList);
        hmList.add(pwdToId);

    }

    public HashMap<Integer, User> getIdToUser(){
        return idToUser;
    }

    public void makePwdtoId(HashMap<char[], ArrayList<Integer>> pwdToid,
                                 ArrayList<User> userList){
        for (User n : userList){
            if (pwdToid.containsKey(n.getPassword())){
                pwdToid.get(n.getPassword()).add(n.getUserNum());
            } else {
                pwdToid.put(n.getPassword(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makePwdtoId(HashMap<char[], ArrayList<Integer>> pwdToid,
                             ArrayList<User> userList, boolean append){
        if(!append){
            pwdToid.clear();
        }
        for (User n : userList){
            if (pwdToid.containsKey(n.getPassword())){
                pwdToid.get(n.getPassword()).add(n.getUserNum());
            } else {
                pwdToid.put(n.getPassword(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makePhoneNumtoId(HashMap<Long, ArrayList<Integer>> phoneToid,
                              ArrayList<User> userList){
        for (User n : userList){
            if (phoneToid.containsKey(n.getPhoneNum())){
                phoneToid.get(n.getPhoneNum()).add(n.getUserNum());
            } else {
                phoneToid.put(n.getPhoneNum(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void phoneNumtoId(HashMap<Long, ArrayList<Integer>> phoneToid,
                              ArrayList<User> userList, boolean append){
        if(!append){
            phoneToid.clear();
        }
        for (User n : userList){
            if (phoneToid.containsKey(n.getPhoneNum())){
                phoneToid.get(n.getPhoneNum()).add(n.getUserNum());
            } else {
                phoneToid.put(n.getPhoneNum(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeEmailtoId(HashMap<String, ArrayList<Integer>> emailToid,
                                 ArrayList<User> userList){
        for (User n : userList){
            if (emailToid.containsKey(n.getEmail())){
                emailToid.get(n.getEmail()).add(n.getUserNum());
            } else {
                emailToid.put(n.getEmail(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeEmailtoId(HashMap<String, ArrayList<Integer>> emailToid,
                                 ArrayList<User> userList, boolean append){
        if(!append){
            emailToid.clear();
        }
        for (User n : userList){
            if (emailToid.containsKey(n.getEmail())){
                emailToid.get(n.getEmail()).add(n.getUserNum());
            } else {
                emailToid.put(n.getEmail(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeRoleNametoId(HashMap<String, ArrayList<Integer>> roleNameToid,
                               ArrayList<User> userList){
        for (User n : userList){
            if (roleNameToid.containsKey(n.getRoleName())){
                roleNameToid.get(n.getRoleName()).add(n.getUserNum());
            } else {
                roleNameToid.put(n.getRoleName(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeRoleNametoId(HashMap<String, ArrayList<Integer>> roleNameToid,
                               ArrayList<User> userList, boolean append){
        if(!append){
            roleNameToid.clear();
        }
        for (User n : userList){
            if (roleNameToid.containsKey(n.getRoleName())){
                roleNameToid.get(n.getRoleName()).add(n.getUserNum());
            } else {
                roleNameToid.put(n.getRoleName(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeGendertoId(HashMap<String, ArrayList<Integer>> genderToid,
                                 ArrayList<User> userList){
        for (User n : userList){
            if (genderToid.containsKey(n.getGender())){
                genderToid.get(n.getGender()).add(n.getUserNum());
            } else {
                genderToid.put(n.getGender(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeGendertoId(HashMap<String, ArrayList<Integer>> genderToid,
                                 ArrayList<User> userList, boolean append){
        if(!append){
            genderToid.clear();
        }
        for (User n : userList){
            if (genderToid.containsKey(n.getGender())){
                genderToid.get(n.getGender()).add(n.getUserNum());
            } else {
                genderToid.put(n.getGender(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }


    public void makeLastNametoId(HashMap<String, ArrayList<Integer>> lNameToid,
                                  ArrayList<User> userList){
        for (User n : userList){
            if (lNameToid.containsKey(n.getSurname())){
                lNameToid.get(n.getSurname()).add(n.getUserNum());
            } else {
                lNameToid.put(n.getSurname(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeLastNametoId(HashMap<String, ArrayList<Integer>> lNameToid,
                                  ArrayList<User> userList, boolean append){
        if(!append){
            lNameToid.clear();
        }
        for (User n : userList){
            if (lNameToid.containsKey(n.getSurname())){
                lNameToid.get(n.getSurname()).add(n.getUserNum());
            } else {
                lNameToid.put(n.getSurname(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeFirstNametoId(HashMap<String, ArrayList<Integer>> fNameToid,
                             ArrayList<User> userList){
        for (User n : userList){
            if (fNameToid.containsKey(n.getFirstname())){
                fNameToid.get(n.getFirstname()).add(n.getUserNum());
            } else {
                fNameToid.put(n.getFirstname(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeFirstNametoId(HashMap<String, ArrayList<Integer>> fNameToid,
                             ArrayList<User> userList, boolean append){
        if(!append){
            fNameToid.clear();
        }
        for (User n : userList){
            if (fNameToid.containsKey(n.getFirstname())){
                fNameToid.get(n.getFirstname()).add(n.getUserNum());
            } else {
                fNameToid.put(n.getFirstname(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeTypetoId(HashMap<String, ArrayList<Integer>> typeToid, ArrayList<User> userList){
        for (User n : userList){
            if (typeToid.containsKey(n.getType())){
                typeToid.get(n.getType()).add(n.getUserNum());
            } else {
                typeToid.put(n.getType(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeTypetoId(HashMap<String, ArrayList<Integer>> typeToid,
                             ArrayList<User> userList, boolean append){
        if(!append){
            typeToid.clear();
        }
        for (User n : userList){
            if (typeToid.containsKey(n.getType())){
                typeToid.get(n.getType()).add(n.getUserNum());
            } else {
                typeToid.put(n.getType(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeActivetoId(HashMap<Boolean, ArrayList<Integer>> activeToid,
                            ArrayList<User> userList){
        for (User n : userList){
            if (activeToid.containsKey(n.isActive())){
                activeToid.get(n.isActive()).add(n.getUserNum());
            } else {
                activeToid.put(n.isActive(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeActivetoId(HashMap<Boolean, ArrayList<Integer>> activeToid,
                            ArrayList<User> userList, boolean append){
        if(!append){
            activeToid.clear();
        }
        for (User n : userList){
            if (activeToid.containsKey(n.isActive())){
                activeToid.get(n.isActive()).add(n.getUserNum());
            } else {
                activeToid.put(n.isActive(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }
        }
    }

    public void makeDobtoId(HashMap<LocalDate, ArrayList<Integer>> dateToid,
                             ArrayList<User> userList){
        for (User n : userList){
            if (dateToid.containsKey(n.getDob())){
                dateToid.get(n.getDob()).add(n.getUserNum());
            } else {
                dateToid.put(n.getDob(), (ArrayList<Integer>) List.of(n.getUserNum()));
            }

        }
    }

    public void makeDobtoId(HashMap<LocalDateTime, ArrayList<Integer>> dateToid,
                             ArrayList<UserNotification> userList, boolean append){
        if (!append){
            dateToid.clear();
        }
        for (UserNotification n : userList){
            if (dateToid.containsKey(n.getDate())){
                dateToid.get(n.getDate()).add(n.getNotifId());
            } else {
                dateToid.put(n.getDate(), (ArrayList<Integer>) List.of(n.getNotifId()));
            }

        }
    }

    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<User> userList){
        for (User n : userList){
            idToList.put(n.getUserNum(), toList(n));
            idToUser.put(n.getUserNum(), n);
        }
    }
    public void makeIdtoList(HashMap<Integer, ArrayList<Object>> idToList,
                             ArrayList<User> userList, boolean append){
        if (!append){
            idToList.clear();
            idToUser.clear();
        }
        for (User n : userList){
            idToList.put(n.getUserNum(), toList(n));
            idToUser.put(n.getUserNum(), n);
        }
    }

    @Override
    public ArrayList<Object> toList(User user) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(user.getUserNum());
        list.add(user.getType());
        list.add(user.isActive());
        list.add(user.getRoleName());
        list.add(user.getSurname());
        list.add(user.getFirstname());
        list.add(user.getDob());
        list.add(user.getGender());
        list.add(user.getPhoneNum());
        list.add(user.getEmail());
        list.add(user.getPassword());
        if (user instanceof WageWorker){
            list.add(((WageWorker) user).getHourlyWage());
        } else if (user instanceof SalaryWorker){
            list.add(((SalaryWorker) user).getYearlySalary());
        } else{
            list.add(-1);
        }
        return list;
    }

}
