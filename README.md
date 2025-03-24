# Hotel Reservation System

## Overview
This is a command-line application that allows users to search for hotel room availability over a specified date range using a **strategy pattern**.

## Prerequisites
Ensure you have the following installed on your system:
- **Java 11** or later
- **Maven 3.6+**

## Build Instructions
1. **Clone the Repository:**
   ```sh
   git clone https://github.com/<your-account>/ReservationSystem.git
   cd ReservationSystem
   ```
2. **Build the Project:**
   ```sh
   mvn clean package
   ```
3. **Generate the Executable JAR:**
   ```sh
   mvn package
   ```
   This will create a JAR file in the `target/` directory:
   ```sh
   target/hotel-reservation-1.0-SNAPSHOT.jar
   ```

## Running the Application
To run the application from the terminal, use:
```sh
java -jar target/hotel-reservation-1.0-SNAPSHOT.jar --hotels hotels.json --bookings bookings.json
```

### Example Commands
#### Check Availability
```sh
Availability(H1 20240901 SGL)
```
#### Search Availability for Next 365 Days
```sh
Search(H1 365 SGL)
```

## Project Structure
```
├── src/main/java/com/staah/reservation
│   ├── Main.java
│   ├── model/
│   │   ├── Hotel.java
│   │   ├── Booking.java
│   ├── strategy/
│   │   ├── QueryStrategy.java
│   │   ├── SearchStrategy.java
│   │   ├── AvailabilityStrategy.java
├── src/main/resources/
│   ├── hotels.json
│   ├── bookings.json
├── pom.xml
```

## Configuration
Modify the `hotels.json` and `bookings.json` files under `src/main/resources/` to update hotel and booking data.


