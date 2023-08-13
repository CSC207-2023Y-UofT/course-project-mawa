package InterfaceAdapters;

import UseCases.UserFileReader;

import java.util.ArrayList;

public class HomePagePresenter {

    private UserFileReader ufr = UserFileReader.getInstance();

    public ArrayList<String> makeHomeButtons(int id){
        //Return a list of buttons which are displayed on the homepage, depending if the providec
        //id represents an HR or employee account.
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Schedule");
        labels.add("Notification Center");
        if (ufr.getType(id).equals("HR")){
            labels.add("Manage Employees");
        } else {
            labels.add("View Payment History");
        }
        return labels;
    }

}
