package UseCases;
import Entities.Payment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;


public class PaymentMaker {

    private ArrayList<Integer> userList;

    private UserFileReader ui;

    private ShiftFileReader si;

    private PaymentFileReader pi;
    private PaymentInteractor interactor;

    private int numberOfPayments;
    private LocalDateTime currentDate = LocalDateTime.now();
    private ArrayList<Integer> salary_worker;
    private ArrayList<Integer> wage_worker;


    public PaymentMaker() {
        si = ShiftFileReader.getInstance();
        pi = PaymentFileReader.getInstance();
        ui = UserFileReader.getInstance();
        interactor = new PaymentInteractor();
        userList = ui.getIds();
        numberOfPayments = pi.getIds().size();
        salary_worker = new ArrayList<>();
        wage_worker = new ArrayList<>();
    }

    public boolean isDoublePayment(){
        int mostRecentPayment = Collections.max(pi.getIds());
        return pi.getDate(mostRecentPayment).toLocalDate().isEqual(currentDate.toLocalDate()) ||
                pi.getDate(mostRecentPayment).toLocalDate().isAfter(currentDate.toLocalDate());
    }

    public void makePayment() {

        if (!isDoublePayment()) {
            for (Integer i : userList) {
                if (ui.getRole(i).equals(UserTypeConstants.SALARY_WORKER)) {
                    salary_worker.add(i);
                } else if (ui.getRole(i).equals(UserTypeConstants.WAGE_WORKER)) {
                    wage_worker.add(i);
                }
            }
            LocalDate lastFri = LocalDate.from(currentDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
            if (LocalDate.from(currentDate).isEqual(lastFri)) {
                for (int sw : salary_worker) {
                    float pay_amount = ui.getPay(sw) / 12;
                    interactor.writeData(new Payment(sw, pay_amount, currentDate, numberOfPayments + 1));
                    numberOfPayments += 1;
                }
                for (int ww : wage_worker) {
                    float pay_amount = wageWorkerMonthlyHours(si.getIds(ww)) * ui.getPay(ww);
                    interactor.writeData(new Payment(ww, pay_amount, currentDate, numberOfPayments + 1));
                    numberOfPayments += 1;
                }
            }
        }
    }

    public float wageWorkerMonthlyHours(ArrayList<Integer> shifts) {
        float hours = 0;
        for (int s : shifts) {
            if (si.getDate(s).getMonth().equals(currentDate.getMonth()) &&
                    si.getDate(s).getYear() == currentDate.getYear()) {
                hours += si.getDuration(s);
            }
        }
        return hours;
    }
}
