import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class AirportSystem{
    List<Vertex> connections; //adjacency list of the cities 

    //private nested class 
    private class Edge{
        private String source; //starting location
        private String destination; //end destination 
        private int distance; //distance between start and destination 

        //constructor 
        public Edge(String source, String destination, int distance){
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }

        //represent edge with its start and end 
        //overrides regular toString 
        public String toString(){
            return ("[" + source + ", " + destination + "]");
        }

    }
    
    //private nested class
    private class Vertex{
        String id; //city name 
        List<Edge> edges; //connected cities 
        
        //constructor 
        public Vertex(String id, List<Edge> edges){
            this.id = id;
            this.edges = edges;
        }

        //represent vertex with id
        //overrides regular toString
        public String toString(){
            return id;
        }
    }

    //Adds a new edge to the connections list. Return false if the edge already exists or the weight is negative.
    boolean addEdge(String source, String destination, int weight){
        return false;
    }

    //Returns the shortest distance between city A and city B using Dijkstra’s algorithm
    int shortestDistance(String cityA, String cityB){
        return 0;
    }

    //Uses Prim’s algorithm to create a minimum spanning tree.
    List<Edge> minimumSpanningTree(){
        return null;
    }

    //Returns a list of all the cities from the start using BFS. This is assuming this start vertex exists. 
    //Order of the cities in the same level does not matter.
    List<String> breadthFirstSearch(String start){
        return null;
    }

    //Prints the graph in a readable format and it is clear which edge belongs to which vertex.
    /*Example:
        V: A | E: [A,B][A,D]
        V: B | E: [B,A][B,C]
        V: C | E: [C,B]
        V: D | E: [D,A]
    */
    void printGraph(){

    }
    
}