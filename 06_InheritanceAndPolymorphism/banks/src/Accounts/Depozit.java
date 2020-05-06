package Accounts;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Depozit extends CheckingAccount {
    private Calendar dateLastTransaction = new GregorianCalendar(2020, Calendar.APRIL, 5);

    public Depozit(Double amount) {
        super(amount);
    }

    @Override
    public void giveMoney(Double money) {
        Calendar currDate = Calendar.getInstance();
        if (diff(currDate)) {
            dateLastTransaction = currDate;
            super.giveMoney(money);
        } else {
            System.out.println("С момента последнего снятия не прошёл месяц");
        }
    }

    private boolean diff(Calendar currentDate) {
        if ((int) ((currentDate.getTimeInMillis() - dateLastTransaction.getTimeInMillis()) / (24 * 60 * 60 * 1000)) > 30) {
            return true;
        }
        return false;
    }
}
