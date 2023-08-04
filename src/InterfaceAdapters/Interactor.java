package InterfaceAdapters;

import java.io.IOException;
import java.util.ArrayList;

public interface Interactor<T> {

  ArrayList<T> readData() throws IOException, ClassNotFoundException;

  void writeData(T obj) throws IOException;

  
}
