package smartcity;

// CityRoutePlanner.java
import java.util.Scanner;

public class CityRoutePlanner {
    private CityManager cityManager;
    private Scanner scanner;
    
    public CityRoutePlanner() {
        cityManager = new CityManager();
        scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("Welcome to Smart City Route Planner!");
        System.out.println("===================================");
        
        int choice;
        
        do {
            displayMenu();
            choice = getValidIntegerInput("Enter your choice (1-7): ");
            
            switch (choice) {
                case 1:
                    addLocation();
                    break;
                case 2:
                    removeLocation();
                    break;
                case 3:
                    addRoad();
                    break;
                case 4:
                    removeRoad();
                    break;
                case 5:
                    displayConnections();
                    break;
                case 6:
                    displayLocations();
                    break;
                case 7:
                    System.out.println("Thank you for using Smart City Route Planner! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1-7.");
            }
            
            if (choice != 7) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine(); // Wait for user input
            }
        } while (choice != 7);
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n=== Smart City Route Planner ===");
        System.out.println("1. Add a new location");
        System.out.println("2. Remove a location");
        System.out.println("3. Add a road between locations");
        System.out.println("4. Remove a road between locations");
        System.out.println("5. Display all connections");
        System.out.println("6. Display all locations (using AVL Tree)");
        System.out.println("7. Exit");
        System.out.println("================================");
    }
    
    private void addLocation() {
        System.out.println("\n--- Add New Location ---");
        System.out.print("Enter location name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Error: Location name cannot be empty!");
            return;
        }
        
        System.out.print("Enter location type (Park, Mall, Hospital, etc.): ");
        String type = scanner.nextLine().trim();
        
        if (type.isEmpty()) {
            type = "General"; // Default type
        }
        
        if (cityManager.addLocation(name, type)) {
            System.out.println("Success: Location '" + name + "' added to the city!");
        } else {
            System.out.println("Error: Location '" + name + "' already exists!");
        }
    }
    
    private void removeLocation() {
        System.out.println("\n--- Remove Location ---");
        System.out.print("Enter location name to remove: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Error: Please enter a valid location name!");
            return;
        }
        
        if (cityManager.removeLocation(name)) {
            System.out.println("Success: Location '" + name + "' removed from the city!");
        } else {
            System.out.println("Error: Location '" + name + "' not found!");
        }
    }
    
    private void addRoad() {
        System.out.println("\n--- Add Road Between Locations ---");
        System.out.print("Enter first location: ");
        String loc1 = scanner.nextLine().trim();
        
        System.out.print("Enter second location: ");
        String loc2 = scanner.nextLine().trim();
        
        if (loc1.isEmpty() || loc2.isEmpty()) {
            System.out.println("Error: Both location names are required!");
            return;
        }
        
        if (loc1.equals(loc2)) {
            System.out.println("Error: Cannot connect a location to itself!");
            return;
        }
        
        if (!cityManager.locationExists(loc1)) {
            System.out.println("Error: Location '" + loc1 + "' does not exist!");
            return;
        }
        
        if (!cityManager.locationExists(loc2)) {
            System.out.println("Error: Location '" + loc2 + "' does not exist!");
            return;
        }
        
        if (cityManager.addRoad(loc1, loc2)) {
            System.out.println("Success: Road between '" + loc1 + "' and '" + loc2 + "' added!");
        } else {
            System.out.println("Error: Road might already exist!");
        }
    }
    
    private void removeRoad() {
        System.out.println("\n--- Remove Road Between Locations ---");
        System.out.print("Enter first location: ");
        String loc1 = scanner.nextLine().trim();
        
        System.out.print("Enter second location: ");
        String loc2 = scanner.nextLine().trim();
        
        if (loc1.isEmpty() || loc2.isEmpty()) {
            System.out.println("Error: Both location names are required!");
            return;
        }
        
        if (cityManager.removeRoad(loc1, loc2)) {
            System.out.println("Success: Road between '" + loc1 + "' and '" + loc2 + "' removed!");
        } else {
            System.out.println("Error: Road not found or locations don't exist!");
        }
    }
    
    private void displayConnections() {
        System.out.println("\n--- Displaying All Connections ---");
        cityManager.displayConnections();
    }
    
    private void displayLocations() {
        System.out.println("\n--- Displaying All Locations ---");
        cityManager.displayLocations();
    }
    
    private int getValidIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1-7.");
            }
        }
    }
    
    public static void main(String[] args) {
        CityRoutePlanner planner = new CityRoutePlanner();
        planner.start();
    }
}