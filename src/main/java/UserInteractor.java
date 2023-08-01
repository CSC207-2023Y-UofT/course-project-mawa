import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<User>{
    public ArrayList<User> readData() {


        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(this.getEmployeeList());
        userList.addAll(this.getHRList());
        return userList;
    }

    public ArrayList<Volunteer> getVolunteerList(){

        ArrayList<Volunteer> volList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream("volunteers.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            volList.addAll ((ArrayList<Volunteer>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return volList;

    }

    public ArrayList<WageWorker> getWageWorkerList(){

        ArrayList<WageWorker> wwList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream("wageworkers.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            wwList.addAll ((ArrayList<WageWorker>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return wwList;

    }

    public ArrayList<SalaryWorker> getSalaryWorkerList(){

        ArrayList<SalaryWorker> swList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream("salaryworkers.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            swList.addAll ((ArrayList<SalaryWorker>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return swList;

    }

    public ArrayList<HR> getHRList(){

        ArrayList<HR> hrList = new ArrayList<>();

        try{
            FileInputStream file = new FileInputStream("hrs.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            hrList.addAll ((ArrayList<HR>) input.readObject()) ;

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }
        return hrList;

    }



    public ArrayList<Employee> getEmployeeList(){
        ArrayList<Employee> employees = new ArrayList<>();
        employees.addAll(this.getVolunteerList());
        employees.addAll(this.getWageWorkerList());
        employees.addAll(this.getSalaryWorkerList());
        return employees;
    }

    public void writeData(User user){

        String fileName;
        ArrayList<User> userList = new ArrayList<>();

        if (user instanceof HR){
            userList.addAll(this.getHRList());
            userList.add(user);
            fileName = "hrs.ser";
        } else if (user instanceof WageWorker){
            userList.addAll(this.getWageWorkerList());
            userList.add(user);
            fileName = "wageworkers.ser";
        } else if (user instanceof SalaryWorker){
            userList.addAll(this.getSalaryWorkerList());
            userList.add(user);
            fileName = "salaryworkers.ser";
        } else{
            userList.addAll(this.getVolunteerList());
            userList.add(user);
            fileName = "volunteers.ser";
        }
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(userList);
            output.close();
        } catch (IOException e){
            System.out.println(e);
        }



    }

}

