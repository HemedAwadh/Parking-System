/**
 * An oversized parking spot designed for large vehicles such as trucks and buses.
 *
 * <p>Accepts vehicles of size {@link VehicleSize#LARGE}. All state and
 * behaviour is inherited from {@link ParkingSpot}.
 */
public class OversizedSpot extends ParkingSpot {
    /**
     * Creates an oversized spot with the given spot number.
     *
     * @param spotNumber unique identifier for this spot within the lot
     */
    public OversizedSpot(int spotNumber) {

        super(spotNumber, VehicleSize.LARGE);
    }
}
