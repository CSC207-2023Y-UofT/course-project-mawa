import UseCases.*;
import FrameworksAndDrivers.*;
import InterfaceAdapters.*;
import Entities.*;

public class main {

    public static void main(String[] args){

        EmptyAppValidator eav = new EmptyAppValidator();
        if (eav.isEmpty()){
            new AddHRGUI();
        } else {
            new Login();
        }
    }
}
