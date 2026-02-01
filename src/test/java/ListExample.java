import java.util.*;

public class ListExample {

    public static void main(String[] args) {

        // List<String> to store menu items
        List<String> menuItems = new ArrayList<>();
        menuItems.add("Pizza");
        menuItems.add("Burger");
        menuItems.add("Noodles");
        menuItems.add("oats");

        System.out.println("Menu Items:");
        for (String item : menuItems) {
            System.out.println(item);
        }

        // Set<String> to store duplicate values
        Set<String> users = new HashSet<>();
        users.add("Admin");
        users.add("Guest");
        users.add("Admin"); // duplicate value

        System.out.println("\nSet Values (duplicates not allowed):");
        for (String user : users) {
            System.out.println(user);
        }

        // Map<String, String> for username and password
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("user1", "password123");
        loginDetails.put("admin", "admin@123");

        System.out.println("\nLogin Details:");
        for (Map.Entry<String, String> entry : loginDetails.entrySet()) {
            System.out.println("Username: " + entry.getKey() + ", Password: " + entry.getValue());
        }
    }
}