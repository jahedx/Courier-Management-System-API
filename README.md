# Courier Management System

A Spring Boot application for managing courier services, including customers, packets, and payments.

## Features

- Customer Management
- Packet Tracking
- Payment Processing
- Employee Management
- Status Tracking
- Swagger API Documentation

## Prerequisites

- Java 17 or later
- Maven 3.6 or later
- SQL Server 2019 or later

## Database Setup

1. Create a new database named `g3`
2. Run the SQL script from `dbo.sql` to create the necessary tables and sample data

## Configuration

Update the database connection details in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://78.38.35.219:1433;databaseName=g3;encrypt=false;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=123456
```

## Building the Application

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring:boot run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI at:

- http://localhost:8080/swagger-ui.html

## API Endpoints

### Customer Management

- POST /api/customers - Create a new customer
- PUT /api/customers/{customerId} - Update customer information
- DELETE /api/customers/{customerId} - Delete a customer
- GET /api/customers/{customerId} - Get customer by ID
- GET /api/customers - Get all customers
- GET /api/customers/search - Search customers
- GET /api/customers/city/{city} - Get customers by city

### Packet Management

- POST /api/packets - Create a new packet
- PUT /api/packets/{packetId} - Update packet information
- DELETE /api/packets/{packetId} - Delete a packet
- GET /api/packets/{packetId} - Get packet by ID
- GET /api/packets - Get all packets
- GET /api/packets/sender/{senderId} - Get packets by sender
- GET /api/packets/receiver/{receiverId} - Get packets by receiver
- GET /api/packets/date-range - Get packets by date range
- GET /api/packets/status/{statusId} - Get packets by status
- GET /api/packets/fragile - Get fragile packets
- GET /api/packets/delivery-type/{deliveryType} - Get packets by delivery type

### Payment Management

- POST /api/payments - Create a new payment
- PUT /api/payments/{paymentId} - Update payment information
- DELETE /api/payments/{paymentId} - Delete a payment
- GET /api/payments/{paymentId} - Get payment by ID
- GET /api/payments - Get all payments
- GET /api/payments/packet/{packetId} - Get payments by packet
- GET /api/payments/date-range - Get payments by date range
- GET /api/payments/status/{status} - Get payments by status
- GET /api/payments/method/{method} - Get payments by method
- GET /api/payments/with-discount - Get payments with discount
- GET /api/payments/pending - Get pending payments

## Technologies Used

- Spring Boot 3.2.3
- Spring JDBC
- SQL Server
- Swagger/OpenAPI
- Lombok
- Maven
