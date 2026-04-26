import java.util.List;

public class ParkingFeeCalculator {
    private final List<ParkingFeeStrategy> parkingFeeStrategies;

    public ParkingFeeCalculator(List<ParkingFeeStrategy> parkingFeeStrategies) {
        this.parkingFeeStrategies = parkingFeeStrategies;
    }

    public Integer calculateParkingFee(Ticket ticket) {
        Integer parkingFee = 100;
        for (ParkingFeeStrategy strategy : parkingFeeStrategies) {
            parkingFee= strategy.calculateParkingFee(ticket, parkingFee);
        }
        return parkingFee;

    }
}
