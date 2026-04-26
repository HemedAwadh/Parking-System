public class OversizedSpot extends ParkingSpot {

    private int spotNumber;
    private Vehicle vehicle;

    public OversizedSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.vehicle = null; //No vehicle occupying initially
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void occupy(Vehicle vehicle) {
        if (isAvailable()) {
            this.vehicle = vehicle;
        }else  {
            System.out.println("Spot is already occupied");
        }

    }

    public void vacate() {
        this.vehicle = null;//Make spot available

    }

    public VehicleSize getVehicleSize() {
        return VehicleSize.LARGE; // Oversized spots fit Large vehicles
    }

}
