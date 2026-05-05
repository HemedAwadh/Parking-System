/**
 * A regular parking spot sized for medium vehicles such as standard cars.
 *
 * <p>Accepts vehicles of size {@link VehicleSize#MEDIUM}. All state and
 * behaviour is inherited from {@link ParkingSpot}.
 */
public class RegularSpot extends ParkingSpot {

    /**
     * Creates a regular spot with the given spot number.
     *
     * @param spotNumber unique identifier for this spot within the lot
     */
    public RegularSpot(int spotNumber) {
        super(spotNumber, VehicleSize.MEDIUM);
    }
}
