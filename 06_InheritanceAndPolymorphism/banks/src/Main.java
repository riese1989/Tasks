import Accounts.*;

public class Main {
    public static void main(String[] args) {
        checkingAccount account = new checkingAccount(2000.0);
        account.putMoney(600.0);
        account.giveMoney(100.0);
        System.out.println(account.getAmount());
        cardAccount card = new cardAccount(1000.0);
        card.giveMoney(1000.0);
        depozit dep = new depozit(2000.0);
        dep.giveMoney(100.0);
        dep.giveMoney(100.0);

    }
}
