package typeClients;

public class Individual extends Client {

    public Individual(Double checkingAccount) {
        setCheckingAccount(checkingAccount);
    }

    @Override
    public void putMoney(Double money) {
        setCheckingAccount(getCheckingAccount() + money);
        System.out.println("На счет положены " + money + " баланс " + getCheckingAccount());
    }

    @Override
    public void giveMoney(Double money) {
        if (getCheckingAccount() >= money) {
            setCheckingAccount(getCheckingAccount() - money);
            System.out.println("Со счета снято " + money + " баланс " + getCheckingAccount());
        } else {
            System.out.println("На счету недостаточно средств");
        }
    }
}

