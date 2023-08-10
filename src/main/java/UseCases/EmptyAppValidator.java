package UseCases;

public class EmptyAppValidator {

    private UserInteractor ui = new UserInteractor();

    public boolean isEmpty(){
        return (ui.readData().size() == 0);

    }


}
