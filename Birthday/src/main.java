import java.text.SimpleDateFormat;
import java.util.*;
import java.util.GregorianCalendar;

public class main {
    public static void main(String[] args) {
        Locale loc = Locale.ENGLISH;
        Calendar calendar = new GregorianCalendar(1989,Calendar.DECEMBER,7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY - E",loc);
        for (int age = 0; calendar.get(calendar.YEAR) < 2020; age++, calendar.add(Calendar.YEAR,1)) {
            System.out.println(age + " " + dateFormat.format(calendar.getTime()));
        }
    }
}
