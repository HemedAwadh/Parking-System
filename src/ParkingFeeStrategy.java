/**
 * Strategy interface for calculating a parking fee component.
 *
 * <p>Implementations follow the <em>Strategy</em> pattern: each strategy
 * receives the current running fee and returns a new (typically higher) fee.
 * {@link ParkingFeeCalculator} chains strategies in sequence, passing the
 * output of one as the {@code baseParkingFee} input of the next.
 *
 * <p>Built-in strategies:
 * <ul>
 *   <li>{@link BaseParkingFeeStrategy} — applies a vehicle-size surcharge</li>
 *   <li>{@link PeakParkingFeeStrategy} — adds a flat 50 % peak-hour surcharge</li>
 * </ul>
 */
public interface ParkingFeeStrategy {

    /**
     * Calculates the parking fee for the given ticket, starting from the
     * provided base amount.
     *
     * @param ticket         the parking ticket containing vehicle and time info
     * @param baseParkingFee the running fee total passed in from the previous
     *                       strategy (or the initial base set by
     *                       {@link ParkingFeeCalculator})
     * @return the updated fee after applying this strategy's rules
     */
    Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee);
}
