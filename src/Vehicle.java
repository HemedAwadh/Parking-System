/**
 * Base class for all vehicles that can enter the parking lot.
 *
 * <p>A vehicle is identified by its {@code licenceNumber} and categorised by
 * its {@link VehicleSize}. The size is used by {@link ParkingManager} to find
 * the smallest spot that can accommodate the vehicle.
 *
 * <p>Concrete subclasses ({@link Car}, {@link Truck}, {@link Motorcycle})
 * exist as semantic type markers and may be extended with type-specific
 * behaviour in the future.
 */
public class Vehicle {

    private String licenceNumber;
    private VehicleSize size;

    /** Creates a Vehicle with no properties set. */
    public Vehicle() {
    }

    /**
     * Creates a Vehicle with both a licence number and a size.
     *
     * @param licenceNumber the vehicle's licence plate number
     * @param size          the size category (SMALL, MEDIUM, or LARGE)
     */
    public Vehicle(String licenceNumber, VehicleSize size) {
        this.licenceNumber = licenceNumber;
        this.size = size;
    }

    /**
     * Creates a Vehicle with only a licence number.
     * The size must be set separately via {@link #setSize(VehicleSize)}.
     *
     * @param licenceNumber the vehicle's licence plate number
     */
    public Vehicle(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    /**
     * Returns the vehicle's licence plate number.
     *
     * @return the licence number string
     */
    public String getLicenceNumber() {
        return licenceNumber;
    }

    /**
     * Sets the vehicle's licence plate number.
     *
     * @param licenceNumber the new licence number
     */
    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    /**
     * Returns the size category of this vehicle.
     *
     * @return the {@link VehicleSize} enum value
     */
    public VehicleSize getSize() {
        return size;
    }

    /**
     * Sets the size category of this vehicle.
     *
     * @param size the {@link VehicleSize} to assign
     */
    public void setSize(VehicleSize size) {
        this.size = size;
    }
}
