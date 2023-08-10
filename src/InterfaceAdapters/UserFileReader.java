package InterfaceAdapters;

import java.time.LocalDate;
import java.util.*;

public class UserFileReader extends FileHandler{
    private ArrayList listHM;

    public UserFileReader(String fileName){
        super(fileName);
        ArrayList listHM = super.getStrategy().getHMList();
    }

    public ArrayList<Integer> getIds(String name){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.SURNAME);
        return (ArrayList<Integer>)hm.get(name);
    }

    public ArrayList<Integer> getIds(boolean active){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ACTIVE);
        return (ArrayList<Integer>)hm.get(active);
    }

    public String getType(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.TYPE);
    }

    public boolean getActive(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (boolean)((ArrayList)hm.get(id)).get(UserProcessorConstants.ACTIVE);
    }
    public String getRole(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.ROLE);
    }

    public String getSurname(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.SURNAME);
    }
    public String getFirstName(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.FIRST_NAME);
    }
    public LocalDate getDob(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (LocalDate) ((ArrayList)hm.get(id)).get(UserProcessorConstants.DOB);
    }
    public String getGender(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.GENDER);
    }
    public long getPhoneNumber(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (long)((ArrayList)hm.get(id)).get(UserProcessorConstants.PHONE_NUMBER);
    }
    public String getEmail(int id){
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (String)((ArrayList)hm.get(id)).get(UserProcessorConstants.EMAIL);
    }
    public char[] getPassword(int id) {
        HashMap hm = (HashMap) listHM.get(UserProcessorConstants.ID);
        return (char[]) ((ArrayList) hm.get(id)).get(UserProcessorConstants.PASSWORD);
    }


}
