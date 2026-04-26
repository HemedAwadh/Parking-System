import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot {
    // Manages parking spots and vehicle assignments
     private final ParkingManager parkingManager;
    // Calculates fare for parking sessions
     private final ParkingFeeCalculator parkingFeeCalculator;


    public ParkingLot(ParkingManager parkingManager, ParkingFeeCalculator parkingFeeCalculator) {
        this.parkingManager = parkingManager;
        this.parkingFeeCalculator = parkingFeeCalculator;
    }

    // Method to handle vehicle entry into the parking lot
 public Ticket  enterVehicle(Vehicle vehicle) {
        //Delegate Parking logic to Parking Manager
     ParkingSpot parkingSpot = parkingManager.parkVehicle(vehicle);
     if (parkingSpot != null) {
         //create ticket with entry time
         Ticket ticket = new Ticket(generateTicketNumber(),vehicle,parkingSpot, LocalDateTime.now());
         return ticket;
     }else  {
         return null; // No spot available
     }

 }
   //Method to handle Vehicle exit from the parking lot
    public void  leaveVehicle(Ticket ticket) {
        //Ensure ticket is valid and the vehicle hasn't already left
        if (ticket != null && ticket.getExitTime() != null) {
            //set exit time
            ticket.setExitTime(LocalDateTime.now());

            //Delegate unparking logic to parking Manager
            parkingManager.unParkVehicle(ticket.getVehicle());

            //calculate the fee
            BigDecimal fee = BigDecimal.valueOf(parkingFeeCalculator.calculateParkingFee(ticket));

        }else   {
            // invalid ticket or vehicle already exited
        }
    }


}
