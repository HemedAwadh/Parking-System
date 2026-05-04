/**
 * A compact parking spot sized for small vehicles such as motorcycles.
 *
 * <p>Accepts vehicles of size {@link VehicleSize#SMALL}. All state and
 * behaviour is inherited from {@link ParkingSpot}.
 */
public class CompactSpot extends ParkingSpot {

    /**
     * Creates a compact spot with the given spot number.
     *
     * @param spotNumber unique identifier for this spot within the lot
     */
    public CompactSpot(int spotNumber) {
        super(spotNumber, VehicleSize.SMALL);
    }
}
