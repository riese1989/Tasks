import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Integer code = 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date curDate = new Date();
        ArrayList <Flight> fly = new ArrayList<>();
        for (int numberAircraft = 0; numberAircraft <=50; numberAircraft++)    {
            Aircraft aircraft = new Aircraft(Airport.getInstance().getAllAircrafts().get(0).getModel());
            fly.add(new Flight(code.toString(), Flight.Type.DEPARTURE,new Date (curDate.getTime() + numberAircraft * 1000 * 420) ,aircraft));
        }
        fly.stream().filter(f -> f.getDate().getTime() - curDate.getTime() <= 2 * 1000 * 3600).forEach(f -> System.out.println(f.getAircraft() + " "+dateFormat.format(f.getDate())));
    }
}
