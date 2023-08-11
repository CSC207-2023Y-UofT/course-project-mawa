package UseCases;

import Entities.Shift;
import Entities.User;
import Entities.UserNotification;

import javax.management.Notification;
import javax.swing.*;
import java.util.ArrayList;

public class ShiftViewHRModel{
    int notificationID;
    DefaultListModel<String> employeesOnShift = new DefaultListModel<>();
    DefaultListModel<String> employeesNotOnShift = new DefaultListModel<>();
    Shift shift;
    ArrayList<User> usersOnShift = new ArrayList<User>();
    ArrayList<User> usersNotOnShift = new ArrayList<User>();
    String[] usersOnShiftString;
    String[] usersNotOnShiftString;
    int userId;


    public ShiftViewHRModel(int notificationId, int userID){
        notificationID = notificationId;
        userId = userID;
        getShiftFromNotificationId();
        getUsers();
        usersToString();
        populateUsersLists();
    }



    public DefaultListModel<String> getEmployeesOnShiftList(){
        return this.employeesOnShift;
    }
    public DefaultListModel<String> getEmployeesNotOnShiftList(){
        return this.employeesNotOnShift;
    }

    public JLabel getShiftDateLabel(){
        String date = "Date: " + shift.getTime().getDayOfWeek() + ", " +shift.getTime().getMonth().toString() + ", " + shift.getTime().getYear();
        return new JLabel(date);
    }

    public JLabel getShiftTimeLabel(){
        float time = shift.getTime().getHour()+shift.getDuration();
        String date = "Shift Time: " + shift.getTime().getHour() + " to " + time;
        return new JLabel(date);
    }

    public void getShiftFromNotificationId(){
        NotificationFileReader notif = NotificationFileReader.getInstance();
        ShiftFileReader sr = ShiftFileReader.getInstance();
        int id = notif.getUserNotification(notificationID).getShiftId();
        this.shift = sr.getShift(id);
    }

    public void getUsers(){
        UserFileReader ur = UserFileReader.getInstance();
        UserInteractor ui = new UserInteractor();
        for(int i: shift.getCoworkers()){
            usersOnShift.add(ur.getUser(i));
        }
        for (User u: ui.readData()){
            boolean equals = false;
            for(User b: usersOnShift){
                if (u.getUserNum() == b.getUserNum()){
                    equals = true;
                }
            }
            if(!equals){
                usersNotOnShift.add(u);
            }
        }
    }

    public void usersToString(){
        usersOnShiftString = new String[usersOnShift.size()];
        for(int i = 0; i < usersOnShift.size(); i++){
            String item =  usersOnShift.get(i).getFirstname()+ " " + usersOnShift.get(i).getSurname() + " (" +usersOnShift.get(i).getRole() + ")";
            usersOnShiftString[i] = item;
        }
        usersNotOnShiftString = new String[usersNotOnShift.size()];
        for(int i = 0; i < usersNotOnShift.size(); i++){
            String item =  usersNotOnShift.get(i).getFirstname()+ " " + usersNotOnShift.get(i).getSurname() + " (" +usersNotOnShift.get(i).getRole() + ")";
            usersNotOnShiftString[i] = item;
        }
    }
    public void populateUsersLists(){
        for (String u: usersOnShiftString){
            employeesOnShift.addElement(u);
        }
        for(String b: usersNotOnShiftString){
            employeesNotOnShift.addElement(b);
        }
    }

    public void updateShiftandNotification(){
        NotificationFileReader nfr = NotificationFileReader.getInstance();
        UserNotification notif = nfr.getUserNotification(notificationID);
        ArrayList<Integer> userID = new ArrayList<>();
        for(int i = 0; i < usersOnShiftString.length; i++){
            if(employeesOnShift.contains(usersOnShiftString[i])){
                userID.add(usersOnShift.get(i).getUserNum());
            }else{
                if(usersOnShift.get(i).getUserNum() == notif.getSenderId()){
                    notif.resolve();
                    UserNotificationInteractor uni = new UserNotificationInteractor();
                    uni.update(notif);
                }
            }
        }
        for(int i = 0; i < usersNotOnShiftString.length; i++){
            if(employeesOnShift.contains(usersNotOnShiftString[i])){
                userID.add(usersNotOnShift.get(i).getUserNum());
            }
        }
        shift.setCoworkers(userID);
        ShiftInteractor si = new ShiftInteractor();
        si.update(shift);
    }
}
