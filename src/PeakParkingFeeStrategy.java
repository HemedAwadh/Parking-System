public class PeakParkingFeeStrategy implements ParkingFeeStrategy {
    private static final int PEAK_RATE = 50;

    @Override
    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
        return baseParkingFee + (baseParkingFee * PEAK_RATE / 100);
    }
}
