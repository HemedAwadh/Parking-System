public class ParkingSpot {
    private int parkingSpotNumber;
    private ParkingSpotSize size;
    private boolean isOccupied;

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy(Vehicle vehicle) {
        isOccupied = true;
        System.out.println("Occupied Vehicle: " + vehicle.getLicenceNumber());
    }

    public void vacate () {
        isOccupied = false;
    }

    public ParkingSpot() {
    }

    public ParkingSpot(int parkingSpotNumber, ParkingSpotSize size, boolean isOccupied) {
        this.parkingSpotNumber = parkingSpotNumber;
        this.size = size;
        this.isOccupied = isOccupied;
    }

    public int getParkingSpotNumber() {

        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(int parkingSpotNumber) {

        this.parkingSpotNumber = parkingSpotNumber;
    }

    public ParkingSpotSize getSize() {
        return size;
    }

    public void setSize(ParkingSpotSize size) {
        this.size = size;
    }
}
