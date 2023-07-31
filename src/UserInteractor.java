import src.User;

import java.io.*;
import java.util.ArrayList;



public class UserInteractor implements Interactor<src.User>{
    public ArrayList<src.User> readData() throws  IOException, ClassNotFoundException {

        ArrayList<src.User> userList = new ArrayList<src.User>();
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

    public void writeData(src.User user) throws IOException{

        FileOutputStream file = new FileOutputStream("users.ser");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(user);
        output.close();
    }

}

