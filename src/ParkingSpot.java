/**
 * Represents a single physical parking space in the lot.
 *
 * <p>Each spot has a unique number and is designed to accept vehicles up to a
 * given {@link VehicleSize}. The spot tracks the currently parked {@link Vehicle}
 * and exposes {@link #isOccupied()} so that {@link ParkingManager} can decide
 * whether the spot is available.
 *
 * <p>Concrete subclasses ({@link CompactSpot}, {@link RegularSpot},
 * {@link HandicappedSpot}, {@link OversizedSpot}) set the accepted size at
 * construction time; they inherit all state management from this class.
 */
public class ParkingSpot {

    private final int spotNumber;
    private final VehicleSize acceptedSize;
    private Vehicle vehicle;

    /**
     * Creates a parking spot with the given number and accepted vehicle size.
     *
     * @param spotNumber   unique identifier for this spot within the lot
     * @param acceptedSize the largest vehicle size this spot can accommodate
     */
    public ParkingSpot(int spotNumber, VehicleSize acceptedSize) {
        this.spotNumber = spotNumber;
        this.acceptedSize = acceptedSize;
    }

    /**
     * Returns {@code true} when a vehicle is currently occupying this spot.
     *
     * @return {@code true} if occupied, {@code false} if available
     */
    public boolean isOccupied() {
        return vehicle != null;
    }

    /**
     * Parks the given vehicle in this spot.
     *
     * <p>If the spot is already occupied a warning is printed and the vehicle
     * is not recorded — the caller should check {@link #isOccupied()} first.
     *
     * @param vehicle the vehicle to park
     */
    public void occupy(Vehicle vehicle) {
        if (this.vehicle == null) {
            this.vehicle = vehicle;
        } else {
            System.out.println("Spot " + spotNumber + " is already occupied");
        }
    }

    /**
     * Removes the currently parked vehicle, making the spot available again.
     */
    public void vacate() {
        this.vehicle = null;
    }

    /**
     * Returns the unique spot number.
     *
     * @return the spot number
     */
    public int getSpotNumber() {
        return spotNumber;
    }

    /**
     * Returns the largest vehicle size this spot can accommodate.
     *
     * <p>Used by {@link ParkingManager} to bucket spots by size when building
     * the available-spot map.
     *
     * @return the accepted {@link VehicleSize}
     */
    public VehicleSize getAcceptedSize() {
        return acceptedSize;
    }
}
