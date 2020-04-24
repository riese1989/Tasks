public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(sumDigits(29095));
        Character ch = '1';
        int i = Integer.parseInt(ch.toString());
        System.out.println(i);
    }

    public static Integer sumDigits(Integer number)
    {
        //@TODO: write code here
        int sum = 0;
        String str = number.toString();
        for (int i = 0; i < str.length(); i++)  {
            sum += Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return sum;
    }
}
