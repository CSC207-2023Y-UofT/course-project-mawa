
import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<User>{
    public ArrayList<User> readData() throws  IOException, ClassNotFoundException {

        ArrayList<User> userList = new ArrayList<User>();
        FileInputStream file = new FileInputStream("users.ser");
        ObjectInputStream input = new ObjectInputStream(file);
        try {
            while (true){
                userList.add((User) input.readObject());
            }
        } catch (OptionalDataException e){
            if (!e.eof){
                throw e;
            }
        } finally {
            input.close();
        }

        return userList;
    }

    public void writeData(User user) throws IOException{

        FileOutputStream file = new FileOutputStream("users.ser");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(user);
        output.close();
    }

}

