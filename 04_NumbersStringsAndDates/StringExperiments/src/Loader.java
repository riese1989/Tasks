import java.util.jar.JarOutputStream;

public class Loader
{
    public static void main(String[] args)
    {
        int sum = 0;
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] casheText = text.replaceAll("[^0-9]"," ").trim().split("\\s+");
        for (int i = 0; i < casheText.length; i++)  {
            sum += Integer.parseInt(casheText[i]);
        }
        System.out.println(sum);
    }
}