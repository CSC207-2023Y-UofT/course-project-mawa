package FrameworksAndDrivers;

import InterfaceAdapters.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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
            new HomePage(userId);
            currPage.dispose();

        }
    }
}
