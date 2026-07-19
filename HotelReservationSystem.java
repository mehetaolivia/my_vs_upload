import java.io.*;
import java.util.*;

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static final String FILE_NAME = "bookings.txt";

    static class Room {
        int roomNo;
        String category;
        double price;
        boolean booked;
        String customerName;

        Room(int roomNo, String category, double price) {
            this.roomNo = roomNo;
            this.category = category;
            this.price = price;
            this.booked = false;
            this.customerName = "";
        }
    }

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 5000));

        loadBookings();

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    bookingDetails();
                    break;
                case 5:
                    saveBookings();
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.booked) {
                System.out.println("Room: " + r.roomNo +
                        " | " + r.category +
                        " | ₹" + r.price);
            }
        }
    }

    static void bookRoom() {
        viewRooms();

        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNo == roomNo && !r.booked) {

                System.out.print("Enter Customer Name: ");
                r.customerName = sc.nextLine();

                System.out.println("Amount to Pay: ₹" + r.price);
                System.out.print("Proceed Payment? (yes/no): ");
                String pay = sc.nextLine();

                if (pay.equalsIgnoreCase("yes")) {
                    r.booked = true;
                    saveBookings();
                    System.out.println("Payment Successful!");
                    System.out.println("Room Booked Successfully!");
                } else {
                    System.out.println("Payment Cancelled.");
                }
                return;
            }
        }

        System.out.println("Room Not Available.");
    }

    static void cancelBooking() {

        System.out.print("Enter Room Number to Cancel: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNo == roomNo && r.booked) {

                r.booked = false;
                r.customerName = "";
                saveBookings();

                System.out.println("Booking Cancelled Successfully.");
                return;
            }
        }

        System.out.println("Booking Not Found.");
    }

    static void bookingDetails() {

        boolean found = false;

        for (Room r : rooms) {

            if (r.booked) {

                found = true;

                System.out.println("-------------------------");
                System.out.println("Room Number : " + r.roomNo);
                System.out.println("Category    : " + r.category);
                System.out.println("Customer    : " + r.customerName);
                System.out.println("Price       : ₹" + r.price);
            }
        }

        if (!found)
            System.out.println("No Bookings Found.");
    }

    static void saveBookings() {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (Room r : rooms) {

                bw.write(r.roomNo + "," +
                        r.category + "," +
                        r.price + "," +
                        r.booked + "," +
                        r.customerName);

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Error Saving File.");
        }
    }

    static void loadBookings() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        try {

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int roomNo = Integer.parseInt(data[0]);

                for (Room r : rooms) {

                    if (r.roomNo == roomNo) {

                        r.booked = Boolean.parseBoolean(data[3]);

                        if (data.length > 4)
                            r.customerName = data[4];
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error Loading File.");
        }
    }
}