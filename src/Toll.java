import java.util.*;

class Toll {
    int tollId;  // Unique ID for the toll
    Map<String, Integer> chargesPerVehicleType;  // Charges for each vehicle type
    List<VehiclePayment> vehiclesPassed;  // Vehicles that passed through this toll
    int totalRevenue;  // Total revenue collected by the toll
    public Toll(int tollId, Map<String, Integer> chargesPerVehicleType) {
        this.tollId = tollId;
        this.chargesPerVehicleType = chargesPerVehicleType;
        this.vehiclesPassed = new ArrayList<>();
        this.totalRevenue = 0;
    }
    // Calculate toll charge based on vehicle type and VIP status
    public int calculateToll(String vehicleType, boolean isVIP) {
        int charge = chargesPerVehicleType.getOrDefault(vehicleType, 0);
        if (isVIP) {
            charge -= (charge * 20) / 100;  // Apply 20% discount
        }
        return charge;
    }
    // Record vehicle details and update total revenue
    public void recordVehicle(Vehicle vehicle, int charge) {
        vehiclesPassed.add(new VehiclePayment(vehicle.vehicleNumber, charge));
        totalRevenue += charge;
    }
    // Display toll details
    public void displayDetails() {
        System.out.println("Toll ID: " + tollId);
        System.out.println("Vehicles Passed:");
        for (VehiclePayment vp : vehiclesPassed) {
            System.out.println("Vehicle: " + vp.vehicleNumber + ", Paid: ₹" + vp.amountPaid);
        }
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}