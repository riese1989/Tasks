package Accounts;

public class CheckingAccount {
    private Double amount;

    public CheckingAccount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
    protected void setAmount(Double amount)    {
        this.amount = amount;
    }

    public void putMoney (Double money)   {
        amount += money;
        System.out.println("Счет по пополнен на " + money + ", на счету " + amount);
    }

    public void giveMoney (Double money)    {
        if (amount >= money)    {
           amount -= money;
            System.out.println("Деньги сняты, на счету " + amount);
        }
        else    {
            System.out.println("На счету недостаточно средств");
        }
    }
}
