import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner; 

// Main AirlineSystem class
class AirlineSystem {
    private String flightNumber;
    private String departure;
    private String destination;
    private String arrivalTime;
    private String departureTime;
    private int availableSeats;
    private double ticketPrice;
    private String flightType; // economy, business, first class

    // Constructor
    public AirlineSystem(String flightNumber, String departure, String destination,
                         String arrivalTime, String departureTime, int availableSeats,
                         double ticketPrice, String flightType) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.flightType = flightType;
    }

    // Getters and setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    // Calculate ticket price based on passenger type and frequent flyer status   //Mariam Walid 
    public double calculateTicketPrice(String passengerType, boolean isFrequentFlyer) {
        double price = ticketPrice;                   //Use Aggregation or Composition (takhdo passengerType mn class Passenger)
        if ("child".equalsIgnoreCase(passengerType)) {
            price *= 0.5; // 50% discount for children
        } else if ("senior".equalsIgnoreCase(passengerType)) {
            price *= 0.7; // 30% discount for seniors
        }

        if (isFrequentFlyer) {
            price *= 0.9; // 10% discount for frequent flyers
        }

        return price;
    }
}

// Passenger clas
class Passenger {
    private String passengerName;
    private String passengerID;
    private String flightNumber; // Current flight the passenger is booked on
    private List<String> bookingHistory;
    private int frequentFlyerPoints;

    // Constructor
    public Passenger(String passengerName, String passengerID, String flightNumber) {
        this.passengerName = passengerName;
        this.passengerID = passengerID;
        this.flightNumber = flightNumber;
        this.bookingHistory = new ArrayList<>();
        this.frequentFlyerPoints = 0;

        // Add initial flight to booking history
        if (flightNumber != null && !flightNumber.isEmpty()) {
            bookingHistory.add(flightNumber);
        }
    }

    // Getter and setter for passengerName
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    // Getter and setter for passengerID
    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    // Getter and setter for flightNumber
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    // Getter for frequentFlyerPoints
    public int getFrequentFlyerPoints() {
        return frequentFlyerPoints;
    }

    // Update frequent flyer points
    public void updateFrequentFlyerPoints(int miles) {
        this.frequentFlyerPoints += miles;
    }

    // Getter for booking history
    public List<String> getBookingHistory() {
        return new ArrayList<>(bookingHistory); // Return a copy for encapsulation
    }

    // Add a booking to history
    public void addBooking(String flightNumber) {
        if (flightNumber != null && !flightNumber.isEmpty()) {
            bookingHistory.add(flightNumber);
        }
    }

    // View booking history
    public void viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("Booking History:");
            for (String flight : bookingHistory) {
                System.out.println("- " + flight);
            }
        }
    }
}


// Base Flight class
class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private double baseCost;

    public Flight(String flightNumber, String origin, String destination, double baseCost) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.baseCost = baseCost;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void displayDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Base Cost: $" + baseCost);
    }
}

// DomesticFlight class inheriting from Flight
class DomesticFlight extends Flight {
    private boolean visaRequired;

    public DomesticFlight(String flightNumber, String origin, String destination, double baseCost) {
        super(flightNumber, origin, destination, baseCost);
        this.visaRequired = false; // Visa is generally not required for domestic flights.
    }

    public boolean isVisaRequired() {
        return visaRequired;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Visa Required: " + (visaRequired ? "Yes" : "No"));
        System.out.println("Cost (Domestic Discount): $" + (getBaseCost() * 0.9)); // 10% discount
    }
}

// InternationalFlight class inheriting from Flight
class InternationalFlight extends Flight {
    private boolean visaRequired;
    private String visaDetails;

    public InternationalFlight(String flightNumber, String origin, String destination, double baseCost, boolean visaRequired, String visaDetails) {
        super(flightNumber, origin, destination, baseCost);
        this.visaRequired = visaRequired;
        this.visaDetails = visaDetails;
    }

    public boolean isVisaRequired() {
        return visaRequired;
    }

    public String getVisaDetails() {
        return visaDetails;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Visa Required: " + (visaRequired ? "Yes" : "No"));
        if (visaRequired) {
            System.out.println("Visa Details: " + visaDetails);
        }
        System.out.println("Cost (International): $" + (getBaseCost() * 1.2)); // 20% additional cost
    }
}

// FlightManager class
class FlightManager {
    private List<Flight> flights;
    
    private ArrayList<Passenger> passengers;

    public FlightManager() {
        flights = new ArrayList<>();
        passengers = new ArrayList<>();
        
    }
    
    public Map<String, Integer> calculatePopularRoutes() {
    Map<String, Integer> routePopularity = new HashMap<>();
    for (Flight flight : flights) {
        String route = flight.getOrigin() + " to " + flight.getDestination();
        routePopularity.put(route, routePopularity.getOrDefault(route, 0) + 1);
    }
    return routePopularity;
    }

    
    public Flight findFlightByNumber(String flightNumber) {
    for (Flight flight : flights) {
        if (flight.getFlightNumber().equals(flightNumber)) {
            return flight;
        }
    }
    return null;
   }
   
   public Passenger findPassengerByID(String passengerID) {
    for (Passenger passenger : passengers) {
        if (passenger.getPassengerID().equals(passengerID)) {
            return passenger;
        }
    }
    return null;
   }


    public void addFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Flight added: " + flight.getFlightNumber());
    }

  public List<Flight> viewFlights() {
    return flights;  // Assuming 'flights' is a List<Flight> in your class
}


   public boolean bookSeat(String flightNumber, Passenger passenger) {
    for (Flight flight : flights) {
        if (flight.getFlightNumber().equals(flightNumber)) {
            passenger.addBooking(flightNumber);  // Add flight to passenger's booking history
            System.out.println("Seat booked for " + passenger.getPassengerName() + " on flight " + flightNumber);
            return true;  // Return true if the seat was booked successfully
        }
    }
    System.out.println("Flight " + flightNumber + " not found.");
    return false;  // Return false if the flight was not found
}
public boolean cancelReservation(String passengerID) {
    for (Passenger passenger : passengers) {
        if (passenger.getPassengerID().equals(passengerID)) {
            passenger.getBookingHistory().clear();  // Clear booking history to cancel the reservation
            System.out.println("Reservation canceled for " + passenger.getPassengerName());
            return true;  // Return true if the reservation was canceled successfully
        }
    }
    System.out.println("Passenger not found.");
    return false;  // Return false if the passenger is not found
}


    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        System.out.println("Passenger added: " + passenger.getPassengerName());
    }
} 



// Main 
public class AirlineSystemApp {
 
   private static String currentRole = "admin";
   public static void main(String[] args) {
    FlightManager flightManager = new FlightManager();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    System.out.println("Welcome to the Airline Reservation System!");

    // Determine if the user is admin or a regular user
    String userType = "";
    while (true) {
        System.out.print("Are you an Admin or a User? (Enter 'admin' or 'user'): ");
        userType = scanner.nextLine().trim().toLowerCase();
        if (userType.equals("admin") || userType.equals("user")) {
            break;
        } else {
            System.out.println("Invalid input. Please enter 'admin' or 'user'.");
        }
    }

    boolean isAdmin = false;
    if (userType.equals("admin")) {
        isAdmin = true;
    
        // Admin login (enter name and admin ID)
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine().trim();
    
        // Validate that the admin name is not empty
       while (adminName.isEmpty() || !adminName.matches("[a-zA-Z]+")) {
            if (adminName.isEmpty()) {
                System.out.println("Admin name cannot be empty. Please enter a valid name.");
            } else {
                System.out.println("Admin name must contain only letters. Please enter a valid name.");
            } System.out.print("Enter Admin Name: ");
            adminName = scanner.nextLine().trim();
        }
    
    System.out.print("Enter Admin ID (5 digits between 1-9): ");
    String adminID = scanner.nextLine().trim();

    // Validate that the admin ID consists of exactly 5 digits between 1 and 9
    while (!adminID.matches("[1-9]{5}")) {
        System.out.println("Invalid Admin ID. It must consist of exactly 5 digits, each between 1 and 9.");
        System.out.print("Enter Admin ID (5 digits between 1-9): ");
        adminID = scanner.nextLine().trim();
    }

    // Assuming admin validation is done here (you can add your own logic for validation)
    System.out.println("Welcome, Admin " + adminName + "!");
} else {
    System.out.println("Welcome, User!");
}


    while (!exit) {
        System.out.println("\n--- Airline System Menu ---");

        if (isAdmin) {
            // Admin menu (only relevant options available)
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Search Flights");
            System.out.println("6. View Passenger Booking History");
            System.out.println("7. Generate Reports");
            System.out.println("8. View Flight Details");
            System.out.println("9. EXIT "); 
            System.out.println("10. switch to USER "); 
            
        } else {
            // Regular user menu (case 1 excluded)
            System.out.println("2. View Flights");
            System.out.println("3. Search Flights");
            System.out.println("4. Book Seat");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. View Passenger Booking History");
            System.out.println("7. Generate Reports");
            System.out.println("8. View Flight Details");
            System.out.println("9. Exit");
        }

        int choice = -1;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 10) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        switch (choice) {
            case 1:
                if (isAdmin) {
                    addFlightMenu(scanner, flightManager); // Only for Admin
                } else {
                    System.out.println("Invalid choice. This option is only available for Admin.");
                }
                break;
            case 2: 
                flightManager.viewFlights();
                break;
            case 3: 
                searchFlightMenu(scanner, flightManager);
                break;
            case 4: 
                if (!isAdmin) {
                    bookSeatMenu(scanner, flightManager); // Only for regular users
                } else {
                    System.out.println("Invalid choice. This option is only available for Users.");
                }
                break;
            case 5: 
                if (!isAdmin) {
                    cancelReservationMenu(scanner, flightManager); // Only for regular users
                } else {
                    System.out.println("Invalid choice. This option is only available for Users.");
                }
                break;
            case 6: 
                if (isAdmin) {
                    viewBookingHistoryMenu(scanner, flightManager); // Only for Admin
                } else {
                    viewBookingHistoryMenu(scanner, flightManager); // Available to Users as well
                }
                break;
            case 7: 
                if (isAdmin) {
                    generateReports(flightManager); // Only for Admin
                } else {
                    generateReports(flightManager); // Available to Users as well
                }
                break;
            case 8: 
                viewFlightDetailsMenu(scanner, flightManager);
                break;
            case 9: {
                exit = true;
                System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                break;
            } 
            case 10: { 
                 System.out.println("2. View Flights");
            System.out.println("3. Search Flights");
            System.out.println("4. Book Seat");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. View Passenger Booking History");
            System.out.println("7. Generate Reports");
            System.out.println("8. View Flight Details");
            System.out.println("9. Exit");
                  
                   int choice2 = -1;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 10) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
                  switch (choice) {
            
            case 2: 
                flightManager.viewFlights();
                break;
            case 3: 
                searchFlightMenu(scanner, flightManager);
                break;
            case 4: 
                
                    bookSeatMenu(scanner, flightManager); // Only for regular users
                 
                break;
            case 5: 
               
                    cancelReservationMenu(scanner, flightManager); // Only for regular users
                
                break;
            case 6: 
                
                    viewBookingHistoryMenu(scanner, flightManager); // Only for Admin
               
                break;
            case 7: 
                
                    generateReports(flightManager); // Available to Users as well
                
                break;
            case 8: 
                viewFlightDetailsMenu(scanner, flightManager);
                break;
            case 9: {
                exit = true;
                System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                break;
            } 
            } 
            
        
        } default: 
                System.out.println("Invalid choice. Try again.");}

        if (!exit) {
            System.out.print("\nWould you like to return to the main menu? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            while (!answer.equals("yes") && !answer.equals("no")) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                System.out.print("Would you like to return to the main menu? (yes/no): ");
                answer = scanner.nextLine().trim().toLowerCase();
            }
            if (!answer.equals("yes")) {
                exit = true;
                System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
            }
        }
    }
} 
 public static void addFlightMenu(Scanner scanner, FlightManager flightManager) {
     String[] popularCountries = {
        "United States", "Canada", "Mexico", "United Kingdom", "France", "Germany", "Italy", "Spain",
        "China", "India", "Japan", "South Korea", "Australia", "Brazil", "Argentina", "South Africa",
        "Russia", "Netherlands", "Switzerland", "Sweden", "Norway", "Denmark", "Finland", "Ireland",
        "New Zealand", "Singapore", "Malaysia", "Thailand", "Vietnam", "Philippines", "Turkey", "Greece",
        "Egypt", "Saudi Arabia", "United Arab Emirates", "Qatar", "Morocco", "Indonesia", "Pakistan", "Bangladesh", "Hurghada", "Sharm elsheikh"
    };
    String flightNumber, origin, destination, international;
    double baseCost;

    // Get valid flight number
    System.out.print("Enter Flight Number: ");
    flightNumber = scanner.nextLine().trim();
    while (true) {
        System.out.print("Enter Flight Number (numeric): ");
        flightNumber = scanner.nextLine().trim();
        if (flightNumber.matches("\\d+")) {
            break;
        } else {
            System.out.println("Invalid flight number. It must be numeric. Please try again.");
        }
    }

    
     while (true) {
        System.out.print("Enter Origin (country): ");
        origin = scanner.nextLine().trim();
        if (isValidCountry(origin, popularCountries)) {
            break;
        } else {
            System.out.println("Invalid origin. It must be a valid country.");
        }
    }
    while (true) {
        System.out.print("Enter Destination (country): ");
        destination = scanner.nextLine().trim();
        if (isValidCountry(destination, popularCountries)) {
            break;
        } else {
            System.out.println("Invalid destination. It must be a valid country.");
        }
    }
    
    // Validate base cost (must be a positive double or integer)
    while (true) {
        System.out.print("Enter Base Cost (numeric): ");
        String baseCostInput = scanner.nextLine().trim();
        try {
            baseCost = Double.parseDouble(baseCostInput);
            if (baseCost > 0) {
                break;
            } else {
                System.out.println("Base Cost must be positive. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Base Cost must be a numeric value. Please try again.");
        }
    }

      while (true) {
        System.out.print("Is this an international flight? (yes/no): ");
        international = scanner.nextLine().trim().toLowerCase();
        if (international.equals("yes") || international.equals("no")) {
            break;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }

    if (international.equals("yes")) {
        // Get visa requirement
        boolean visaRequired;
        while (true) {
            System.out.print("Is a visa required? (yes/no): ");
            String visaRequiredInput = scanner.nextLine().trim().toLowerCase();
            if (visaRequiredInput.equals("yes") || visaRequiredInput.equals("no")) {
                visaRequired = visaRequiredInput.equals("yes");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
         String visaDetails;
        while (true) {
            System.out.print("Enter Visa Details: ");
            visaDetails = scanner.nextLine().trim();
            if (!visaDetails.isEmpty()) {
                break;
            } else {
                System.out.println("Visa Details cannot be empty. Please try again.");
            }
        }

        // Add international flight
        flightManager.addFlight(new InternationalFlight(flightNumber, origin, destination, baseCost, visaRequired, visaDetails));
    } else {
        // Add domestic flight
        flightManager.addFlight(new DomesticFlight(flightNumber, origin, destination, baseCost));
    }
   } 
   public static boolean isValidCountry(String country, String[] popularCountries) {
    for (String validCountry : popularCountries) {
        if (validCountry.equalsIgnoreCase(country)) {
            return true;
        }
    }
    return false;
}


// Search Flight Menu
 private static void searchFlightMenu(Scanner scanner, FlightManager flightManager) {
    String flightNumber;

    // Validate flight number (must be numeric)
    while (true) {
        System.out.print("Enter Flight Number (numeric): ");
        flightNumber = scanner.nextLine().trim();
        if (flightNumber.matches("\\d+")) { // Ensures input contains only digits
            break;
        } else {
            System.out.println("Invalid flight number. It must be numeric. Please try again.");
        }
    }

    // Search for the flight
    Flight flight = flightManager.findFlightByNumber(flightNumber);
    if (flight != null) {
        flight.displayDetails(); // Display flight details if found
    } else {
        System.out.println("Flight not found."); // Inform user if flight doesn't exist
    }
}

   

    // Book Seat Menu
   private static boolean bookSeatMenu(Scanner scanner, FlightManager flightManager) {
    String flightNumber;
    String passengerName;
    String passengerID;

    // Validate flight number (must be numeric)
    while (true) {
        System.out.print("Enter Flight Number (numeric): ");
        flightNumber = scanner.nextLine().trim();
        if (flightNumber.matches("\\d+")) { // Ensures input contains only digits
            break;
        } else {
            System.out.println("Invalid flight number. It must be numeric. Please try again.");
        }
    }

    // Validate passenger name (alphabetic characters only, with spaces allowed)
    while (true) {
        System.out.print("Enter Passenger Name (letters only): ");
        passengerName = scanner.nextLine().trim();
        if (passengerName.matches("[a-zA-Z ]+")) { // Ensures name contains only letters and spaces
            break;
        } else {
            System.out.println("Invalid passenger name. It must contain only letters. Please try again.");
        }
    }

    // Validate passenger ID (alphanumeric)
    while (true) {
        System.out.print("Enter Passenger ID (alphanumeric): ");
        passengerID = scanner.nextLine().trim();
        if (passengerID.matches("[a-zA-Z0-9]+")) { // Allows letters and digits
            break;
        } else {
            System.out.println("Invalid Passenger ID. It must be alphanumeric. Please try again.");
        }
    }

    // Check if passenger already exists
    Passenger passenger = flightManager.findPassengerByID(passengerID);
    if (passenger == null) {
        // Create a new passenger if not found
        passenger = new Passenger(passengerName, passengerID, flightNumber);
        flightManager.addPassenger(passenger);
    } else if (!passenger.getFlightNumber().equals(flightNumber)) {
        System.out.println("Passenger already exists and is booked on another flight.");
        return false;
    }

    // Attempt to book a seat
    if (flightManager.bookSeat(flightNumber, passenger)) {
        System.out.println("Seat successfully booked for " + passengerName + ".");
        return true; // Booking successful
    } else {
        System.out.println("Seat booking failed. Flight may be full or not found.");
        return false; // Booking failed
    }
}


    // Cancel Reservation Menu
private static void cancelReservationMenu(Scanner scanner, FlightManager flightManager) {
    String passengerID;

    // Validate Passenger ID (alphanumeric)
    while (true) {
        System.out.print("Enter Passenger ID (alphanumeric): ");
        passengerID = scanner.nextLine().trim();
        if (passengerID.matches("[a-zA-Z0-9]+")) { // Ensures ID is alphanumeric
            break;
        } else {
            System.out.println("Invalid Passenger ID. It must be alphanumeric. Please try again.");
        }
    }

    // Attempt to cancel the reservation and check the result
    boolean success = flightManager.cancelReservation(passengerID);
    if (success) {
        System.out.println("Reservation successfully canceled for Passenger ID: " + passengerID);
    } else {
        System.out.println("No reservation found for Passenger ID: " + passengerID);
    }
}



   // View Booking History Menu
private static void viewBookingHistoryMenu(Scanner scanner, FlightManager flightManager) {
    String passengerID;

    // Validate Passenger ID (alphanumeric)
    while (true) {
        System.out.print("Enter Passenger ID (alphanumeric): ");
        passengerID = scanner.nextLine().trim();
        if (passengerID.matches("[a-zA-Z0-9]+")) { // Ensures ID is alphanumeric
            break;
        } else {
            System.out.println("Invalid Passenger ID. It must be alphanumeric. Please try again.");
        }
    }

    // Find passenger by ID
    Passenger passenger = flightManager.findPassengerByID(passengerID);
    if (passenger != null) {
        // Display booking history if passenger is found
        passenger.viewBookingHistory();
    } else {
        // Inform user if passenger is not found
        System.out.println("Passenger not found.");
    }
}


    // Generate Reports
private static void generateReports(FlightManager flightManager) {
    System.out.println("===== Daily Report =====");
    // Display all flights for the day
    if (flightManager.viewFlights().isEmpty()) {
        System.out.println("No flights available for today.");
    }

    System.out.println("\n===== Popular Routes =====");
    Map<String, Integer> routePopularity = flightManager.calculatePopularRoutes();

    if (routePopularity.isEmpty()) {
        System.out.println("No route data available.");
    } else {
        // Sort the popular routes by the number of bookings in descending order
        routePopularity.entrySet()
            .stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort descending
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " bookings"));
    }

    System.out.println("\n===== End of Report =====");
}

// View Flight Details
private static void viewFlightDetailsMenu(Scanner scanner, FlightManager flightManager) {
    String flightNumber;

    // Validate flight number (must be numeric)
    while (true) {
        System.out.print("Enter Flight Number (numeric): ");
        flightNumber = scanner.nextLine().trim();
        if (flightNumber.matches("\\d+")) { // Ensures input contains only digits
            break;
        } else {
            System.out.println("Invalid flight number. It must be numeric. Please try again.");
        }
    }

    // Find the flight by number
    Flight flight = flightManager.findFlightByNumber(flightNumber);
    if (flight != null) {
        // Display flight details if found
        flight.displayDetails();
    } else {
        // Inform user if flight doesn't exist
        System.out.println("Flight not found.");
    }
}
}
 