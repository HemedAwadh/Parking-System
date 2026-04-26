import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private Map<ParkingSpotSize, List<ParkingSpot>> emptyParkingSpots;
    private Map<Vehicle,ParkingSpot> vehicleParkingSpots;
    private final Map<ParkingSpot,Vehicle> spotToVehicleMap;

    public ParkingManager(Map<ParkingSpotSize, List<ParkingSpot>> emptyParkingSpots, Map<Vehicle, ParkingSpot> vehicleParkingSpots, Map<ParkingSpot, Vehicle> spotToVehicleMap) {
        this.emptyParkingSpots = emptyParkingSpots;
        this.spotToVehicleMap = spotToVehicleMap;
        this.vehicleParkingSpots = new HashMap<>();
    }


    public ParkingSpot getParkingSpot(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();

        // Start looking for the smallest spot that can fit the vehicle
        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() >= vehicleSize.ordinal()) {
                List<ParkingSpot> parkingSpots = emptyParkingSpots.get(size);
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (!parkingSpot.isOccupied()){
                        return parkingSpot; //Return the first available parking spot
                    }

                }
            }

        }
        return null; //No suitable spot found

    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = getParkingSpot(vehicle);
        if (!parkingSpot.isOccupied()) {
            parkingSpot.occupy(vehicle);
            vehicleParkingSpots.put(vehicle,parkingSpot);
            spotToVehicleMap.put(parkingSpot,vehicle);
            List<ParkingSpot> spots = emptyParkingSpots.get(parkingSpot.getSize());
            spots.remove(parkingSpot);
            return parkingSpot; //Park successful
        }

        return null; // No spot found for this vehicle
    }

    public void unParkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = vehicleParkingSpots.remove(vehicle);
        if (parkingSpot != null) {
            spotToVehicleMap.remove(parkingSpot);
            parkingSpot.vacate();
            emptyParkingSpots.get(parkingSpot.getSize()).add(parkingSpot);
        }
    }
    //Find Vehicle's parking spot
    public ParkingSpot findVehicleByParkingSpot(Vehicle vehicle) {
        return vehicleParkingSpots.get(vehicle);
    }

   //Find which vehicle is parked in a spot
    public Vehicle findSpotByVehicle(ParkingSpot parkingSpot) {
        return spotToVehicleMap.get(parkingSpot);
    }

}
