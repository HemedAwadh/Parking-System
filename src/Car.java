/**
 * Represents a standard car in the parking system.
 *
 * <p>Cars are typically assigned {@link VehicleSize#MEDIUM} and parked in
 * {@link RegularSpot} or {@link HandicappedSpot} spaces. A car with
 * {@link VehicleSize#SMALL} may also fit in a {@link CompactSpot}.
 */
public class Car extends Vehicle {

    /**
     * Creates a Car with the given licence plate and size.
     *
     * @param licenceNumber the vehicle's licence plate number
     * @param size          the size category of this vehicle
     */
    public Car(String licenceNumber, VehicleSize size) {

        super(licenceNumber, size);
    }
}
