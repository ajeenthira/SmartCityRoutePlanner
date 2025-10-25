package smartcity;

// CityManager.java
public class CityManager {
    private Graph transportNetwork;
    private AVLTree locationTree;
    private int locationIdCounter;
    
    public CityManager() {
        transportNetwork = new Graph();
        locationTree = new AVLTree();
        locationIdCounter = 1;
    }
    
    // Add new location to both AVL tree and graph
    public boolean addLocation(String name, String type) {
        // Create location object
        Location newLocation = new Location(name, type, locationIdCounter++);
        
        // Add to AVL tree for sorted storage
        locationTree.insert(newLocation);
        
        // Add to graph for connections
        return transportNetwork.addVertex(name);
    }
    
    // Remove location from both structures
    public boolean removeLocation(String name) {
        // Remove from AVL tree
        Location locationToRemove = new Location(name, "", 0);
        locationTree.delete(locationToRemove);
        
        // Remove from graph
        return transportNetwork.removeVertex(name);
    }
    
    // Add road between two locations
    public boolean addRoad(String location1, String location2) {
        return transportNetwork.addEdge(location1, location2);
    }
    
    // Remove road between two locations
    public boolean removeRoad(String location1, String location2) {
        return transportNetwork.removeEdge(location1, location2);
    }
    
    // Display all connections in the transport network
    public void displayConnections() {
        transportNetwork.displayConnections();
    }
    
    // Display all locations from AVL tree (sorted)
    public void displayLocations() {
        locationTree.inorder();
    }
    
    // Check if location exists
    public boolean locationExists(String name) {
        return transportNetwork.containsLocation(name);
    }
    
    // Get transport network for testing
    public Graph getTransportNetwork() {
        return transportNetwork;
    }
}