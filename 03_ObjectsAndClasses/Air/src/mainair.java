import com.skillbox.airport.*;

import java.util.List;

public class mainair {
    //air
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List aicrafts = airport.getAllAircrafts();
        System.out.println(airport.getAllAircrafts());
        System.out.println(aicrafts.size());

    }

}