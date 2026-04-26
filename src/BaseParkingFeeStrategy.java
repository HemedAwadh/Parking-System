import java.math.BigDecimal;

public class BaseParkingFeeStrategy implements ParkingFeeStrategy {
    private final Integer SMALL_VEHICLE_RATE = 20;
    private final Integer MEDIUM_VEHICLE_RATE = 30;
    private final Integer LARGE_VEHICLE_RATE = 40;

    @Override
    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
        if (ticket.getVehicle().getSize() == VehicleSize.SMALL) {
            return baseParkingFee + (baseParkingFee * SMALL_VEHICLE_RATE / 100);
        } else if (ticket.getVehicle().getSize() == VehicleSize.MEDIUM) {
            return baseParkingFee + (baseParkingFee * MEDIUM_VEHICLE_RATE / 100);
        } else {
            return baseParkingFee + (baseParkingFee * LARGE_VEHICLE_RATE / 100);
        }
    }

//
//    /// implement using switch
//    @Override
//    public Integer calculateParkingFee(Ticket ticket, Integer baseParkingFee) {
//        int rate;
//        switch (ticket.getVehicle().getSize()) {
//            case SMALL:
//                rate = SMALL_VEHICLE_RATE;
//                break;
//            case MEDIUM:
//                rate = MEDIUM_VEHICLE_RATE;
//                break;
//            case LARGE:
//            default:
//                rate = LARGE_VEHICLE_RATE;
//                break;
//        }
//
//        return baseParkingFee + (baseParkingFee * rate / 100);
//    }


}
