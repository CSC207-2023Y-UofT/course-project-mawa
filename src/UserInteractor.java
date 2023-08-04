import Entities.Employee;
import Entities.User;

import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<User>{
    public ArrayList<User> readData() {


        ArrayList<User> userList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream("users.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            userList.addAll ((ArrayList<User>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return userList;
    }


    public ArrayList<Employee> getEmployeeList(){
        ArrayList<User> users = this.readData();
        ArrayList<Employee> employees = new ArrayList<>();
        for (User user: users){
            if (user instanceof Employee){
                employees.add((Employee)user);
            }
        }
        return employees;
    }

    public void update(User u){
        ArrayList<User> users = this.readData();
        users.removeIf(user -> u.getUserNum() == user.getUserNum());
        users.add(u);
        try {
            FileOutputStream file = new FileOutputStream("users.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeData(User user){

        ArrayList<User> userList = this.readData();
        userList.add(user);
            try {
                FileOutputStream file = new FileOutputStream("users.ser");
                ObjectOutputStream output = new ObjectOutputStream(file);
                output.writeObject(userList);
                output.close();
            } catch (IOException e) {
                System.out.println(e);
            }




    }

}

