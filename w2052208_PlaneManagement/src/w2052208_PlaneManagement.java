import java.util.Scanner;
import java.io.File;

public class w2052208_PlaneManagement {
    private static final int rows = 4; // Number of rows in the plane
    private static final int[] seats = {14, 12, 12, 14}; // Number of seats in each row

    // Prices of seats
    private static final int[][] prices = {
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 150, 180, 180, 180, 180},
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 150, 180, 180},
            {200, 200, 200, 200, 200, 150, 150, 150, 150, 150, 180, 180},
            {200, 200, 200, 200, 200, 180, 180, 180, 180, 180, 180, 180, 180, 180}
    };

    private static int[][] SeatAvailability;
    private static Ticket[] tickets;  // Array to store ticket
    private static int TotalSales = 0;
    private static int TotalSeats = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        SeatStatus();
        CalculateTotalSeats();
        tickets = new Ticket[TotalSeats];
        Scanner scanner = new Scanner(System.in);
        Menu(scanner);
        scanner.close();
    }

    private static void CalculateTotalSeats() {
        TotalSeats = 0;
        for (int seats : seats) {
            TotalSeats += seats;
        }
    }


    // Display the main menu and get user input
    private static void Menu(Scanner scanner) {
        int choice;
        // Menu loop
        while (true) {
            System.out.println("**************************************");
            System.out.println("*          MENU OPTIONS              *");
            System.out.println("**************************************");
            System.out.println("   1) Buy a seat");
            System.out.println("   2) Cancel a seat");
            System.out.println("   3) Find first available seat");
            System.out.println("   4) Show seating plan");
            System.out.println("   5) Print tickets information and total sales");
            System.out.println("   6) Search ticket");
            System.out.println("   0) Quit");
            System.out.println("**************************************");
            System.out.print("Please select an option: ");

            // Validate user input
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            // switch options based on user choice
            switch (choice) {
                case 1:
                    buy_seat(scanner);
                    break;
                case 2:
                    cancel_seat(scanner);
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket(scanner);
                    break;
                case 0:
                    System.out.println("Exit program");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initializes the seat status array with all seats available
    private static void SeatStatus() {
        SeatAvailability = new int[rows][];  // Initialize seat availability array
        for (int i = 0; i < SeatAvailability.length; i++) {
            SeatAvailability[i] = new int[seats[i]];  // Initialize seats for each row
            for (int j = 0; j < seats[i]; j++) {
                SeatAvailability[i][j] = 0; //  display an available seat
            }
        }
    }

    // Handles the process of buying a seat
    private static void buy_seat(Scanner scanner) {
        // Prompt user for row and seat number
        char row;
        int seatNumber;

        while (true) {
            System.out.print("Please enter row letter (A-D): ");
            try {
                row = Character.toUpperCase(scanner.next().charAt(0));
                int rowIndex = rowindex(row); // Convert row letter to index
                if (rowIndex == -1) {
                    System.out.println("Invalid row. Please enter a letter between A and D.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a letter between A and D.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Please enter seat number: ");
            try {
                seatNumber = scanner.nextInt();
                int rowIndex = rowindex(row);
                if (seatNumber < 1 || seatNumber > seats[rowIndex]) {
                    System.out.println("Invalid seat number. Please enter a valid seat number.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.next();
            }
        }

        // Calculate the index for the chosen seat
        int rowIndex = rowindex(row);  // Convert row letter to index
        int seatIndex = seatNumber - 1;  // Convert seat number to index

        // Check if the seat is already sold
        if (SeatAvailability[rowIndex][seatIndex] == 1) {
            System.out.println("Seat sold.");
            return;
        }

        // get passenger basic information
        System.out.print("Please enter your name: ");
        String name = scanner.next();
        System.out.print("Please enter your surname: ");
        String surname = scanner.next();
        System.out.print("Please enter your email: ");
        String email = scanner.next();

        // Create a Person object and calculate the price of the ticket
        Person person = new Person(name, surname, email);
        int price = prices[rowIndex][seatIndex];

        // Create a Ticket object and add it to the tickets array
        Ticket ticket = new Ticket(row, seatNumber, price, person);
        // Add the Ticket object to the tickets array at the calculated index
        tickets[rowIndex * seats[rowIndex] + seatIndex] = ticket;

        // Update seat status and total sales
        SeatAvailability[rowIndex][seatIndex] = 1; // dispaly a sold seat
        TotalSales += price;

        System.out.println("Ticket purchased successfully.");

        ticket.save();

    }
    private static void cancel_seat(Scanner scanner) {
        // Prompt user for row and seat number
        char row;
        int seatNumber;

        while (true) {
            System.out.print("Please enter row letter (A-D): ");
            try {
                row = Character.toUpperCase(scanner.next().charAt(0));
                int rowIndex = rowindex(row);
                if (rowIndex == -1) {
                    System.out.println("Invalid row. Please enter a letter between A and D.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a letter between A and D.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Please enter seat number: ");
            try {
                seatNumber = scanner.nextInt();
                int rowIndex = rowindex(row);
                if (seatNumber < 1 || seatNumber > seats[rowIndex]) {
                    System.out.println("Invalid seat number. Please enter a valid seat number.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.next();
            }
        }

        // Calculate the index for the chosen seat
        int rowIndex = rowindex(row);
        int seatIndex = seatNumber - 1;

        // Check if the seat is already available
        if (SeatAvailability[rowIndex][seatIndex] == 0) {
            System.out.println("Seat already available.");
            return;
        }

        // Update seat status and total sales
        SeatAvailability[rowIndex][seatIndex] = 0; // 0 indicates an available seat
        TotalSales -= prices[rowIndex][seatIndex];

        // Clear ticket information
        tickets[rowIndex * seats[rowIndex] + seatIndex] = null;

        String filename = row + String.valueOf(seatNumber) + ".txt";
        File deleted = new File(filename);

        if (deleted.delete()) {
            System.out.println("Seat canceled successfully.");
        } else {
            System.out.println("Failed to cancel the seat.");
        }

    }



    // Finds the first available seat and prints its location
    private static void find_first_available() {
        for (int i = 0; i < SeatAvailability.length; i++) {
            for (int j = 0; j < SeatAvailability[i].length; j++) {
                if (SeatAvailability[i][j] == 0) {
                    System.out.println("First available seat: " + (char) ('A' + i) + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }



    // Displays the seating plan with seat availability
    private static void show_seating_plan() {
        System.out.println("**** Seating Plan ****");
        for (int i = 0; i < rows; i++) {
            // Determine the row label (A, B, C, D) based on the index
            char row = (char) ('A' + i);
            System.out.print(row + " ");
            for (int j = 0; j < SeatAvailability[i].length; j++) {
                // Print 'O' if the seat is available (0), otherwise print 'X' for sold seats (1)
                if (SeatAvailability[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }


    // Prints information about all sold tickets and the total sales
    private static void print_tickets_info() {
        System.out.println("===== Tickets Information =====");
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.TicketInfo();
            }
        }
        // Print total sales
        System.out.println("Total sales: £" + TotalSales);
    }

    // Method to search for a ticket
    private static void search_ticket(Scanner scanner) {
        // Prompt user for row and seat number
        char row;
        int seatNumber;

        while (true) {
            System.out.print("Please enter row letter (A-D): ");

            try {
                row = Character.toUpperCase(scanner.next().charAt(0));
                int rowIndex = rowindex(row);
                if (rowIndex == -1) {
                    System.out.println("Invalid row. Please enter a letter between A and D.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a letter between A and D.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Enter seat number: ");
            try {
                seatNumber = scanner.nextInt();
                int rowIndex = rowindex(row);
                if (seatNumber < 1 || seatNumber > seats[rowIndex]) {
                    System.out.println("Invalid seat number. Please enter a valid seat number.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.next();
            }
        }


        int rowIndex = rowindex(row);
        int seatIndex = seatNumber - 1;

        // Check if the seat is available or sold
        if (SeatAvailability[rowIndex][seatIndex] == 0) {
            System.out.println("This seat is available.");
        } else {
            Ticket ticket = tickets[rowIndex * seats[rowIndex] + seatIndex];
            System.out.println("Ticket Information:");
            ticket.TicketInfo();
        }
    }

    // Convert row letter to index
    private static int rowindex(char row) {
        switch (row) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            default:
                return -1;
        }
    }
}