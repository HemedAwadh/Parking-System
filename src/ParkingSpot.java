public class ParkingSpot {
    private final int spotNumber;
    private final VehicleSize acceptedSize;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, VehicleSize acceptedSize) {
        this.spotNumber = spotNumber;
        this.acceptedSize = acceptedSize;
    }

    public boolean isOccupied() {
        return vehicle != null;
    }

    public void occupy(Vehicle vehicle) {
        if (this.vehicle == null) {
            this.vehicle = vehicle;
        } else {
            System.out.println("Spot " + spotNumber + " is already occupied");
        }
    }

    public void vacate() {
        this.vehicle = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getAcceptedSize() {
        return acceptedSize;
    }
}
