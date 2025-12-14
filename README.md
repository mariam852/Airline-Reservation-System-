# ✈️ Airline Reservation and Management System

This project provides a simple system for managing flights and passenger bookings, implemented in Java. It includes both a console-based application and a graphical user interface (GUI) application.

## 📁 File Descriptions

| File Name | Description | Key Classes | Features |
| :--- | :--- | :--- | :--- |
| `AirlineSystemApp.java` | The main **console-based** application. It uses object-oriented principles like inheritance (for Domestic and International flights) and composition/aggregation (in `FlightManager` and `Passenger`). It includes Admin and User roles. | `AirlineSystem`, `Passenger`, `Flight`, `DomesticFlight`, `InternationalFlight`, `FlightManager`, `AirlineSystemApp` | Flight management, passenger booking, cancellation, searching, and report generation (e.g., popular routes). |
| `AirlineSystemGUI.java` | A separate **Graphical User Interface (GUI)** application using Java Swing. This focuses on the `AirlineSystem` class functionality. | `AirlineSystem`, `AirlineSystemGUI` | Adding new flights, viewing flights in a table, and a dedicated form to calculate ticket prices based on flight type, passenger type, and frequent flyer status. |

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or newer installed on your system.

### 1. Console Application (`AirlineSystemApp.java`)

This application allows interaction via the command line with separate menus for Admin and regular Users.

#### How to Run

1.  **Compile:** Open your terminal or command prompt, navigate to the directory containing `AirlineSystemApp.java`, and compile the file:
    ```bash
    javac AirlineSystemApp.java
    ```
    *Note: If you are using a basic editor, you may need to ensure all related classes (like `Flight`, `Passenger`, etc.) are compiled together, though in this case, they are all within one file.*

2.  **Run:** Execute the compiled class:
    ```bash
    java AirlineSystemApp
    ```

3.  **Interaction:** Follow the prompts to enter as an **Admin** or **User**.
    * **Admin Login:** Requires a non-empty name and a 5-digit ID (1-9 only).
    * **Menus:** The Admin menu includes flight management options, while the User menu focuses on booking and viewing information.

### 2. GUI Application (`AirlineSystemGUI.java`)

This application provides a visual interface for managing flights and calculating ticket prices using Java Swing.

#### How to Run

1.  **Compile:** Open your terminal or command prompt, navigate to the directory containing `AirlineSystemGUI.java` and `AirlineSystemApp.java` (as `AirlineSystemGUI` relies on the `AirlineSystem` class defined in the other file), and compile:
    ```bash
    javac *.java
    ```

2.  **Run:** Execute the compiled GUI class:
    ```bash
    java AirlineSystemGUI
    ```

3.  **Interaction:**
    * Use the input fields and **"Add Flight"** button to populate the flight table.
    * Use the **"Book Ticket"** button to open a separate window where you can select a flight, passenger type, and frequent flyer status to calculate the final ticket price.
