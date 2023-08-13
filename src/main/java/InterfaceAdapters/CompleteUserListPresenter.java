package InterfaceAdapters;


import UseCases.UserFileReader;
import java.util.ArrayList;

public class CompleteUserListPresenter{

    private UserFileReader fr = UserFileReader.getInstance();



    public Object[] makeUserPanel(int id){
        //Create a single panel which displays a users information, and has a button which
        //is to serve the function of activating/deactivating the employee depending on their
        //existing status.

        Object[] userPanelList = new Object[12];
        userPanelList[0] = fr.getFirstName(id);
        userPanelList[1] = fr.getSurname(id);
        userPanelList[2] = fr.getGender(id);
        userPanelList[3] = fr.getEmail(id);
        userPanelList[4] = Long.toString(fr.getPhoneNumber(id));
        userPanelList[5] = fr.getRole(id);
        userPanelList[6] = Integer.toString(id);
        userPanelList[7] = fr.getDob(id).toString();
        userPanelList[8] = fr.getType(id);
        userPanelList[9] = Float.toString(fr.getPay(id));
        if (fr.getActive(id)){
            userPanelList[10] = "Yes";
        } else{
            userPanelList[10] = "No";
        }
        if (fr.getType(id).equals("HR")){
            userPanelList[11] = 0;
        } else{
            userPanelList[11] = id;
        }
        return userPanelList;

    }

    public ArrayList<Object[]> makeUserPanels(){
        //Get the users from the file and create an array of attributes for each of them, and return them in a list.
        ArrayList<Object[]> panels = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();
        for (Integer user: users){
            panels.add(this.makeUserPanel(user));
        }
        return panels;
    }


}
