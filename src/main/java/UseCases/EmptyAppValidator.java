package UseCases;

public class EmptyAppValidator {

    public UserInteractor ui = new UserInteractor();

    public boolean isEmpty(){
        return (ui.readData().size() == 0);

    }


}
