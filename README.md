# Coworking App

A Spring Boot Java application that allows users to manage coworking spaces (hot desks, offices, and meeting rooms) and memberships following Domain-Driven Design (DDD) and a hexagonal architecture.

---

## Overview

The Coworking App provides REST endpoints to manage coworking spaces, reservations, and memberships. The application adheres to a clean hexagonal architecture where core business logic is isolated from infrastructure and UI layers.

The app is organized into two bounded contexts:

- **Core:**  
  Focused on managing coworking spaces and reservations.  
  **Key Use Cases:**
  - **Register HotDesk**: Register a hot desk with a unique number. **Endpoint:** `/registerHotDesk`
  - **Register Office**: Register an office with a unique number and an optional lease period and status. Default values are applied when not provided. **Endpoint:** `/registerOffice`
  - **Register MeetingRoom**: Register a meeting room with a unique name and valid capacity. **Endpoint:** `/registerMeetingRoom`
  - **Reserve MeetingRoom**: Reserve a meeting room for a specific date, hour, and duration. When a reservation is made, the system attempts to assign a complimentary hot desk for that day (if available). **Endpoint:** `/reserveMeetingRoom`
  - **Reserve HotDesk**: Reserve a hot desk for a specific user and date. **Endpoint:** `/reserveHotDesk`

- **Membership:**  
  Handles user memberships and package subscriptions using a **CQRS** approach and **event sourcing**.  
  All write operations (commands) generate domain events that are persisted in an **event store** and later used to update the read model.  
  **Key Use Cases:**
  - **Create Membership**: Create a membership for a user. **Endpoint:** `/createMembership`
  - **Register Package**: Subscribe a membership to a package (set of pre-paid credits with a validity period). **Endpoint:** `/registerPackage`
  - **Get Full Membership Summary**: Retrieve a summary of a membership including total active credits. **Endpoint:** `/memberships/{userId}`

---

## Bounded Contexts

- **Core:**  
  Manages coworking spaces and reservations through domain entities like `HotDesk`, `Office`, and `MeetingRoom`, along with corresponding reservation functionalities.

- **Membership:**  
  Manages user memberships and package subscriptions. This context provides endpoints to create memberships, subscribe to packages (including handling credits and validity periods), and obtain a summarized view of the membership state.

---

## Architecture

The application is built using a **hexagonal (ports and adapters) architecture** which separates the following layers:

- **Domain:**  
  Contains core business logic and domain entities (e.g., `HotDesk`, `Office`, `MeetingRoom`, and membership-related entities).

- **Application:**  
  Implements use case command handlers and query handlers that orchestrate domain operations.  
  In the Membership bounded context, this includes:
  - **Command Handlers** (e.g., `CreateMembershipCommandHandler`, `RegisterPackageCommandHandler`) that process commands, generate domain events, and persist these events to an **event store**.
  - **Query Handlers** (e.g., `GetFullMembershipSummaryQueryHandler`) that serve read operations by querying a dedicated read model, following the **CQRS** pattern.

- **Infrastructure:**  
  Provides in-memory repository implementations (e.g., `InMemoryHotDeskRepository`, `InMemoryMeetingRoomRepository`, `InMemoryMembershipRepository`, etc.) for persistence.

- **UI:**  
  Contains REST controllers that expose endpoints and convert incoming JSON requests into domain commands.

Each bounded context encapsulates its own domain and application layers while sharing a common infrastructure and UI layer where applicable.

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

### 6. Create Membership

- **URL:** `/createMembership`
- **Method:** `POST`
- **Payload Example:**
  ```json
  {
    "userId": "User-Id"
  }
  ```
  
### 7. Register Package

- **URL:** `/registerPackage`
- **Method:** `POST`
- **Payload Example:**
  ```json
   {
      "membershipId": "membership-001",
      "credits": 10,
      "year": 2025,
      "month": 2
   }
  ```

### 8. Get Full Membership Summary

- **URL:** `/memberships/{userId}`
- **Method:** `GET`
- **Response Example:**
  ```json
   {
      "id": "membership-001",
      "user_id": "123e4567-e89b-12d3-a456-426614174000",
      "total_credits": 100
   }
  ```

---

## Running the Application

To run the application using Maven from the command line, follow these steps. The project now has **two bounded contexts** (Core and Membership), each with its own main class and default server port. By default:
- **Core** runs on port `8080`.
- **Membership** runs on port `8081`.

You can override these defaults either by editing the respective `application.properties` files or by using the `-Dserver.port` parameter on the command line.

1. **Clone the repository:**

```bash
git clone <repository-url>
```

2. **Navigate to the project directory:**

```bash
cd coworking-app
```

### Running the Core Bounded Context

Run the Core context with its default port (8080):

```bash
mvn spring-boot:run -Dspring-boot.run.main-class=com.ppinol.coworkingapp.core.CoworkingAppCoreBoundedContext -Dspring-boot.run.profiles=core
```

### Running the Membership Bounded Context

Run the Membership context with its default port (8081):

```bash
mvn spring-boot:run -Dspring-boot.run.main-class=com.ppinol.coworkingapp.membership.CoworkingAppMembershipBoundedContext -Dspring-boot.run.profiles=membership
```

### Accessing the Endpoints

- **Core Endpoints** (running on port `8080` by default):
  - http://localhost:8080/registerHotDesk
  - http://localhost:8080/reserveMeetingRoom

- **Membership Endpoints** (running on port `8081` by default):
  - http://localhost:8081/createMembership
  - http://localhost:8081/registerPackage
  - http://localhost:8081/memberships/{userId}

---

## Testing

The application includes integration tests for domain for each use case. You can run the tests using Maven:

```bash
mvn clean test
```

---

## Author

👤 **Pol Piñol Castuera**

- GitHub: [@PolPinol](https://github.com/PolPinol)
- LinkedIn: [Pol Piñol Castuera](https://www.linkedin.com/in/polpinol/)

---

