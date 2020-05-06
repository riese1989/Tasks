package Accounts;

public class CardAccount extends CheckingAccount {

    public CardAccount(Double amount) {
        super(amount);
    }

    @Override
    public void giveMoney(Double money) {
        if (super.getAmount() >= money * 1.1) {
            super.setAmount(super.getAmount() - money * 1.1);
            System.out.println("Деньги сняты, на счету " + super.getAmount());
        }
        else
        {
            System.out.println("На счёте недостаточно средств");
        }
    }
}
