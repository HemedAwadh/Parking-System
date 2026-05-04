import java.time.LocalDateTime;

public class ParkingLot {
    private final ParkingManager parkingManager;
    private final ParkingFeeCalculator parkingFeeCalculator;

    public ParkingLot(ParkingManager parkingManager, ParkingFeeCalculator parkingFeeCalculator) {
        this.parkingManager = parkingManager;
        this.parkingFeeCalculator = parkingFeeCalculator;
    }

    public Ticket enterVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingManager.parkVehicle(vehicle);
        if (spot != null) {
            return new Ticket(LocalDateTime.now(), spot, vehicle);
        }
        return null; // No spot available
    }

    public int leaveVehicle(Ticket ticket) {
        if (ticket == null || ticket.getExitTime() != null) {
            return -1; // Invalid ticket or vehicle already exited
        }
        ticket.setExitTime(LocalDateTime.now());
        parkingManager.unParkVehicle(ticket.getVehicle());
        return parkingFeeCalculator.calculateParkingFee(ticket);
    }
}
