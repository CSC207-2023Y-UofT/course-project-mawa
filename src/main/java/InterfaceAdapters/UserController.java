package InterfaceAdapters;

import UseCases.PaymentMaker;
import UseCases.UserActivator;

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
}
