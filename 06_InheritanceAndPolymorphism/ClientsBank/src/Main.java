import typeClients.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Физлицо");
        Client client = new Individual(1000.0);
        client.putMoney(100.0);
        client.giveMoney(1200.0);
        System.out.println("Юрлицо");
        Client client2 = new Entity(1000.0);
        client2.putMoney(100.0);
        client2.giveMoney(200.0);
        System.out.println("ИП");
        Client client3 = new IndividualEntrepreneur(1000.0);
        client3.putMoney(2000.0);
        client3.giveMoney(200.0);
    }
}
