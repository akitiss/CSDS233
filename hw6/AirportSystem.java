import java.util.List;
import java.util.ArrayList;

public class AirportSystem{
    private List<Vertex> connections; //adjacency list of the cities 

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

        //return source
        public String getSource(){
            return source;
        }

        //return destination
        public String getDestination(){
            return destination;
        }

    }
    
    //private nested class
    private class Vertex{
        private String id; //city name 
        private List<Edge> edges; //connected cities 
        
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

        //returns list of edges 
        public List<Edge> getEdges(){
            return edges;
        }

        //adds edge to vertex given that edge doesn't already exist
        public void updateEdges(Edge edge){
            edges.add(edge);
        }
    }

    //Adds a new edge to the connections list. Return false if the edge already exists or the weight is negative.
    boolean addEdge(String source, String destination, int weight){
        if (weight < 0){ //return false if weight is negative
            return false;
        }

        //will check to see if source and destination already have a vertex
        //if there is assign that vertex to the var, if not leave null 
        Vertex hasSource = null;
        Vertex hasDestination = null;

        //check is edge already exists 
        for (Vertex vertex : connections){
            if (source.equals(vertex.toString())){ //check is source is the same
                hasSource = vertex;  
                List<Edge> edges = vertex.getEdges(); //get connected cities 
                for (Edge edge : edges){
                    if (destination.equals(edge.getDestination())){ //check if desination is the same 
                        return false; //connection exists return false
                    }
                }
            }
            if (destination.equals(vertex.toString())){ //checks if desination exists 
                hasDestination = vertex;
            }
        }

        //else 

        //edge pointing both ways since should be a two way 
        Edge edge1 = new Edge(source, destination, weight);
        Edge edge2 = new Edge(destination, source, weight);

        //check if vertex already exists so you need to create a new vertex or add to the vertex 
        if (hasSource != null){ //if vertex exists 
            hasSource.updateEdges(edge1);
        } else { //if vertex doesn't exist 
            List<Edge> e = new ArrayList<>(); //create list with that one edge 
            e.add(edge1);
            Vertex newVertex = new Vertex(source, e); //create new vertex 
            connections.add(newVertex);
        }
        
        //do same for desination 
        if (hasDestination != null){
            hasDestination.updateEdges(edge2);
        } else {
            List<Edge> e = new ArrayList<>();
            e.add(edge2);
            Vertex newVertex = new Vertex(destination, e);
            connections.add(newVertex);
        }

        //need to check both becuase desination and start might exist but aren't connected

        return true;
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
        String graph = "";

        for (Vertex vertex : connections){
            graph += "V" + vertex;

        }
    }

    public static void main(String[] args){
        AirportSystem airport = new AirportSystem(); //initilaize 
        airport.addEdge("New York", "Cleveland", 365);
        airport.printGraph();

    } 
    
}