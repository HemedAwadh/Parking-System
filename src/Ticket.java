import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Records a single parking session for one vehicle.
 *
 * <p>A ticket is created when a vehicle enters the lot (via
 * {@link ParkingLot#enterVehicle(Vehicle)}) and updated when it exits
 * (via {@link ParkingLot#leaveVehicle(Ticket)}). The ticket holds all
 * information needed to compute the parking fee:
 *
 * <ul>
 *   <li>A randomly generated alphanumeric {@code ticketNumber}</li>
 *   <li>A reference to the {@link Vehicle} that was parked</li>
 *   <li>A reference to the {@link ParkingSpot} that was occupied</li>
 *   <li>{@code entryTime} set at construction</li>
 *   <li>{@code exitTime} set when the vehicle leaves</li>
 * </ul>
 *
 * <p>Use {@link #calculateParkingDuration()} after {@code exitTime} is set to
 * obtain the total duration in minutes.
 */
public class Ticket {

    private final String ticketNumber;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    /**
     * Creates a ticket for a vehicle that has just entered a parking spot.
     * A unique ticket number is generated automatically.
     *
     * @param entryTime   the date-time at which the vehicle entered the lot
     * @param parkingSpot the spot assigned to this vehicle
     * @param vehicle     the vehicle being parked
     */
    public Ticket(LocalDateTime entryTime, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.ticketNumber = generateTicketNumber(8);
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    /**
     * Generates a random alphanumeric string of the given length, used as the
     * unique ticket identifier.
     *
     * @param length number of characters in the ticket number
     * @return a random uppercase alphanumeric string
     */
    private String generateTicketNumber(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Returns the total time the vehicle was parked, in minutes.
     *
     * <p><strong>Precondition:</strong> {@code exitTime} must have been set via
     * {@link #setExitTime(LocalDateTime)} before calling this method, otherwise
     * a {@link NullPointerException} will be thrown.
     *
     * @return parking duration in minutes
     */
    public long calculateParkingDuration() {
        return Duration.between(entryTime, exitTime).toMinutes();
    }

    /**
     * Records the date-time at which the vehicle left the lot.
     * Called by {@link ParkingLot#leaveVehicle(Ticket)}.
     *
     * @param exitTime the exit date-time
     */
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Returns the auto-generated ticket number.
     *
     * @return the ticket number string
     */
    public String getTicketNumber() {
        return ticketNumber;
    }

    /**
     * Returns the vehicle associated with this ticket.
     *
     * @return the parked {@link Vehicle}
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the parking spot assigned for this session.
     *
     * @return the occupied {@link ParkingSpot}
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /**
     * Returns the date-time at which the vehicle entered the lot.
     *
     * @return the entry {@link LocalDateTime}
     */
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    /**
     * Returns the date-time at which the vehicle exited the lot, or
     * {@code null} if the vehicle is still parked.
     *
     * @return the exit {@link LocalDateTime}, or {@code null}
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
