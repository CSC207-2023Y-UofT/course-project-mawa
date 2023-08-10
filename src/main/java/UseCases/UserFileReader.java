package UseCases;

import Entities.User;

import java.time.LocalDate;
import java.util.*;

public class UserFileReader{
    private User user;
    private static UserFileReader instance;
    private UserInteractor interactor;
    private ArrayList<User> list;

    private UserFileReader(){
        user = new User("", "", "", "", "", -10,
                0, "9999-12-31", new char[]{"q".charAt(0)}, UserTypeConstants.SALARY_WORKER, -111);
        interactor = new UserInteractor();
        list = interactor.readData();
    }

    public static UserFileReader getInstance(){
        if (instance == null) {
            synchronized (UserFileReader.class) {
                if (instance == null) {
                    instance = new UserFileReader();
                }
            }
        }
        return instance;
    }

    private void checkUser(int id){
        if (user.getUserNum() == id){
            return;
        }
        for (User u:list){
            if(u.getUserNum() == id){
                this.user = u;
                return;
            }
        }
        System.out.println("Invalid User Number");
    }
    public int getHRId(){
        for (User u:list){
            if(u.getType().equals(UserTypeConstants.HR)){ //only one HR account
                return u.getUserNum();
            }
        }
        return -9;
    }

    public ArrayList<Integer> getIds(boolean active){
        ArrayList<Integer> ids = new ArrayList<>();
        for (User u:list){
            if (u.isActive() == active){
                ids.add(u.getUserNum());
            }
        }
        return ids;
    }

    public String getType(int id){
        checkUser(id);
        return user.getType();
    }

    public boolean getActive(int id){
        checkUser(id);
        return user.isActive();
    }
    public float getPay(int id){
        checkUser(id);
        return user.getPay();
    }

    public String getRole(int id){
        checkUser(id);
        return user.getRole();
    }

    public String getSurname(int id){
        checkUser(id);
        return user.getSurname();
    }
    public String getFirstName(int id){
        checkUser(id);
        return user.getFirstname();
    }
    public LocalDate getDob(int id){
        checkUser(id);
        return user.getDob();
    }
    public String getGender(int id){
        checkUser(id);
        return user.getGender();
    }
    public long getPhoneNumber(int id){
        checkUser(id);
        return user.getPhoneNum();
    }
    public String getEmail(int id){
        checkUser(id);
        return user.getEmail();
    }
    public char[] getPassword(int id) {
        checkUser(id);
        return user.getPassword();
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        for (User u:list){
            ids.add(u.getUserNum());
        }
        return ids;
    }


}
