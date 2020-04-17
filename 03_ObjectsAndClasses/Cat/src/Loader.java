
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        System.out.println(cat.getWeight());
        System.out.println(cat2.getWeight());
        System.out.println(cat3.getWeight());
        System.out.println(cat4.getWeight());
        System.out.println(cat5.getWeight());

        while (cat.getStatus() != "Exploded")   {
            cat.feed(100.0);
        }

        while (cat2.getStatus() != "Dead")   {
            cat2.meow();
        }

        System.out.println(cat.getStatus());
        System.out.println(cat2.getStatus());
    }
}