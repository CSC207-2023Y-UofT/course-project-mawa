package InterfaceAdapters;

import UseCases.PaymentMaker;
import UseCases.UserActivator;
import UseCases.UserFactory;

/**
 * The UserController class acts as an intermediary between the user interface and the underlying use cases.
 * It handles actions related to user management and payments.
 */
public class UserController {

    /**
     * Changes the activation status of a user.
     *
     * @param IDnum The ID of the user for whom to change the activation status.
     */
    public void changeActivation(int IDnum) {
        UserActivator ua = new UserActivator();
        ua.changeActivation(IDnum);
    }

    /**
     * Initiates the process of making a payment to an employee.
     *
     * @param id The ID of the employee for whom to make the payment.
     */
    public void makePayment(int id) {
        PaymentMaker p = new PaymentMaker(id);
        p.makePayment();
    }

    /**
     * Creates a new user based on input data.
     *
     * @param surname     The surname of the user.
     * @param firstname   The first name of the user.
     * @param gender      The gender of the user.
     * @param byr         The birth year of the user.
     * @param bmth        The birth month of the user.
     * @param bd          The birthdate of the user.
     * @param phnum       The phone number of the user.
     * @param email       The email of the user.
     * @param role        The role of the user.
     * @param type        The type of employee ("Volunteer", "Wage Worker", "Salary Worker").
     * @param password    The password of the user.
     * @param pay         The payment amount (hourly wage or yearly salary).
     */
    public void userFromInput(String surname, String firstname, String gender, String byr, String bmth, String bd,
                              long phnum, String email, String role, String type, String password, Float pay) {

        UserFactory uf = new UserFactory();
        float p;
        if (type.equals("Volunteer")) {
            p = 0;
        } else {
            p = pay;
        }
        uf.makeUser(surname, firstname, gender, byr, bmth, bd, phnum, email, role, type, password, p);
    }
}
