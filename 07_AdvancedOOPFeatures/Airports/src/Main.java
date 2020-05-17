import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        Date curDate = new Date();
        String string = "\n==================";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        for (Terminal terminal : airport.getTerminals())    {
            System.out.println("Вылеты из " + terminal.getName() + string);
            terminal.getFlights().stream().filter(f -> f.getDate().getTime() - curDate.getTime() <= 2 * 1000 * 3600).forEach(f -> System.out.println(f.getAircraft() + " "+dateFormat.format(f.getDate())));
            System.out.println(string);
        }}


}
