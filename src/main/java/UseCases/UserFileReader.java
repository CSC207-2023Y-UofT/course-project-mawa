package UseCases;

import Entities.User;

import java.time.LocalDate;
import java.util.*;
/**
 * The UserFileReader class provides methods to read user records from a data source.
 */
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

    /**
     * Get the singleton instance of UserFileReader.
     *
     * @return The instance of UserFileReader.
     */
    public static UserFileReader getInstance() {
        if (instance == null) {
            instance = new UserFileReader();
        }
        return instance;
    }

    /**
     * Check if the specified user ID matches the currently loaded user record, and update if necessary.
     *
     * @param id The ID of the user.
     */
    public void checkUser(int id){
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
    /**
     * Update the list of user records from the data source.
     */
    public void update(){
        list = interactor.readData();
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
    /**
     * Get the User object for the specified user ID.
     *
     * @param id The ID of the user.
     * @return The User object.
     */
    public User getUser(int id){
        checkUser(id);
        return user;
    }


}
