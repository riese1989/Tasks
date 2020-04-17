
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        cat.feed(150.0);
        for (int i = 0; i <= 10; i++)   {
            cat.pee();
        }
        System.out.println(cat.getAllAmountEat());
    }
}