package UseCases;

public class EmptyAppValidator {

    private UserInteractor ui;
    public EmptyAppValidator(){
        this.ui = new UserInteractor();
    }
    public EmptyAppValidator(String isTest){
        this.ui = new UserInteractor(isTest);
    }

    public boolean isEmpty(){
        return (ui.readData().size() == 0);

    }


}
