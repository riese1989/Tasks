package typeClients;

public class IndividualEntrepreneur extends Client {
    public IndividualEntrepreneur(Double checkingAccount) {
        setCheckingAccount(checkingAccount);
    }

    @Override
    public void putMoney(Double money) {
        double comission;
        if (money >= 1000)  {
            comission = 0.005;
        }
        else    {
            comission = 0.01;
        }
        setCheckingAccount(getCheckingAccount() + money * (1 - comission));
        System.out.println("На счет положены " + money * (1 - comission) + " баланс " + getCheckingAccount());
    }

    @Override
    public void giveMoney(Double money) {
        if (getCheckingAccount() >= money) {
            setCheckingAccount(getCheckingAccount() - money);
            System.out.println("Со счета снято " + money + " баланс " + getCheckingAccount());
        } else {
            System.out.println("На счете недостаточно средств");
        }
    }
}
