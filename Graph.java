package smartcity;

// Graph.java
import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;
    
    public Graph() {
        adjacencyList = new HashMap<>();
    }
    
    // Add location to graph
    public boolean addVertex(String location) {
        if (adjacencyList.containsKey(location)) {
            return false; // Location already exists
        }
        adjacencyList.put(location, new ArrayList<>());
        return true;
    }
    
    // Remove location from graph
    public boolean removeVertex(String location) {
        if (!adjacencyList.containsKey(location)) {
            return false; // Location doesn't exist
        }
        
        // Remove all roads connected to this location
        List<String> edges = adjacencyList.get(location);
        for (String neighbor : edges) {
            adjacencyList.get(neighbor).remove(location);
        }
        
        // Remove the location itself
        adjacencyList.remove(location);
        return true;
    }
    
    // Add road between two locations
    public boolean addEdge(String location1, String location2) {
        if (!adjacencyList.containsKey(location1) || !adjacencyList.containsKey(location2)) {
            return false; // One or both locations don't exist
        }
        
        // Add connection in both directions
        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);
        return true;
    }
    
    // Remove road between two locations
    public boolean removeEdge(String location1, String location2) {
        if (!adjacencyList.containsKey(location1) || !adjacencyList.containsKey(location2)) {
            return false;
        }
        
        boolean removed1 = adjacencyList.get(location1).remove(location2);
        boolean removed2 = adjacencyList.get(location2).remove(location1);
        
        return removed1 && removed2;
    }
    
    // Display all connections using BFS with Queue
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No locations in the graph.");
            return;
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        System.out.println("City Transport Network:");
        System.out.println("======================");
        
        for (String location : adjacencyList.keySet()) {
            if (!visited.contains(location)) {
                queue.add(location);
                visited.add(location);
                
                while (!queue.isEmpty()) {
                    String current = queue.poll();
                    System.out.print(current + " -> ");
                    
                    List<String> neighbors = adjacencyList.get(current);
                    if (neighbors.isEmpty()) {
                        System.out.print("No connections");
                    } else {
                        for (String neighbor : neighbors) {
                            System.out.print(neighbor + " ");
                            if (!visited.contains(neighbor)) {
                                visited.add(neighbor);
                                queue.add(neighbor);
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
    
    // Get all locations in graph
    public List<String> getAllLocations() {
        return new ArrayList<>(adjacencyList.keySet());
    }
    
    // Check if location exists
    public boolean containsLocation(String location) {
        return adjacencyList.containsKey(location);
    }
    
    // Get neighbors of a location
    public List<String> getNeighbors(String location) {
        return adjacencyList.getOrDefault(location, new ArrayList<>());
    }
}