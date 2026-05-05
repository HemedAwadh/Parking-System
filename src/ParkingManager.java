import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages spot allocation and vehicle tracking within the parking lot.
 *
 * <p>Internally maintains three data structures:
 * <ul>
 *   <li>{@code emptyParkingSpots} — available spots grouped by accepted vehicle size</li>
 *   <li>{@code vehicleParkingSpots} — maps each parked {@link Vehicle} to its {@link ParkingSpot}</li>
 *   <li>{@code spotToVehicleMap} — reverse lookup: maps each occupied spot to its vehicle</li>
 * </ul>
 *
 * <p>Spot selection follows a <em>best-fit-ascending</em> strategy: the manager
 * starts from the vehicle's own size and works upward through larger sizes,
 * returning the first available spot it finds. This minimises wasted space.
 *
 * <p>{@link ParkingLot} is the public façade; callers should use that class
 * rather than invoking {@code ParkingManager} directly.
 */
public class ParkingManager {

    private final Map<VehicleSize, List<ParkingSpot>> emptyParkingSpots;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpots;
    private final Map<ParkingSpot, Vehicle> spotToVehicleMap;

    /**
     * Creates a ParkingManager with a pre-populated map of available spots.
     *
     * <p>The lists inside {@code emptyParkingSpots} are mutated directly as
     * vehicles park and leave, so callers should pass {@code ArrayList}
     * instances (or other mutable lists).
     *
     * @param emptyParkingSpots a mutable map from {@link VehicleSize} to the
     *                          list of spots of that size that are initially free
     */
    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> emptyParkingSpots) {
        this.emptyParkingSpots = emptyParkingSpots;
        this.vehicleParkingSpots = new HashMap<>();
        this.spotToVehicleMap = new HashMap<>();
    }

    /**
     * Finds the smallest available spot that can accommodate the given vehicle.
     *
     * <p>Iterates through all {@link VehicleSize} values in ordinal order (SMALL →
     * MEDIUM → LARGE) and returns the first unoccupied spot whose accepted size
     * is at least as large as the vehicle's size.
     *
     * @param vehicle the vehicle looking for a spot
     * @return the best available {@link ParkingSpot}, or {@code null} if the lot is full
     */
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

    /**
     * Allocates a spot for the given vehicle and updates all internal maps.
     *
     * <p>Steps:
     * <ol>
     *   <li>Find the best available spot via {@link #getParkingSpot(Vehicle)}</li>
     *   <li>Mark the spot as occupied</li>
     *   <li>Record the vehicle ↔ spot mapping in both directions</li>
     *   <li>Remove the spot from the available-spots list</li>
     * </ol>
     *
     * @param vehicle the vehicle to park
     * @return the {@link ParkingSpot} that was allocated, or {@code null} if
     *         no suitable spot was available
     */
    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = getParkingSpot(vehicle);
        if (spot == null) return null;
        spot.occupy(vehicle);
        vehicleParkingSpots.put(vehicle, spot);
        spotToVehicleMap.put(spot, vehicle);
        emptyParkingSpots.get(spot.getAcceptedSize()).remove(spot);
        return spot;
    }

    /**
     * Releases the spot occupied by the given vehicle and updates all internal maps.
     *
     * <p>If the vehicle is not currently tracked (e.g. it was never parked or has
     * already been unparked) this method does nothing.
     *
     * @param vehicle the vehicle leaving the lot
     */
    public void unParkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleParkingSpots.remove(vehicle);
        if (spot != null) {
            spotToVehicleMap.remove(spot);
            spot.vacate();
            emptyParkingSpots.get(spot.getAcceptedSize()).add(spot);
        }
    }

    /**
     * Looks up the spot currently occupied by the given vehicle.
     *
     * @param vehicle the vehicle to look up
     * @return the vehicle's current {@link ParkingSpot}, or {@code null} if not parked
     */
    public ParkingSpot findSpotByVehicle(Vehicle vehicle) {

        return vehicleParkingSpots.get(vehicle);
    }
    /**
     * Looks up the vehicle currently occupying the given spot.
     *
     * @param spot the spot to query
     * @return the {@link Vehicle} in that spot, or {@code null} if the spot is empty
     */
    public Vehicle findVehicleBySpot(ParkingSpot spot) {

        return spotToVehicleMap.get(spot);
    }
}
