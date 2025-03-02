# Coworking App

A Spring Boot Java application that allows users to manage a coworking space (hot desks, offices, and meeting rooms) following DDD and a hexagonal architecture.

---

## Overview

The Coworking App provides REST endpoints to manage coworking spaces and reservations. The application adheres to a clean hexagonal architecture where core business logic is isolated from infrastructure and UI layers.

**Key Use Cases:**

- **Register HotDesk**  
  Register a hot desk with a unique number. **Endpoint:** `/registerHotDesk`

- **Register Office**  
  Register an office with a unique number and an optional lease period and status. Default values are applied when not provided. **Endpoint:** `/registerOffice`

- **Register MeetingRoom**  
  Register a meeting room with a unique name and valid capacity. **Endpoint:** `/registerMeetingRoom`

- **Reserve MeetingRoom**  
  Reserve a meeting room for a specific date, hour, and duration. When a reservation is made, the system attempts to assign a complimentary hot desk for that day (if available). **Endpoint:** `/reserveMeetingRoom`

- **Reserve HotDesk**  
  Reserve a hot desk for a specific user and date. **Endpoint:** `/reserveHotDesk`

---

## Architecture

The application is built using a **hexagonal (ports and adapters) architecture** which separates the following layers:

- **Domain:**  
  Contains core business logic and domain entities (e.g., `HotDesk`, `Office` and `MeetingRoom`).

- **Application:**  
  Implements use case command handlers (e.g., `RegisterHotDeskCommandHandler`, `ReserveMeetingRoomCommandHandler`) that orchestrate domain operations.

- **Infrastructure:**  
  Provides in-memory repository implementations (e.g., `InMemoryHotDeskRepository`, `InMemoryMeetingRoomRepository`) for persistence.

- **UI:**  
  Contains REST controllers that expose endpoints and convert incoming JSON requests into domain commands.

---

## Endpoints

### 1. Register HotDesk

- **URL:** `/registerHotDesk`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "number": 1
  }
    ```
  
### 2. Register Office

- **URL:** `/registerOffice`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "number": 1,
    "leasePeriod": 6,
    "status": "Active"
  }
  ```
  
### 3. Register MeetingRoom

- **URL:** `/registerMeetingRoom`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "name": "Boardroom",
    "capacity": 10
  }
  ```
  
### 4. Reserve MeetingRoom

- **URL:** `/reserveMeetingRoom`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "meetingRoomId": "Boardroom-Id",
    "date": "2022-12-01",
    "hour": 10,
    "duration": 2,
    "userId": "User-Id"
  }
  ```

### 5. Reserve HotDesk

- **URL:** `/reserveHotDesk`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "userId": "User-Id",
    "date": "2022-12-01"
  }
  ```

---

## Running the Application

1. Clone the repository:
   ```bash
   git clone
    ```
   
2. Navigate to the project directory:
    ```bash
    cd coworking-app
    ```
   
3. Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
   
4. The application will start on port `8080`. You can access the endpoints using a REST client (e.g., Postman).

---

## Testing

The application includes unit tests for domain entities and use case command handlers. You can run the tests using Maven:

```bash
mvn test
```

---

## Author

👤 **Pol Piñol Castuera**

- GitHub: [@PolPinol](https://github.com/PolPinol)
- LinkedIn: [Pol Piñol Castuera](https://www.linkedin.com/in/polpinol/)

---

