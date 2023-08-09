package UseCases;


import java.util.ArrayList;
import java.util.HashMap;

public interface FileProcessor<T> {

    public void makeHM();
    public ArrayList<HashMap> getHMList();

    public ArrayList<Object> toList(T entity);
    public Interactor getInteractor();

}
