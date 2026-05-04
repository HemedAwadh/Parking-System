/**
 * Represents a motorcycle or scooter in the parking system.
 *
 * <p>Motorcycles are typically assigned {@link VehicleSize#SMALL} and are
 * preferentially placed in {@link CompactSpot} spaces. When compact spots are
 * full the system will fall back to larger spots.
 */
public class Motorcycle extends Vehicle {

    /**
     * Creates a Motorcycle with the given licence plate and size.
     *
     * @param licenceNumber the vehicle's licence plate number
     * @param size          the size category of this vehicle
     */
    public Motorcycle(String licenceNumber, VehicleSize size) {
        super(licenceNumber, size);
    }
}
