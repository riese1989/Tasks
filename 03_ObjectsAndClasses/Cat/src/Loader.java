
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat2= new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();
        Cat cat6 = cat5.copyCat();

        System.out.println(cat5.getWeight());
        System.out.println(cat6.getWeight());

    }

    private static Cat getKitten()  {
        return new Cat(1100.0);
    }
}