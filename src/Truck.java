/**
 * Represents a truck or heavy goods vehicle in the parking system.
 *
 * <p>Trucks are typically assigned {@link VehicleSize#LARGE} and require an
 * {@link OversizedSpot}. If no oversized spot is available the system will not
 * issue a ticket.
 */
public class Truck extends Vehicle {

    /**
     * Creates a Truck with the given licence plate and size.
     *
     * @param licenceNumber the vehicle's licence plate number
     * @param size          the size category of this vehicle
     */
    public Truck(String licenceNumber, VehicleSize size) {

        super(licenceNumber, size);
    }
}
