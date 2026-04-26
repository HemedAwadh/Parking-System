public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        Animal animal = new Animal();

        String a = null;
        animal.setName(a);

        String b = "Hello World";
        animal.setName(b);



        String plate = vehicle.getLicenceNumber();
        vehicle.setLicenceNumber("KDP");


    }
}