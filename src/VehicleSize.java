/**
 * Classifies vehicles and parking spots by physical size.
 *
 * <p>The ordinal order matters: {@link ParkingManager} uses it to find the
 * smallest available spot that can still fit a given vehicle.
 *
 * <ul>
 *   <li>{@code SMALL}  — motorcycles and scooters → {@link CompactSpot}</li>
 *   <li>{@code MEDIUM} — standard cars and SUVs   → {@link RegularSpot} / {@link HandicappedSpot}</li>
 *   <li>{@code LARGE}  — trucks and buses         → {@link OversizedSpot}</li>
 * </ul>
 */
public enum VehicleSize {
    SMALL, MEDIUM, LARGE
}
