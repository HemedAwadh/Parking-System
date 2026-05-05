import java.util.*;

/**
 * Application entry point and demonstration driver for the Parking System.
 *
 * <p>This class wires all components together and simulates a realistic parking
 * session covering the key scenarios:
 * <ol>
 *   <li>Normal vehicle entry — ticket is issued and spot is allocated</li>
 *   <li>Normal vehicle exit  — fee is calculated and spot is released</li>
 *   <li>Double-exit attempt  — rejected because the ticket is already closed</li>
 *   <li>Null-ticket exit     — rejected with an error code</li>
 *   <li>Full-lot entry       — {@code null} returned when no spot fits</li>
 * </ol>
 *
 * <h2>Lot configuration used in this demo</h2>
 * <pre>
 *   Spot 1-2  — CompactSpot    (SMALL  vehicles)
 *   Spot 3-4  — RegularSpot    (MEDIUM vehicles)
 *   Spot 5    — HandicappedSpot(MEDIUM vehicles)
 *   Spot 6-7  — OversizedSpot  (LARGE  vehicles)
 * </pre>
 *
 * <h2>Fee breakdown (base = 100)</h2>
 * <pre>
 *   Base strategy  : adds 20 / 30 / 40 % for SMALL / MEDIUM / LARGE
 *   Peak strategy  : adds a further 50 % on top
 *
 *   SMALL  + base        = 120
 *   MEDIUM + base        = 130
 *   LARGE  + base        = 140
 *   MEDIUM + base + peak = 195
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Is it peak hour?(Yes/No): ");

        String peakInput = input.nextLine();
        boolean peakHours = peakInput.equalsIgnoreCase("Yes") || peakInput.equalsIgnoreCase("No");

        ParkingLot lot = buildParkingLot(peakHours);

        printBanner("PARKING SYSTEM DEMO");

        // ------------------------------------------------------------------ //
        //  1. Vehicle entry
        // ------------------------------------------------------------------ //
        printSection("Vehicle Entry");

        System.out.println("Enter number of vehicles:");
        int vehicles = input.nextInt();
        input.nextLine();
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < vehicles; i++) {
            System.out.println("\n Vehicle #" + (i + 1) + ":");

            System.out.println("Enter Vehicle Type(Motorcycle/Car/Truck): )");
            String type = input.nextLine().toLowerCase();

            System.out.println("Enter Licence Number: ");
            String licence = input.nextLine();

            System.out.println("Enter vehicle size(SMALL/MEDIUM/LARGE): )");
            String size = input.nextLine().toUpperCase();

            VehicleSize vehicleSize ;
            try {
                vehicleSize = VehicleSize.valueOf(size.toUpperCase());

            }catch (IllegalArgumentException e){
                System.out.println("Invalid Vehicle Size.");
                continue;
            }

            Vehicle vehicle;
            switch (type) {
                case "motorcycle":
                    vehicle = new Motorcycle(licence, vehicleSize);
                    break;
                case "car":
                    vehicle = new Car(licence, vehicleSize);
                    break;
                case "truck":
                    vehicle = new Truck(licence, vehicleSize);
                    break;
                default:
                    System.out.println("Invalid input.... Skipping....");
                    continue;
            }

            Ticket ticket = enterAndPrint(lot,vehicle);
            tickets.add(ticket);


        }

        // ------------------------------------------------------------------ //
        //  2. Vehicle exit
        // ------------------------------------------------------------------ //
        printSection("Vehicle Exit");

        while (true) {
            System.out.println("\n Enter ticket number to exit (or exit to stop) :");
            String inpt = input.nextLine();
            if (inpt.equalsIgnoreCase("exit")) {
                break;
            }

            Ticket found = null;
            for (Ticket ticket : tickets) {
                if (ticket != null && ticket.getTicketNumber().equals(inpt)) {
                    found = ticket;
                    break;
                }
            }

            if (found == null) {
                System.out.println("No ticket found!....");
            }else  {
                exitAndPrint(lot,found);
            }

        }
        // ------------------------------------------------------------------ //
        //  3. Summary
        // ------------------------------------------------------------------ //

        printSection("Still Parked");

        for (Ticket ticket : tickets) {
            printStillParked(ticket);
        }

        System.out.println("\n----------------------------------------------");
        input.close();


    }

    // ------------------------------------------------------------------ //
    //  Helpers
    // ------------------------------------------------------------------ //

    /**
     * Assembles and returns a fully configured {@link ParkingLot}.
     *
     * <p>The lot contains 7 spots spread across three size categories. When
     * {@code peakHours} is {@code true} a {@link PeakParkingFeeStrategy} is
     * appended to the fee-strategy chain, raising all fees by 50 %.
     *
     * @param peakHours {@code true} to enable peak-hour pricing
     * @return a ready-to-use {@link ParkingLot}
     */
    private static ParkingLot buildParkingLot(boolean peakHours) {
        // -- Spots --
        Map<VehicleSize, List<ParkingSpot>> spots = new HashMap<>();
        spots.put(VehicleSize.SMALL, new ArrayList<>(List.of(
                new CompactSpot(1),
                new CompactSpot(2)
        )));
        spots.put(VehicleSize.MEDIUM, new ArrayList<>(List.of(
                new RegularSpot(3),
                new RegularSpot(4),
                new HandicappedSpot(5)
        )));
        spots.put(VehicleSize.LARGE, new ArrayList<>(List.of(
                new OversizedSpot(6),
                new OversizedSpot(7)
        )));

        // -- Fee strategies --
        List<ParkingFeeStrategy> strategies = new ArrayList<>();
        strategies.add(new BaseParkingFeeStrategy());
        if (peakHours) {
            strategies.add(new PeakParkingFeeStrategy());
        }

        return new ParkingLot(new ParkingManager(spots), new ParkingFeeCalculator(strategies));
    }

    /**
     * Calls {@link ParkingLot#enterVehicle(Vehicle)}, prints the result, and
     * returns the ticket (which may be {@code null} if the lot is full).
     *
     * @param lot     the parking lot to enter
     * @param vehicle the vehicle attempting to park
     * @return the issued {@link Ticket}, or {@code null}
     */
    private static Ticket enterAndPrint(ParkingLot lot, Vehicle vehicle) {
        Ticket ticket = lot.enterVehicle(vehicle);
        if (ticket == null) {
            System.out.printf("  %-12s | NO SPOT AVAILABLE%n",
                    vehicle.getLicenceNumber());
        } else {
            System.out.printf("  %-12s | Ticket: %-8s | Spot: %d (%s) | Entry: %s%n",
                    vehicle.getLicenceNumber(),
                    ticket.getTicketNumber(),
                    ticket.getParkingSpot().getSpotNumber(),
                    ticket.getParkingSpot().getAcceptedSize(),
                    ticket.getEntryTime().toLocalTime());
        }
        return ticket;
    }

    /**
     * Calls {@link ParkingLot#leaveVehicle(Ticket)}, prints the fee and
     * session duration.
     *
     * @param lot    the parking lot to exit
     * @param ticket the ticket for the session being closed
     */
    private static void exitAndPrint(ParkingLot lot, Ticket ticket) {
        if (ticket == null) return;
        int fee = lot.leaveVehicle(ticket);
        System.out.printf("  %-12s | Ticket: %-8s | Duration: %d min | Fee: KES %d%n",
                ticket.getVehicle().getLicenceNumber(),
                ticket.getTicketNumber(),
                ticket.calculateParkingDuration(),
                fee);
    }

    /**
     * Prints a one-line summary for a vehicle that is still in the lot.
     *
     * @param ticket the open ticket for the parked vehicle (may be {@code null})
     */
    private static void printStillParked(Ticket ticket) {
        if (ticket == null) return;
        System.out.printf("  %-12s | Spot: %d | Since: %s%n",
                ticket.getVehicle().getLicenceNumber(),
                ticket.getParkingSpot().getSpotNumber(),
                ticket.getEntryTime().toLocalTime());
    }

    /** Prints a titled banner. */
    private static void printBanner(String title) {
        System.out.println("==============================================");
        System.out.printf("  %s%n", title);
        System.out.println("==============================================");
    }

    /** Prints a section heading. */
    private static void printSection(String heading) {
        System.out.println("\n--- " + heading + " ---");
    }
}
