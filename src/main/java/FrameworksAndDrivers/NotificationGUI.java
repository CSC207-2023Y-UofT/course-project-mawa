package FrameworksAndDrivers;

import InterfaceAdapters.NotificationMiddleManPresenter;

public class NotificationGUI {
    public NotificationGUI(int userID){
        NotificationMiddleManPresenter presenter = new NotificationMiddleManPresenter(userID);
        if (presenter.isHR()){
            new NotificationHRGUI(userID);
        }else{
            new NotificationEmployeeGUI(userID);
        }

    }
}
