import java.util.ArrayList;
import java.util.Scanner;

class Reservation {
    private int pnr;
    private String name;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String source;
    private String destination;

    public Reservation(int pnr, String name, String trainNumber, String classType, String dateOfJourney, String source, String destination) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.source = source;
        this.destination = destination;
    }

    public int getPNR() {
        return pnr;
    }

    public String toString() {
        return "PNR: " + pnr + ", Name: " + name + ", Train Number: " + trainNumber + ", Class Type: " + classType
                + ", Date of Journey: " + dateOfJourney + ", Source: " + source + ", Destination: " + destination;
    }
}

public class OnlineReservationSystem {
    private static int pnrCounter = 1;
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (true) {
            System.out.println("\nOnline Reservation System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                loggedIn = login(scanner);
            } else if (choice == 2) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please select 1 or 2.");
            }

            if (loggedIn) {
                while (true) {
                    System.out.println("\nReservation System");
                    System.out.println("1. Make a Reservation");
                    System.out.println("2. Cancel Reservation");
                    System.out.println("3. Logout");
                    System.out.print("Select an option: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        makeReservation(scanner);
                    } else if (choice == 2) {
                        cancelReservation(scanner);
                    } else if (choice == 3) {
                        System.out.println("Logged out.");
                        loggedIn = false;
                        break;
                    } else {
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                }
            }
        }
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter your login ID: ");
        String loginID = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Simple authentication, assuming the loginID and password match
        return true;
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("Enter Reservation Details:");
        String name, trainNumber, classType, dateOfJourney, source, destination;

        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Train Number: ");
        trainNumber = scanner.nextLine();

        System.out.print("Class Type: ");
        classType = scanner.nextLine();

        System.out.print("Date of Journey: ");
        dateOfJourney = scanner.nextLine();

        System.out.print("Source: ");
        source = scanner.nextLine();

        System.out.print("Destination: ");
        destination = scanner.nextLine();

        int newPNR = pnrCounter++;
        Reservation reservation = new Reservation(newPNR, name, trainNumber, classType, dateOfJourney, source, destination);
        reservations.add(reservation);

        System.out.println("Reservation successful. Your PNR number is: " + newPNR);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR number for cancellation: ");
        int pnrToCancel = scanner.nextInt();
        boolean canceled = false;

        for (Reservation reservation : reservations) {
            if (reservation.getPNR() == pnrToCancel) {
                System.out.println("Reservation details:\n" + reservation);
                System.out.print("Do you want to confirm cancellation? (Y/N): ");
                String confirmation = scanner.next();
                if (confirmation.equalsIgnoreCase("Y")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation with PNR " + pnrToCancel + " is canceled.");
                }
                canceled = true;
                break;
            }
        }

        if (!canceled) {
            System.out.println("No reservation found with PNR " + pnrToCancel);
        }
    }
}
