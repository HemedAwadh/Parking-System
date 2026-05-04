import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private final Map<VehicleSize, List<ParkingSpot>> emptyParkingSpots;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpots;
    private final Map<ParkingSpot, Vehicle> spotToVehicleMap;

    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> emptyParkingSpots) {
        this.emptyParkingSpots = emptyParkingSpots;
        this.vehicleParkingSpots = new HashMap<>();
        this.spotToVehicleMap = new HashMap<>();
    }

    public ParkingSpot getParkingSpot(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() >= vehicleSize.ordinal()) {
                List<ParkingSpot> spots = emptyParkingSpots.get(size);
                if (spots != null) {
                    for (ParkingSpot spot : spots) {
                        if (!spot.isOccupied()) {
                            return spot;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = getParkingSpot(vehicle);
        if (spot == null) return null;
        spot.occupy(vehicle);
        vehicleParkingSpots.put(vehicle, spot);
        spotToVehicleMap.put(spot, vehicle);
        emptyParkingSpots.get(spot.getAcceptedSize()).remove(spot);
        return spot;
    }

    public void unParkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleParkingSpots.remove(vehicle);
        if (spot != null) {
            spotToVehicleMap.remove(spot);
            spot.vacate();
            emptyParkingSpots.get(spot.getAcceptedSize()).add(spot);
        }
    }

    public ParkingSpot findSpotByVehicle(Vehicle vehicle) {
        return vehicleParkingSpots.get(vehicle);
    }

    public Vehicle findVehicleBySpot(ParkingSpot spot) {
        return spotToVehicleMap.get(spot);
    }
}
