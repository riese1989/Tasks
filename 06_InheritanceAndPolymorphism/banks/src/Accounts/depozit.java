package Accounts;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class depozit extends checkingAccount{
    private Calendar dateLastTransaction = new GregorianCalendar(2020,Calendar.APRIL,5);

    public depozit(Double amount) {
        super(amount);
    }

    @Override
    public void giveMoney(Double money) {
        Calendar currDate = Calendar.getInstance();
        if (diff(currDate))   {
            if (super.getAmount() >= money) {
                super.setAmount(super.getAmount() - money);
                dateLastTransaction = currDate;
                System.out.println("Деньги сняты, на счету " + super.getAmount());
            }
        }
        else {
            System.out.println("С момента последнего снятия не прошёл месяц");
        }
    }

    private boolean diff (Calendar currentDate) {
        if ((int) ((currentDate.getTimeInMillis() - dateLastTransaction.getTimeInMillis())/(24 * 60 * 60 * 1000)) > 30) {
            return true;
        }
        return false;
    }
}
