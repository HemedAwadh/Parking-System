public interface ParkingFeeStrategy {
    Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee);
}
