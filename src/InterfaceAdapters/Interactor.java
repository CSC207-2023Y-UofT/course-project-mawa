package InterfaceAdapters;

import java.io.IOException;
import java.util.ArrayList;

public interface Interactor<T> {



  //This method returns an ArrayList of T.
  ArrayList<T> readData() throws IOException, ClassNotFoundException;

  //This method takes in an entity (user/shift/notification/payment), and updates its respective
  //.ser file (which contains a list of the particular entity) to contain that instance.
  void writeData(T obj) throws IOException;

  //This method behaves similar to writeData, except that it has the precondition that obj is
  //already in the list stored in its respective .ser file.
  void update(T obj) throws IOException;

  
}
