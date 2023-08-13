import UseCases.*;
import FrameworksAndDrivers.*;

public class Main {

    public static void main(String[] args){

        EmptyAppValidator eav = new EmptyAppValidator();
        if (eav.isEmpty()){
            new AddHRGUI();
        } else {
            new Login();
        }
    }
}
