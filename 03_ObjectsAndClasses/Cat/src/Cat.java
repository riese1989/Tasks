
public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double allAmountEat = 0;
    private static int count = 0;

    private static final int COUNT_EYES = 2;
    private static final double MAX_WEIGTH = 10000;
    private static final double MIN_WEIGTH = 1;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;

    }

    public Cat (double weight)  {
        this.weight = weight;
    }

    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        if (getStatus() != "Dead" || getStatus() != "Exploded") {
            weight = weight + amount;
            allAmountEat += amount;
        }
    }

    public double getAllAmountEat() {
        return allAmountEat;
    }

    public void drink(Double amount) {
        if (getStatus() != "Dead" || getStatus() != "Exploded")
            weight = weight + amount;
    }

    public void pee() {
        if (getStatus() != "Dead" || getStatus() != "Exploded") {
            weight -= 1;
            System.out.println("Сходила в туалет");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public static int getCount() {
        return count;
    }

    public String getStatus() {
        if (weight < minWeight) {
            count--;
            return "Dead";
        } else if (weight > maxWeight) {
            count--;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}