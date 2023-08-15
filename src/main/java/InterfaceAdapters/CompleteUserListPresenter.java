package InterfaceAdapters;

import UseCases.UserFileReader;
import java.util.ArrayList;

/**
 * The CompleteUserListPresenter class handles presenting user data for display.
 * It creates arrays of user information and lists of arrays for UI rendering.
 * This class is part of the MVP design pattern.
 */
public class CompleteUserListPresenter {

    private UserFileReader fr = UserFileReader.getInstance();

    /**
     * Creates an array of user information for display.
     *
     * @param id The ID of the user for whom to create the array.
     * @return An array of objects representing user information for display.
     */
    public Object[] makeUserPanel(int id) {
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

        if (fr.getActive(id)) {
            userPanelList[10] = "Yes";
        } else {
            userPanelList[10] = "No";
        }

        if (fr.getType(id).equals("HR")) {
            userPanelList[11] = 0;
        } else {
            userPanelList[11] = id;
        }

        return userPanelList;
    }

    /**
     * Creates arrays of user information for multiple users.
     *
     * @return An ArrayList of arrays of objects, each representing user information for display.
     */
    public ArrayList<Object[]> makeUserPanels() {
        ArrayList<Object[]> panels = new ArrayList<>();
        ArrayList<Integer> users = fr.getIds();

        for (Integer user : users) {
            panels.add(this.makeUserPanel(user));
        }

        return panels;
    }
}
