package InterfaceAdapters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UseCases.*;

public class NotificationPresenter {
    public NotificationGUI gui;
    public NotificationHRListModel unresolvedList;
    public NotificationHRListModel resolvedList;
    public int user;

    public NotificationPresenter(int userID, NotificationGUI gui, NotificationHRListModel unresolvedList,
                                 NotificationHRListModel resolvedList){
        this.user = userID;
        this.unresolvedList = unresolvedList;
        this.resolvedList = resolvedList;
        this.gui = gui;
    }

}