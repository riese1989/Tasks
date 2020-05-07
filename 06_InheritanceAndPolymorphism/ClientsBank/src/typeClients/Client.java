package typeClients;

abstract public class Client {

    private Double checkingAccount;

    public Double getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(Double checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public abstract void putMoney(Double money);
    public abstract void giveMoney (Double money);


}
