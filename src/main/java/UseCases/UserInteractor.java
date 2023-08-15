package UseCases;

import Entities.User;

import java.io.*;
import java.util.ArrayList;

/**
 * The UserInteractor class manages reading, writing, and updating user data.
 * It implements the Interactor interface for user objects.
 */
public class UserInteractor implements Interactor<User> {

    private String fileName; // Stores the name of the user data file.

    /**
     * Default constructor that initializes the UserInteractor with the default user data file name.
     */
    public UserInteractor() {
        this.fileName = FileNameConstants.USER_FILE_NAME;
    }

    /**
     * Constructor that specifies the file name to be that of testing data.
     *
     * @param isTest A string identifier indicating the use of a test user data file.
     */
    public UserInteractor(String isTest) {
        this.fileName = "testUsers.ser";
    }

    /**
     * Reads user data from the user data file.
     *
     * @return An ArrayList of User objects read from the file.
     */
    public ArrayList<User> readData() {
        ArrayList<User> userList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            userList.addAll((ArrayList<User>) input.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return userList;
    }

    /**
     * Updates an existing user in the user data file.
     *
     * @param u The updated User object.
     */
    @Override
    public void update(User u) {
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

    /**
     * Writes a new user object to the user data file.
     *
     * @param user The User object to be written to the file.
     */
    public void writeData(User user) {
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
