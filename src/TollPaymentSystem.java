import java.util.*;

public class TollPaymentSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize tolls
        List<Toll> tollPoints = new ArrayList<>();
        tollPoints.add(new Toll(0, Map.of("Car", 50, "Bike", 20, "Truck", 100)));
        tollPoints.add(new Toll(1, Map.of("Car", 60, "Bike", 25, "Truck", 120)));
        tollPoints.add(new Toll(2, Map.of("Car", 70, "Bike", 30, "Truck", 150)));

        Highway highway = new Highway(tollPoints);

        while (true){
            System.out.println("1. Process Journey");
            System.out.println("2. Display Toll Details");
            System.out.println("3. Display Vehicle Details");
            System.out.println("4. Find Shortest Route and Calculate Toll");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String vehicleNumber = scanner.next();
                    System.out.print("Enter Vehicle Type (e.g., Car, Bike, Truck): ");
                    String vehicleType = scanner.next();
                    System.out.print("Are you a VIP? (true/false): ");
                    boolean isVIP = scanner.nextBoolean();
                    System.out.print("Enter Start Point: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter End Point: ");
                    int end = scanner.nextInt();

                    // Calculate toll for regular route between start and end
                    int regularToll = highway.calculateRegularTollForRoute(start, end, vehicleType, isVIP);
                    System.out.println("Total Toll Paid: " + regularToll);

                    // Process the journey
                    List<Integer> tollRoute = new ArrayList<>();
                    for (int i = start; i <= end; i++) {
                        tollRoute.add(i);  //[0,1,2]
                    }
                    highway.processJourney(vehicleNumber, vehicleType, isVIP, String.valueOf(start), String.valueOf(end), tollRoute);
                    break;

                case 2:
                    highway.displayDetails();
                    break;
                case 3:
                    highway.displayVehicleDetails();
                    break;
                case 4:
                    System.out.print("Enter Start and End Points: ");
                    int start1 = scanner.nextInt();
                    int end1 = scanner.nextInt();

                    // Ensure that the start is always less than or equal to the end
                    if (start1 > end1) {
                        int temp = start1;
                        start1 = end1;
                        end1 = temp;
                    }

                    // Find the shortest route (either forward or backward)
                    List<Integer> tollRouteCircular = highway.findCirCularRoute(start1, end1);
                    System.out.println("Shortest Route Tolls: " + tollRouteCircular);

                    // Calculate and print toll cost for the shortest route
                    System.out.print("Enter Vehicle Type (e.g., Car, Bike, Truck): ");
                    String vehicleType1 = scanner.next();
                    System.out.print("Are you a VIP? (true/false): ");
                    boolean isVIP1 = scanner.nextBoolean();

                    int totalCostCircular = highway.calculateTollForRoute(tollRouteCircular, vehicleType1, isVIP1);
                    System.out.println("Total Toll Cost for Circular Route: " + totalCostCircular);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
