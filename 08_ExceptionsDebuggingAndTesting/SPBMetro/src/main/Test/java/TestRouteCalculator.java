import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRouteCalculator extends TestCase {

    private static StationIndex stationIndex;
    List<Station> routeOneLine;
    List<Station> routeTwoLine;
    List<Station> routeThreeLine;

    Station fromOne;
    Station toOne;


    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Red");
        Line line2 = new Line(2, "Blue");
        Line line3 = new Line(3, "Green");

        Station station1 = new Station("Чернышевская", line1);
        Station station2 = new Station("Площадь Восстания", line1);
        Station station3 = new Station("Владимирская", line1);
        Station station4 = new Station("Пушкинская", line1);
        Station station5 = new Station("Технологический институт", line1);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
        line1.addStation(station5);

        Station station6 = new Station("Горьковская", line2);
        Station station7 = new Station("Невский проспект", line2);
        Station station8 = new Station("Сенная площадь", line2);
        Station station9 = new Station("Технологический институт", line2);

        line2.addStation(station6);
        line2.addStation(station7);
        line2.addStation(station8);
        line2.addStation(station9);

        Station station10 = new Station("Гостиный двор", line3);
        Station station11 = new Station("Маяковская", line3);

        line3.addStation(station10);
        line3.addStation(station11);

        ArrayList<Station> connect1 = new ArrayList<>();
        connect1.add(station5);
        connect1.add(station9);

        ArrayList<Station> connect2 = new ArrayList<>();
        connect2.add(station7);
        connect2.add(station10);

        ArrayList<Station> connect3 = new ArrayList<>();
        connect3.add(station2);
        connect3.add(station11);

        stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        stationIndex.addStation(station10);
        stationIndex.addStation(station11);
        stationIndex.addConnection(connect1);
        stationIndex.addConnection(connect2);
        stationIndex.addConnection(connect3);

        fromOne = station1;
        toOne = station6;


        routeOneLine = new ArrayList<>();
        routeOneLine.add(station1);
        routeOneLine.add(station2);
        routeOneLine.add(station3);
        routeOneLine.add(station4);

        routeTwoLine = new ArrayList<>();
        routeTwoLine.add(station1);
        routeTwoLine.add(station2);
        routeTwoLine.add(station11);
        routeTwoLine.add(station10);

        routeThreeLine = new ArrayList<>();
        routeThreeLine.add(station1);
        routeThreeLine.add(station2);
        routeThreeLine.add(station3);
        routeThreeLine.add(station4);
        routeThreeLine.add(station5);
        routeThreeLine.add(station9);
        routeThreeLine.add(station8);
        routeThreeLine.add(station7);
        routeThreeLine.add(station6);
    }

    public void testCalcDur()
    {
        double actual = RouteCalculator.calculateDuration(routeOneLine);
        double expected = 7.5;
        assertEquals(expected,actual);
    }
    public void testSortestRoute ()   {
        RouteCalculator rt = new RouteCalculator(stationIndex);
        List <Station> expected = routeThreeLine;
        List <Station> actual = rt.getShortestRoute(stationIndex.getStation(fromOne.getName()),
                                                    stationIndex.getStation(toOne.getName()));
        assertEquals(expected, actual);

    }




    @Override
    protected void tearDown() throws Exception {
    }
}
