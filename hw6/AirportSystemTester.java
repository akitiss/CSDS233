import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class AirportSystemTester{
    @Test
     
    public void shortestDistanceTester(){
        //test 1 
        AirportSystem airport = new AirportSystem(); //initilaize
        airport.connections = new ArrayList<>();

        airport.addEdge("New York", "Cleveland", 300);
        airport.addEdge("China", "Cleveland", 200);
        airport.addEdge("Cleveland", "Chicago", 10);
        airport.addEdge("Chicago", "California", 5);
        airport.addEdge("China", "California", 20);
        airport.printGraph();

        int output = airport.shortestDistance("New York", "Cleveland");
        assertEquals(300, output);
        output = airport.shortestDistance("China", "Cleveland");
        assertEquals(35, output);

        //test 2
        airport = new AirportSystem(); //initilaize
        airport.connections = new ArrayList<>();

        airport.addEdge("Buffalo", "Cleveland", 191);
        airport.addEdge("Buffalo", "Pittsburgh", 216);
        airport.addEdge("Cleveland", "Pittsburgh", 135);
        airport.addEdge("Pittsburgh", "Columbus", 185);
        airport.addEdge("Columbus", "Cleveland", 143);

        output = airport.shortestDistance("Buffalo", "Columbus");
        assertEquals(191+143, output);
        output = airport.shortestDistance("Buffalo", "Buffalo");
        assertEquals(0, output);

        //test 3
        airport = new AirportSystem(); //initilaize
        airport.connections = new ArrayList<>();

        airport.addEdge("Cleveland", "Toledo", 117);
        airport.addEdge("Toledo", "Cincinnati", 198);
        airport.addEdge("Cincinnati", "Indianapolis", 110);
        airport.addEdge("Indianapolis", "Chicago", 181);
        airport.addEdge("Chicago", "Toledo", 244);
        airport.addEdge("Chicago", "Detroit", 281);
        airport.addEdge("Detroit", "Toledo", 60);
        airport.addEdge("Columbus", "Cincinnati", 101);
        airport.addEdge("Columbus", "Cleveland", 143);

        output = airport.shortestDistance("Toledo", "Chicago");
        assertEquals(244, output);
        output = airport.shortestDistance("Cleveland", "Indianapolis");
        assertEquals(143+101+110, output);

    }
}