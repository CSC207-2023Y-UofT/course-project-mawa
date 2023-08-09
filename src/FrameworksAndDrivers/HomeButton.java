
import Entities.User;
import FrameworksAndDrivers.Page;
package FrameworksAndDrivers;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;

import InterfaceAdapters.*;

public class HomeButton extends JButton implements ActionListener {
    private Page currPage;
    private int userId;
    public HomeButton(Page currPage, int userId){
        this.addActionListener(this);
        this.currPage = currPage;
        this.userId = userId;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            EmployeeDataBaseInteractor empDB =  new EmployeeDataBaseInteractor();
            try{
                User user = empDB.getUser(userId);
                new EmployeeHomePage();
            }catch(InvalidKeyException ex){
                HRDatabaseInteractor hrDB = new HRDatabaseInteractor();
                User user = hrDB.getUser(userId);
                new HRHomePage();
            }
            currPage.dispose();

        }
    }
}
