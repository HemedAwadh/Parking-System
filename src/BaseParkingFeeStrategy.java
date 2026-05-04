public class BaseParkingFeeStrategy implements ParkingFeeStrategy {
    private static final int SMALL_VEHICLE_RATE = 20;
    private static final int MEDIUM_VEHICLE_RATE = 30;
    private static final int LARGE_VEHICLE_RATE = 40;

    @Override
    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
        int rate;
        if (ticket.getVehicle().getSize() == VehicleSize.SMALL) {
            rate = SMALL_VEHICLE_RATE;
        } else if (ticket.getVehicle().getSize() == VehicleSize.MEDIUM) {
            rate = MEDIUM_VEHICLE_RATE;
        } else {
            rate = LARGE_VEHICLE_RATE;
        }
        return baseParkingFee + (baseParkingFee * rate / 100);
    }
}
