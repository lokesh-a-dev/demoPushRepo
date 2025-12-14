import java.util.*;

class Vehicle {
    String vehicleNumber;  // Unique vehicle number
    String vehicleType;  // Type of vehicle (Car, Bike, Truck)
    boolean isVIP;  // Whether the vehicle belongs to a VIP user
    List<Journey> journeys;  // List of journeys taken by the vehicle
    public Vehicle(String vehicleNumber, String vehicleType, boolean isVIP) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.isVIP = isVIP;
        this.journeys = new ArrayList<>();
    }
    // Add a journey to the vehicle's record
    public void addJourney(Journey journey) {
        journeys.add(journey);
    }
    // Display vehicle details
    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + vehicleType + ", VIP: " + isVIP);
        for (Journey journey : journeys) {
            System.out.println("Journey: " + journey.startPoint + " -> " + journey.endPoint);
            System.out.println("Tolls Passed: " + journey.tollsPassed);
            System.out.println("Amount Paid: ₹" + journey.amountPaid);
        }
        int totalPaid = journeys.stream().mapToInt(j -> j.amountPaid).sum();
        System.out.println("Total Amount Paid: ₹" + totalPaid);
    }
}