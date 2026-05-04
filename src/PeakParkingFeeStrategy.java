/**
 * Fee strategy that applies a flat peak-hour surcharge.
 *
 * <p>Adds 50 % on top of whatever fee the previous strategy produced.
 * Formula: {@code fee = baseParkingFee + (baseParkingFee * 50 / 100)}
 *
 * <p>This strategy is typically added to the {@link ParkingFeeCalculator}
 * chain only when the parking lot is operating in peak-hour mode.
 */
public class PeakParkingFeeStrategy implements ParkingFeeStrategy {

    private static final int PEAK_RATE = 50;

    /**
     * Adds a 50 % peak-hour surcharge to {@code baseParkingFee}.
     *
     * @param ticket         the parking ticket (not used directly by this strategy)
     * @param baseParkingFee the fee amount before this strategy is applied
     * @return the fee after adding the peak surcharge
     */
    @Override
    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
        return baseParkingFee + (baseParkingFee * PEAK_RATE / 100);
    }
}
