import java.time.LocalDateTime;

/**
 * Public façade for the parking lot system.
 *
 * <p>Coordinates the two primary operations — vehicle entry and exit — by
 * delegating to:
 * <ul>
 *   <li>{@link ParkingManager} for spot allocation / release</li>
 *   <li>{@link ParkingFeeCalculator} for fee computation on exit</li>
 * </ul>
 *
 * <p>Typical usage:
 * <pre>{@code
 *   ParkingLot lot = new ParkingLot(manager, calculator);
 *
 *   // Vehicle enters
 *   Ticket ticket = lot.enterVehicle(vehicle);
 *   if (ticket == null) {
 *       System.out.println("Lot is full");
 *   }
 *
 *   // Vehicle leaves
 *   int fee = lot.leaveVehicle(ticket);
 *   System.out.println("Fee: KES " + fee);
 * }</pre>
 */
public class ParkingLot {

    private final ParkingManager parkingManager;
    private final ParkingFeeCalculator parkingFeeCalculator;

    /**
     * Creates a ParkingLot backed by the given manager and fee calculator.
     *
     * @param parkingManager      handles spot allocation and release
     * @param parkingFeeCalculator chains fee strategies to produce the final charge
     */
    public ParkingLot(ParkingManager parkingManager, ParkingFeeCalculator parkingFeeCalculator) {
        this.parkingManager = parkingManager;
        this.parkingFeeCalculator = parkingFeeCalculator;
    }

    /**
     * Processes a vehicle's entry into the parking lot.
     *
     * <p>Asks {@link ParkingManager} to allocate a spot. If a spot is found, a
     * {@link Ticket} is created with the current timestamp and returned to the
     * caller. If the lot is full, {@code null} is returned.
     *
     * @param vehicle the vehicle wishing to enter
     * @return a {@link Ticket} for the session, or {@code null} if no spot is available
     */
    public Ticket enterVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingManager.parkVehicle(vehicle);
        if (spot != null) {
            return new Ticket(LocalDateTime.now(), spot, vehicle);
        }
        return null; // No spot available
    }

    /**
     * Processes a vehicle's exit from the parking lot.
     *
     * <p>Steps:
     * <ol>
     *   <li>Validates the ticket (must be non-null and not already closed)</li>
     *   <li>Stamps the exit time on the ticket</li>
     *   <li>Instructs {@link ParkingManager} to release the spot</li>
     *   <li>Calculates and returns the total fee via {@link ParkingFeeCalculator}</li>
     * </ol>
     *
     * @param ticket the ticket issued when the vehicle entered
     * @return the parking fee in KES, or {@code -1} if the ticket is invalid
     *         or the vehicle has already exited
     */
    public int leaveVehicle(Ticket ticket) {
        if (ticket == null || ticket.getExitTime() != null) {
            return -1; // Invalid ticket or vehicle already exited
        }
        ticket.setExitTime(LocalDateTime.now());
        parkingManager.unParkVehicle(ticket.getVehicle());
        return parkingFeeCalculator.calculateParkingFee(ticket);
    }
}
