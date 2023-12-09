import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class AirportSystemTester{
    @Test
     
    public void shortestDistanceTester(){
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
    }
}