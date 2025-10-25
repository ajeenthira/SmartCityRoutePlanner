package smartcity;

// Location.java
public class Location implements Comparable<Location> {
    private String name;
    private String type; // e.g., "Park", "Mall", "Hospital", etc.
    private int id;
    
    // Constructor
    public Location(String name, String type, int id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    
    // For sorting in AVL tree
    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }
    
    // For displaying location info
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
    
    // For comparing locations
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return name.equals(location.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}