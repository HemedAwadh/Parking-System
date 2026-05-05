/**
 * Fee strategy that applies a vehicle-size surcharge to the base parking fee.
 *
 * <p>Surcharge rates:
 * <ul>
 *   <li>SMALL  vehicles — 20 %</li>
 *   <li>MEDIUM vehicles — 30 %</li>
 *   <li>LARGE  vehicles — 40 %</li>
 * </ul>
 *
 * <p>Formula: {@code fee = baseParkingFee + (baseParkingFee * rate / 100)}
 *
 * <p>This is usually the first strategy in the {@link ParkingFeeCalculator}
 * chain. Subsequent strategies (e.g. {@link PeakParkingFeeStrategy}) receive
 * the result of this calculation as their own base.
 */
public class BaseParkingFeeStrategy implements ParkingFeeStrategy {

    private static final int SMALL_VEHICLE_RATE = 20;
    private static final int MEDIUM_VEHICLE_RATE = 30;
    private static final int LARGE_VEHICLE_RATE = 40;

    /**
     * Applies a size-based percentage surcharge to {@code baseParkingFee}.
     *
     * @param ticket         the parking ticket (used to read vehicle size)
     * @param baseParkingFee the fee amount before this strategy is applied
     * @return the fee after adding the size surcharge
     */
    @Override
    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
        int rate;
        if (ticket.getVehicle().getSize() == VehicleSize.SMALL) {
            rate = SMALL_VEHICLE_RATE;
        } else if (ticket.getVehicle().getSize() == VehicleSize.MEDIUM) {
            rate = MEDIUM_VEHICLE_RATE;
        } else {
            rate = LARGE_VEHICLE_RATE;
        }
        return baseParkingFee + (baseParkingFee * rate / 100);
    }
}
