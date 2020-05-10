package typeClients;

public class Entity extends Client {
    public Entity(Double checkingAccount) {
        setCheckingAccount(checkingAccount);
    }

    @Override
    public void putMoney(Double money) {
        setCheckingAccount(getCheckingAccount() + money);
        System.out.println("На счет положены " + money + " баланс " + getCheckingAccount());
    }

    @Override
    public void giveMoney(Double money) {
        if (getCheckingAccount() >= money * 1.01) {
            setCheckingAccount(getCheckingAccount() - money * 1.01);
            System.out.println("Со счета снято " + money * 1.01 + " баланс " + getCheckingAccount());
        } else {
            System.out.println("На счете недостаточно средств");
        }
    }
}
