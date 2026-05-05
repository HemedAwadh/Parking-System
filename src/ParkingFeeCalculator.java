import java.util.List;

/**
 * Calculates the total parking fee by chaining a list of {@link ParkingFeeStrategy}
 * implementations.
 *
 * <p>The calculator starts with a fixed base fee of <strong>100</strong> and
 * passes it through each strategy in order. Every strategy receives the
 * running total and returns a new (typically higher) value:
 *
 * <pre>
 *   fee = 100
 *   fee = strategy1.calculateParkingFee(ticket, fee)
 *   fee = strategy2.calculateParkingFee(ticket, fee)
 *   ...
 * </pre>
 *
 * <p>Example with base + peak strategies for a MEDIUM vehicle:
 * <ol>
 *   <li>Base:  100 + 30 % = 130</li>
 *   <li>Peak:  130 + 50 % = 195</li>
 * </ol>
 */
public class ParkingFeeCalculator {

    private final List<ParkingFeeStrategy> parkingFeeStrategies;

    /**
     * Creates a calculator that will apply the given strategies in order.
     *
     * @param parkingFeeStrategies ordered list of fee strategies; must not be null
     */
    public ParkingFeeCalculator(List<ParkingFeeStrategy> parkingFeeStrategies) {
        this.parkingFeeStrategies = parkingFeeStrategies;
    }

    /**
     * Computes the total parking fee for the given ticket.
     *
     * <p>Starts at a base of 100 and chains all configured strategies.
     *
     * @param ticket the parking ticket whose vehicle size and duration inform the fee
     * @return the final fee after all strategies have been applied
     */
    public Integer calculateParkingFee(Ticket ticket) {
        Integer parkingFee = 100;
        for (ParkingFeeStrategy strategy : parkingFeeStrategies) {
            parkingFee = strategy.calculateParkingFee(ticket, parkingFee);
        }
        return parkingFee;
    }
}
