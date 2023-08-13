package UseCases;


import Entities.User;

import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<User> {
    private String fileName;
    public UserInteractor(){this.fileName = FileNameConstants.USER_FILE_NAME;}
    public UserInteractor(String isTest){this.fileName = "testUsers.ser";}

    public ArrayList<User> readData() {


        ArrayList<User> userList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            userList.addAll ((ArrayList<User>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return userList;
    }




    public void update(User u){
        ArrayList<User> users = this.readData();
        users.removeIf(user -> u.getUserNum() == user.getUserNum());
        users.add(u);
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(users);
            output.close();
            UserFileReader.getInstance().update();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeData(User user){

        ArrayList<User> userList = this.readData();
        userList.add(user);
            try {
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream output = new ObjectOutputStream(file);
                output.writeObject(userList);
                output.close();
                UserFileReader.getInstance().update();
            } catch (IOException e) {
                System.out.println(e);
            }

    }

}

