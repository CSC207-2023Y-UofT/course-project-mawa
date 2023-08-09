package InterfaceAdapters;

import UseCases.UserFactory;

public class UserFactoryInteractor {

    public void userFromInput(String surname, String firstname, String gender, String byr, String bmth, String bd,
                              long phnum, String email, String role, String type, String password, Float pay){

        UserFactory uf = new UserFactory();
        float p;
        if (type.equals("Volunteer")){
            p = 0;
        } else{
            p = pay;
        }
        uf.makeUser(surname, firstname, gender, byr, bmth,
                bd, phnum, email, role,
                type, password, p);
    }


}
