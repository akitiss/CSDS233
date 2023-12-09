import java.util.*;

public class AirportSystem{
    List<Vertex> connections; //adjacency list of the cities 
    private int infinity = Integer.MAX_VALUE; //creating value of infinity for easy access

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

        //return distance
        public int getDistance(){
            return distance;
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

    //returns dist between adjacent cities  
    //returns infinity if they aren't adjacent  
    private int getDistance(String cityA, String cityB){
        if (cityA.equals(cityB)){ //check if its the same city
            return 0;
        }

        for (Vertex vertex : connections){ //go through all vertex
            if (vertex.toString().equals(cityA)){  //check if vertex is cityA 
                List<Edge> edges = vertex.getEdges(); 
                for (Edge edge : edges){ //go through all edges in cityA  
                    if (edge.getDestination().equals(cityB)){ //check is cityA and cityB are adjacent 
                        return edge.getDistance(); //returns distance between the two cities  
                    }
                }
            }
        }
        return infinity; //if reaches end of loop then cityA and cityB aren't adjacent 
    }

    //returns vertex given the name
    private Vertex getVertex(String name){
        for (Vertex vertex : connections){ //loop through all connections
            if (vertex.toString().equals(name)){
                return vertex;
            }
        }
        return null; 
    }


    //Returns the shortest distance between city A and city B using Dijkstra’s algorithm
    int shortestDistance(String cityA, String cityB){
        Map<String, Integer> distances = new HashMap<>(); //map distances 
        List<String> visited = new ArrayList<>(); //record all visited 

        for (Vertex vertex : connections){ //initlize distances for all values
            distances.put(vertex.toString(), infinity);   
        }
        distances.put(cityA, 0); //overwrite cityA value with 0 

        while (!(visited.contains(cityB))){ //loop through all values until cityB is reached 
            String current = null; //current city in loop      

            int min = infinity; //find city with min value 
            for (String city : distances.keySet()) { //loop through all cities 
                if (!(visited.contains(city))){ //check if vertex has been visited 
                    int value = distances.get(city);
                    if (value < min) { //check if value is smaller
                        min = value;
                        current = city;
                    }
                }
            }

            visited.add(current); //add city with min to current

            if (current == null){ //cant reach veritices 
                break;
            }
            
            //update distances in comparison to current vertex
            List<Edge> edges = getVertex(current).getEdges(); //get all edges of vertex
            for (Edge edge : edges){  //loop through all edges 
                if (!(visited.contains(edge.getDestination()))){ //check if vertex has been visited 
                    String vertex = edge.getDestination();
                    int newDist = distances.get(current) + edge.getDistance(); //get new distance 
                    if (newDist < distances.get(vertex)){ //if new distance is less than the old distance 
                        distances.put(vertex, newDist);
                    }
                } 
            }
        }

        return distances.get(cityB);
    }

    //Uses Prim’s algorithm to create a minimum spanning tree.
    List<Edge> minimumSpanningTree(){
        List<Edge> tree = new ArrayList<>(); //min spanning tree
        List<String> visited = new ArrayList<>(); //all visisted vertex 

        visited.add(connections.get(0).toString()); //use first index as starting vertex 
        
        while (visited.size() < connections.size()){ //repeat until reach all nodes 
            
            //find min edge 
            int min = infinity;
            Edge minEdge = null;

            for (Vertex vertex : connections){ //loop through all vertex 
                if (visited.contains(vertex.toString())){ //need to find an already visited vertex to check its neighbors  
                    for (Edge edge : vertex.getEdges()){ 
                        if (!(visited.contains(edge.getDestination()))){ //if not visisted 
                            if (edge.getDistance() < min){ //if edge is smaller 
                                min = edge.getDistance();
                                minEdge = edge;
                            }
                        }
                    }
                }
            }

            if (minEdge == null){ //if no more edges 
                break;
            }

            visited.add(minEdge.getDestination()); //update visited and tree 
            tree.add(minEdge);
        }
        return tree;
    }

    //converts a List<Edge> to List<String>
    //makes it possible to compare minimumspanningtree with its expected output in the junit test since Edge is a private class 
    public List<String> listEdgeToString(List<Edge> edges){
        List<String> tree = new ArrayList<>();
        for (Edge edge : edges){ //loop through all the edges
            tree.add(edge.toString());
        }

        return tree;
    }

    //Returns a list of all the cities from the start using BFS. This is assuming this start vertex exists. 
    //Order of the cities in the same level does not matter.
    List<String> breadthFirstSearch(String start){
        List<String> output = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        Queue<String> queue = new LinkedList<>(); //use queue 

        visited.add(start);
        queue.add(start);

        while (!(queue.isEmpty())){ //while queue still has stuff 
            String current = queue.peek(); //get head 
            queue.remove(current); //remove current head 
            output.add(current); 
            
            Vertex vertex = getVertex(current);
            List<Edge> edges = vertex.getEdges();
            for (Edge edge : edges){ //gets all edges of the current vertex 
                String dest = edge.getDestination();
                if (!(visited.contains(dest))){ //if isnt visisted 
                    visited.add(dest);
                    queue.add(dest);
                }
            }

        }

        return output;
    }

    //prints out List<String> in a readbale format 
    void printListString(List<String> input){
        String output = "[";
        for (String city : input){ //loop through all the cities
            output += city + ", ";
        }
        output = output.substring(0,output.length()-2); //remove comma
        output += "]";
        System.out.println(output);
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
            graph += "V: " + vertex + " | ";
            List<Edge> edges = vertex.getEdges();
            for (Edge edge: edges){
                graph += edge;
            }
            graph += "\n";
        }

        System.out.println(graph);
    }

    public static void main(String[] args){
        AirportSystem airport = new AirportSystem(); //initilaize
        airport.connections = new ArrayList<>();

        airport.addEdge("New York", "Cleveland", 365);
        airport.addEdge("Cleveland", "Chicago", 10);
        airport.addEdge("China", "California", 20);
        airport.addEdge("China", "California", 500);
        airport.printGraph();

        //test for BFS
        airport = new AirportSystem(); //initilaize
        airport.connections = new ArrayList<>();

        airport.addEdge("Buffalo", "Cleveland", 191);
        airport.addEdge("Buffalo", "Pittsburgh", 216);
        airport.addEdge("Cleveland", "Pittsburgh", 135);
        airport.addEdge("Pittsburgh", "Columbus", 185);
        airport.addEdge("Columbus", "Cleveland", 143);
        airport.addEdge("Cleveland", "Toledo", 117);
        airport.addEdge("Toledo", "Cincinnati", 198);
        airport.addEdge("Cincinnati", "Indianapolis", 110);
        airport.addEdge("Indianapolis", "Chicago", 181);
        airport.addEdge("Chicago", "Toledo", 244);
        airport.addEdge("Chicago", "Detroit", 281);
        airport.addEdge("Detroit", "Toledo", 60);
        airport.addEdge("Columbus", "Cincinnati", 101);

        List<String> bfs = airport.breadthFirstSearch("Cincinnati");
        airport.printListString(bfs);



    } 
    
}