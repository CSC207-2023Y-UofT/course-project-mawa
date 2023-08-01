import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<User>{
    public ArrayList<User> readData() {

        ArrayList<User> userList = new ArrayList<>();


        Object obj = null;

        boolean isExist = true;

        try{
            FileInputStream file = new FileInputStream("users.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            while(isExist){
                if(file.available() != 0){
                    obj = input.readObject();
                    userList.add((User) obj);
                }
                else{
                    isExist =false;
                }
                input.close();
            }
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

    public void writeData(User user){


        try{
            FileOutputStream file = new FileOutputStream("users.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(user);
            output.close();
        } catch (IOException e){
            System.out.println(e);
        }


    }

}

