import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Highway {

    List<Toll> tollPoints; // 0 : toll A - 25
    Map<String  , Vehicle > vehicleRecords;
//     eg vehrevoreds.put( 1223 , car)

    public Highway(List<Toll> tollPoints){
        this.tollPoints = tollPoints;
        this.vehicleRecords = new HashMap<>();
    }

    public void processJourney(String vehicleNumber ,String  vehicleType , boolean isVip , String start , String  end , List<Integer> tollRoute){

        Vehicle vehicle = vehicleRecords.computeIfAbsent(vehicleNumber , vn ->  new Vehicle(vn , vehicleType , isVip));

        int totalAmount = 0 ;

        for( int tollId : tollRoute){
            // 0 , 1 , 2
            Toll toll = tollPoints.get(tollId);
            int charge = toll.calculateToll(vehicleType , isVip);
            toll.recordVehicle(vehicle , charge);
            totalAmount += charge;
        }

        Journey journey = new Journey(start , end , tollRoute , totalAmount);
        vehicle.addJourney(journey);
        System.out.println("Journey Completed ! Total toll paid  : " + totalAmount);

    }

    public void displayDetails(){
        for (Toll toll : tollPoints){
            toll.displayDetails();
        }
    }

    public void displayVehicleDetails(){
        for (Vehicle vehicle : vehicleRecords.values()){
            vehicle.displayDetails();
        }
    }

    public List<Integer> findCirCularRoute(int start , int end){
        List<Integer> forwardRoute = new ArrayList<>();
        List<Integer> backwardRoute = new ArrayList<>();
        int totalTolls = tollPoints.size();

        for( int i = start ; i != end ; i = (i+ 1) % totalTolls){
            forwardRoute.add(i);
        }
        forwardRoute.add(end);

        for (int i = start ; i != end ; i = ( i - 1 + totalTolls ) % totalTolls){
            backwardRoute.add(i);

        }
        backwardRoute.add(end);

        return forwardRoute.size() <= backwardRoute.size() ? forwardRoute : backwardRoute;
    }

    // Calculate toll for regular route between start and end
    public int calculateRegularTollForRoute(int start, int end, String vehicleType, boolean isVIP) {
        int totalCost = 0;
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                Toll toll = tollPoints.get(i);
                totalCost += toll.calculateToll(vehicleType, isVIP);
            }
        } else {
            // [0, 1, 2]
            for (int i = start; i < tollPoints.size(); i++) {
                Toll toll = tollPoints.get(i);
                totalCost += toll.calculateToll(vehicleType, isVIP);
            }
            for (int i = 0; i <= end; i++) {
                Toll toll = tollPoints.get(i);
                totalCost += toll.calculateToll(vehicleType, isVIP);
            }
        }
        return totalCost;
    }

    // Calculate toll for a specific route
    public int calculateTollForRoute(List<Integer> tollRoute, String vehicleType, boolean isVIP) {
        int totalCost = 0;
        for (int tollId : tollRoute) {
            Toll toll = tollPoints.get(tollId);
            totalCost += toll.calculateToll(vehicleType, isVIP);
        }
        return totalCost;
    }


}
