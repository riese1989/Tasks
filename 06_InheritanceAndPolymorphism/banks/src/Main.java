import Accounts.*;

public class Main {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount(2000.0);
        account.putMoney(600.0);
        account.giveMoney(100.0);
        System.out.println(account.getAmount());
        CardAccount card = new CardAccount(1000.0);
        card.giveMoney(1000.0);
        Depozit dep = new Depozit(2000.0);
        dep.giveMoney(100.0);
        dep.giveMoney(100.0);

    }
}
