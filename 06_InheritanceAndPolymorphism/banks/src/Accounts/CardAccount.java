package Accounts;

public class CardAccount extends CheckingAccount {

    public CardAccount(Double amount) {
        super(amount);
    }

    @Override
    public void giveMoney(Double money) {
        super.giveMoney(money * 1.1);
    }
}
