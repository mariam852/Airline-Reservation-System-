
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AirlineSystemGUI extends JFrame {
    private ArrayList<AirlineSystem> flights = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable flightTable;

    public AirlineSystemGUI() {
        setTitle("Airline Management System");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layouts
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 5));

        // Input Fields
        JTextField flightNumberField = new JTextField();
        JTextField departureField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField arrivalTimeField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField seatsField = new JTextField();
        JTextField priceField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});

        // Add inputs
        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Departure:"));
        inputPanel.add(departureField);
        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationField);
        inputPanel.add(new JLabel("Arrival Time:"));
        inputPanel.add(arrivalTimeField);
        inputPanel.add(new JLabel("Departure Time:"));
        inputPanel.add(departureTimeField);
        inputPanel.add(new JLabel("Available Seats:"));
        inputPanel.add(seatsField);
        inputPanel.add(new JLabel("Ticket Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Flight Type:"));
        inputPanel.add(typeCombo);

        // Buttons
        JButton addFlightBtn = new JButton("Add Flight");
        JButton bookTicketBtn = new JButton("Book Ticket");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addFlightBtn);
        buttonPanel.add(bookTicketBtn);

        // Table
        tableModel = new DefaultTableModel(new String[]{
            "Flight No", "Departure", "Destination", "Arrival", "Depart", "Seats", "Price", "Type"
        }, 0);
        flightTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(flightTable);

        // Add to panels
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tableScroll, BorderLayout.SOUTH);

        add(mainPanel);

        // Button functionality
        addFlightBtn.addActionListener(e -> {
            try {
                AirlineSystem flight = new AirlineSystem(
                    flightNumberField.getText(),
                    departureField.getText(),
                    destinationField.getText(),
                    arrivalTimeField.getText(),
                    departureTimeField.getText(),
                    Integer.parseInt(seatsField.getText()),
                    Double.parseDouble(priceField.getText()),
                    typeCombo.getSelectedItem().toString()
                );
                flights.add(flight);
                tableModel.addRow(new Object[]{
                    flight.getFlightNumber(), flight.getDeparture(), flight.getDestination(),
                    flight.getArrivalTime(), flight.getDepartureTime(),
                    flight.getAvailableSeats(), flight.getTicketPrice(), flight.getFlightType()
                });
                JOptionPane.showMessageDialog(this, "Flight added successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding flight: " + ex.getMessage());
            }
        });

        bookTicketBtn.addActionListener(e -> showBookingForm());
    }

    private void showBookingForm() {
        JFrame bookingFrame = new JFrame("Book Ticket");
        bookingFrame.setSize(400, 300);
        bookingFrame.setLocationRelativeTo(this);
        bookingFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JComboBox<String> flightSelect = new JComboBox<>();
        for (AirlineSystem f : flights) {
            flightSelect.addItem(f.getFlightNumber());
        }

        JComboBox<String> passengerType = new JComboBox<>(new String[]{"Adult", "Child", "Senior"});
        JCheckBox frequentFlyer = new JCheckBox("Frequent Flyer");
        JLabel priceLabel = new JLabel("Price: ");

        JButton calcBtn = new JButton("Calculate Price");

        calcBtn.addActionListener(e -> {
            String selectedFlight = flightSelect.getSelectedItem().toString();
            for (AirlineSystem flight : flights) {
                if (flight.getFlightNumber().equals(selectedFlight)) {
                    String pType = passengerType.getSelectedItem().toString().toLowerCase();
                    boolean isFF = frequentFlyer.isSelected();
                    double price = flight.calculateTicketPrice(pType, isFF);
                    priceLabel.setText("Price: $" + price);
                    break;
                }
            }
        });

        bookingFrame.add(new JLabel("Select Flight:"));
        bookingFrame.add(flightSelect);
        bookingFrame.add(new JLabel("Passenger Type:"));
        bookingFrame.add(passengerType);
        bookingFrame.add(new JLabel(""));
        bookingFrame.add(frequentFlyer);
        bookingFrame.add(calcBtn);
        bookingFrame.add(priceLabel);

        bookingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AirlineSystemGUI().setVisible(true));
    }
}
