/**
 * A reserved parking spot for vehicles with disability permits.
 *
 * <p>Physically equivalent to a {@link RegularSpot} in size — it accepts
 * {@link VehicleSize#MEDIUM} vehicles. All state and behaviour is inherited
 * from {@link ParkingSpot}.
 *
 * <p>Access control (permit verification) is outside the scope of this class
 * and should be enforced at the {@link ParkingLot} entry point.
 */
public class HandicappedSpot extends ParkingSpot {

    /**
     * Creates a handicapped spot with the given spot number.
     *
     * @param spotNumber unique identifier for this spot within the lot
     */
    public HandicappedSpot(int spotNumber) {
        super(spotNumber, VehicleSize.MEDIUM);
    }
}
