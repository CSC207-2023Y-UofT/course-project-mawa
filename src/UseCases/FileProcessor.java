package UseCases;


import java.util.ArrayList;

public interface FileProcessor<T> {

    public void makeHM();

    public ArrayList<Object> toList(T entity);

}
