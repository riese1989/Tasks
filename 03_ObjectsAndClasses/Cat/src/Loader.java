
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Cat cat2= new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        System.out.println(Cat.getCount());

        while(cat.getStatus() != "Dead")    {
            cat.meow();
        }
        System.out.println(getKitten().getWeight());
    }

    private static Cat getKitten()  {
        return new Cat(1100.0);
    }
}