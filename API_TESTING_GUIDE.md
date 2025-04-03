# API Testing Guide

This guide provides test inputs and database objects for each API endpoint in the Courier Management System.

## Customer Management APIs

### 1. Create Customer

**Endpoint:** POST /api/customers
**Table:** Customers
**Test Input:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "09123456789",
  "email": "john.doe@example.com",
  "address": "123 Main St, Tehran",
  "postalCode": "1234567890",
  "nationalId": "1234567890"
}
```

### 2. Update Customer

**Endpoint:** PUT /api/customers/{customerId}
**Table:** Customers
**Test Input:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "09123456789",
  "email": "john.doe.updated@example.com",
  "address": "456 New St, Tehran",
  "postalCode": "9876543210",
  "nationalId": "1234567890"
}
```

### 3. Delete Customer

**Endpoint:** DELETE /api/customers/{customerId}
**Table:** Customers
**Test Input:** customerId = 1

### 4. Get Customer by ID

**Endpoint:** GET /api/customers/{customerId}
**Table:** Customers
**Test Input:** customerId = 1

### 5. Get All Customers

**Endpoint:** GET /api/customers
**Table:** Customers
**Test Input:** No input required

### 6. Search Customers

**Endpoint:** GET /api/customers/search
**Table:** Customers
**Test Input:** keyword = "john"

### 7. Get Customers by City

**Endpoint:** GET /api/customers/city/{city}
**Table:** Customers
**Test Input:** city = "Tehran"

## Packet Management APIs

### 1. Create Packet

**Endpoint:** POST /api/packets
**Table:** Packets
**Test Input:**

```json
{
  "weight": 2.5,
  "size": "Medium",
  "packetType": "Document",
  "sendDate": "2024-04-03",
  "statusId": 1,
  "price": 150000,
  "fragile": false,
  "deliveryType": "Standard",
  "recieverId": 2,
  "senderId": 1,
  "registererId": 1,
  "postmanId": 1
}
```

### 2. Update Packet

**Endpoint:** PUT /api/packets/{packetId}
**Table:** Packets
**Test Input:**

```json
{
  "weight": 2.5,
  "size": "Medium",
  "packetType": "Document",
  "sendDate": "2024-04-03",
  "statusId": 2,
  "price": 150000,
  "fragile": false,
  "deliveryType": "Express",
  "recieverId": 2,
  "senderId": 1,
  "registererId": 1,
  "postmanId": 1
}
```

### 3. Delete Packet

**Endpoint:** DELETE /api/packets/{packetId}
**Table:** Packets
**Test Input:** packetId = 1

### 4. Get Packet by ID

**Endpoint:** GET /api/packets/{packetId}
**Table:** Packets
**Test Input:** packetId = 1

### 5. Get All Packets

**Endpoint:** GET /api/packets
**Table:** Packets
**Test Input:** No input required

### 6. Get Packets by Sender

**Endpoint:** GET /api/packets/sender/{senderId}
**Table:** Packets
**Test Input:** senderId = 1

### 7. Get Packets by Receiver

**Endpoint:** GET /api/packets/receiver/{receiverId}
**Table:** Packets
**Test Input:** receiverId = 2

### 8. Get Packets by Date Range

**Endpoint:** GET /api/packets/date-range
**Table:** Packets
**Test Input:**

- startDate = 2024-04-01
- endDate = 2024-04-30

### 9. Get Packets by Status

**Endpoint:** GET /api/packets/status/{statusId}
**Table:** Packets
**Test Input:** statusId = 1

### 10. Get Fragile Packets

**Endpoint:** GET /api/packets/fragile
**Table:** Packets
**Test Input:** No input required

### 11. Get Packets by Delivery Type

**Endpoint:** GET /api/packets/delivery-type/{deliveryType}
**Table:** Packets
**Test Input:** deliveryType = "Express"

## Payment Management APIs

### 1. Create Payment

**Endpoint:** POST /api/payments
**Table:** Payments
**Test Input:**

```json
{
  "packetId": 1,
  "amount": 150000,
  "paymentDate": "2024-04-03",
  "paymentMethod": "Cash",
  "transactionId": "TRX123456",
  "discountCode": "DISCOUNT10",
  "paymentStatus": "Completed",
  "taxAmount": 15000
}
```

### 2. Update Payment

**Endpoint:** PUT /api/payments/{paymentId}
**Table:** Payments
**Test Input:**

```json
{
  "packetId": 1,
  "amount": 150000,
  "paymentDate": "2024-04-03",
  "paymentMethod": "Cash",
  "transactionId": "TRX123456",
  "discountCode": "DISCOUNT10",
  "paymentStatus": "Refunded",
  "taxAmount": 15000
}
```

### 3. Delete Payment

**Endpoint:** DELETE /api/payments/{paymentId}
**Table:** Payments
**Test Input:** paymentId = 1

### 4. Get Payment by ID

**Endpoint:** GET /api/payments/{paymentId}
**Table:** Payments
**Test Input:** paymentId = 1

### 5. Get All Payments

**Endpoint:** GET /api/payments
**Table:** Payments
**Test Input:** No input required

### 6. Get Payments by Packet

**Endpoint:** GET /api/payments/packet/{packetId}
**Table:** Payments
**Test Input:** packetId = 1

### 7. Get Payments by Date Range

**Endpoint:** GET /api/payments/date-range
**Table:** Payments
**Test Input:**

- startDate = 2024-04-01
- endDate = 2024-04-30

### 8. Get Payments by Status

**Endpoint:** GET /api/payments/status/{status}
**Table:** Payments
**Test Input:** status = "Completed"

### 9. Get Payments by Method

**Endpoint:** GET /api/payments/method/{method}
**Table:** Payments
**Test Input:** method = "Cash"

### 10. Get Payments with Discount

**Endpoint:** GET /api/payments/with-discount
**Table:** Payments
**Test Input:** No input required

### 11. Get Pending Payments

**Endpoint:** GET /api/payments/pending
**Table:** Payments
**Test Input:** No input required

## Database Tables and Views

### Tables

1. Customers

   - CustomerID (PK)
   - FirstName
   - LastName
   - Phone
   - Email
   - Address
   - PostalCode
   - NationalID

2. Packets

   - PacketID (PK)
   - Weight
   - Size
   - PacketType
   - SendDate
   - StatusID (FK)
   - Price
   - Fragile
   - DeliveryType
   - RecieverID (FK)
   - SenderID (FK)
   - RegistererID (FK)
   - PostmanID (FK)

3. Payments
   - PaymentID (PK)
   - PacketID (FK)
   - Amount
   - PaymentDate
   - PaymentMethod
   - TransactionID
   - DiscountCode
   - PaymentStatus
   - TaxAmount

### Views

1. CustomerPacketView

   - Shows customer details with their sent and received packets
   - Joins Customers and Packets tables

2. PaymentDetailsView
   - Shows payment details with related packet and customer information
   - Joins Payments, Packets, and Customers tables

### Functions

1. CalculateTotalPayments

   - Calculates total payments for a specific date range
   - Used by payment reporting endpoints

2. GetCustomerStatistics
   - Retrieves statistics about customer's packet history
   - Used by customer-related endpoints

## Testing Notes

1. All date inputs should be in ISO format (YYYY-MM-DD)
2. IDs should be valid existing IDs in the database
3. Status values should match the predefined status codes
4. Payment amounts should be positive numbers
5. Phone numbers should follow the correct format
6. Email addresses should be valid
7. Postal codes should match the required format
8. National IDs should be unique
