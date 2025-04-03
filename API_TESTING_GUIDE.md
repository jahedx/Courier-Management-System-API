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

## APIs Using Stored Procedures

The following APIs are implemented using stored procedures:

### Customer Management

1. Search Customers (GET /api/customers/search)

   - Uses `sp_SearchCustomers` procedure
   - Searches across multiple columns (FirstName, LastName, Phone)

2. Get Customers by City (GET /api/customers/city/{city})
   - Uses `sp_GetCustomersByCity` procedure
   - Handles partial city name matching

### Packet Management

1. Get Packets by Date Range (GET /api/packets/date-range)

   - Uses `sp_GetPacketsByDateRange` procedure
   - Includes date validation and formatting

2. Get Fragile Packets (GET /api/packets/fragile)

   - Uses `sp_GetFragilePackets` procedure
   - Includes special handling for fragile items

3. Get Packets by Delivery Type (GET /api/packets/delivery-type/{deliveryType})
   - Uses `sp_GetPacketsByDeliveryType` procedure
   - Includes delivery type validation

### Payment Management

1. Get Payments by Date Range (GET /api/payments/date-range)

   - Uses `sp_GetPaymentsByDateRange` procedure
   - Includes date validation and formatting

2. Get Payments with Discount (GET /api/payments/with-discount)

   - Uses `sp_GetPaymentsWithDiscount` procedure
   - Includes discount code validation

3. Get Pending Payments (GET /api/payments/pending)
   - Uses `sp_GetPendingPayments` procedure
   - Includes status validation

## Stored Procedures

### Customer Procedures

1. sp_SearchCustomers

   ```sql
   CREATE PROCEDURE sp_SearchCustomers
   @Keyword NVARCHAR(100)
   AS
   BEGIN
       SELECT * FROM Customers
       WHERE FirstName LIKE '%' + @Keyword + '%'
       OR LastName LIKE '%' + @Keyword + '%'
       OR Phone LIKE '%' + @Keyword + '%'
   END
   ```

2. sp_GetCustomersByCity
   ```sql
   CREATE PROCEDURE sp_GetCustomersByCity
   @City NVARCHAR(100)
   AS
   BEGIN
       SELECT * FROM Customers
       WHERE Address LIKE '%' + @City + '%'
   END
   ```

### Packet Procedures

1. sp_GetPacketsByDateRange

   ```sql
   CREATE PROCEDURE sp_GetPacketsByDateRange
   @StartDate DATE,
   @EndDate DATE
   AS
   BEGIN
       SELECT * FROM Packets
       WHERE SendDate BETWEEN @StartDate AND @EndDate
   END
   ```

2. sp_GetFragilePackets

   ```sql
   CREATE PROCEDURE sp_GetFragilePackets
   AS
   BEGIN
       SELECT * FROM Packets
       WHERE Fragile = 1
   END
   ```

3. sp_GetPacketsByDeliveryType
   ```sql
   CREATE PROCEDURE sp_GetPacketsByDeliveryType
   @DeliveryType NVARCHAR(50)
   AS
   BEGIN
       SELECT * FROM Packets
       WHERE DeliveryType = @DeliveryType
   END
   ```

### Payment Procedures

1. sp_GetPaymentsByDateRange

   ```sql
   CREATE PROCEDURE sp_GetPaymentsByDateRange
   @StartDate DATE,
   @EndDate DATE
   AS
   BEGIN
       SELECT * FROM Payments
       WHERE PaymentDate BETWEEN @StartDate AND @EndDate
   END
   ```

2. sp_GetPaymentsWithDiscount

   ```sql
   CREATE PROCEDURE sp_GetPaymentsWithDiscount
   AS
   BEGIN
       SELECT * FROM Payments
       WHERE DiscountCode IS NOT NULL
   END
   ```

3. sp_GetPendingPayments
   ```sql
   CREATE PROCEDURE sp_GetPendingPayments
   AS
   BEGIN
       SELECT * FROM Payments
       WHERE PaymentStatus = 'در انتظار'
   END
   ```

## Database Operations APIs

### Stored Procedure Operations

#### 1. Create Customer Using SP

**Endpoint:** POST /api/db-operations/customers/sp
**Stored Procedure:** sp_CreateCustomer
**Test Input:**

```json
{
  "firstName": "علی",
  "lastName": "محمدی",
  "phone": "09123456789",
  "email": "ali@example.com",
  "address": "تهران، ولیعصر",
  "nationalId": "1234567890",
  "postalCode": "1234567890"
}
```

#### 2. Update Customer Using SP

**Endpoint:** PUT /api/db-operations/customers/{customerId}/sp
**Stored Procedure:** sp_UpdateCustomer
**Test Input:**

```json
{
  "firstName": "علی",
  "lastName": "محمدی",
  "phone": "09123456789",
  "email": "ali.updated@example.com",
  "address": "تهران، سعادت آباد",
  "postalCode": "9876543210"
}
```

#### 3. Add Packet Using SP

**Endpoint:** POST /api/db-operations/packets/sp
**Stored Procedure:** sp_AddPacket
**Test Input:**

```json
{
  "senderId": 3,
  "recieverId": 4,
  "weight": 2.5,
  "size": "30x20x10",
  "packetType": "کالا",
  "price": 50000.0,
  "statusId": 1,
  "sendDate": "2024-04-03",
  "fragile": true,
  "postmanId": 1,
  "registererId": 2,
  "deliveryType": "سریع"
}
```

#### 4. Delete Packet Using SP

**Endpoint:** DELETE /api/db-operations/packets/{packetId}/sp
**Stored Procedure:** sp_DeletePacket
**Test Input:** packetId = 1

#### 5. Process Payment Using SP

**Endpoint:** POST /api/db-operations/payments/sp
**Stored Procedure:** sp_ProcessPayment
**Test Input:**

```json
{
  "packetId": 1,
  "amount": 50000.0,
  "paymentDate": "2024-04-03",
  "paymentMethod": "کارت بانکی",
  "transactionId": "TXN123456",
  "discountCode": "NEWYEAR10",
  "paymentStatus": "موفق",
  "taxAmount": 4500.0
}
```

### Function Operations

#### 1. Get Total Payments for Customer

**Endpoint:** GET /api/db-operations/customers/{customerId}/total-payments
**Function:** GetTotalPaymentsForCustomer
**Test Input:** customerId = 3
**Expected Output:**

```json
{
  "customerId": 3,
  "firstName": "حنانه",
  "lastName": "کریمی",
  "totalPayments": 50000.0
}
```

#### 2. Get Packet Status

**Endpoint:** GET /api/db-operations/packets/{packetId}/status
**Function:** GetPacketStatusByPacketID
**Test Input:** packetId = 1
**Expected Output:**

```json
{
  "packetId": 1,
  "sender": "حنانه",
  "receiver": "محمدحسین",
  "statusName": "در حال ارسال",
  "currentLocation": "تهران - مرکز پست",
  "expectedDeliveryDate": "2025-02-23"
}
```

#### 3. Get Employee Workload

**Endpoint:** GET /api/db-operations/employees/{employeeId}/workload
**Function:** GetEmployeeWorkloadByID
**Test Input:** employeeId = 1
**Expected Output:**

```json
{
  "employeeId": 1,
  "firstName": "علی",
  "lastName": "احمدی",
  "totalDeliveries": 5
}
```

### View Operations

#### 1. Get Customer Shipments

**Endpoint:** GET /api/db-operations/customer-shipments
**View:** CustomerShipments
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "customerId": 3,
    "firstName": "حنانه",
    "lastName": "کریمی",
    "packetId": 1,
    "weight": 2.5,
    "size": "30x20x10",
    "packetType": "کالا",
    "sendDate": "2025-02-20",
    "statusName": "در حال ارسال",
    "price": 50000.0
  }
]
```

#### 2. Get Customer Total Spent

**Endpoint:** GET /api/db-operations/customer-total-spent
**View:** CustomerTotalSpent
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "customerId": 3,
    "firstName": "حنانه",
    "lastName": "کریمی",
    "totalSpent": 50000.0,
    "countPackets": 1
  }
]
```

#### 3. Get Employee Workload

**Endpoint:** GET /api/db-operations/employee-workload
**View:** EmployeeWorkload
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "employeeId": 1,
    "firstName": "علی",
    "lastName": "احمدی",
    "position": "پست چی",
    "registeredShipments": 3,
    "deliveredShipments": 5
  }
]
```

#### 4. Get Recent Deliveries

**Endpoint:** GET /api/db-operations/recent-deliveries
**View:** RecentDeliveries
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "packetId": 2,
    "sendDate": "2025-02-21",
    "price": 20000.0,
    "statusName": "تحویل شده",
    "senderFirstName": "زهرا",
    "senderLastName": "روحی فر",
    "deliveredByFirstName": "علی",
    "deliveredByLastName": "احمدی"
  }
]
```

#### 5. Get Shipment Status Summary

**Endpoint:** GET /api/db-operations/shipment-status-summary
**View:** ShipmentStatusSummary
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "statusId": 1,
    "statusName": "در حال ارسال",
    "totalPackets": 3
  },
  {
    "statusId": 2,
    "statusName": "تحویل شده",
    "totalPackets": 2
  }
]
```

#### 6. Get Top Paying Customers

**Endpoint:** GET /api/db-operations/top-paying-customers
**View:** TopPayingCustomers
**Test Input:** No input required
**Expected Output:**

```json
[
  {
    "customerId": 3,
    "firstName": "حنانه",
    "lastName": "کریمی",
    "totalPayments": 50000.0,
    "totalRows": 1
  }
]
```

### Testing Notes for Database Operations

1. Stored Procedures:

   - All stored procedures include validation checks
   - Error messages are in Persian
   - National ID must be unique for customer creation
   - Packet deletion is restricted if payment exists

2. Functions:

   - Functions return table-valued results
   - Results are mapped to Map<String, Object>
   - All functions include proper error handling

3. Views:

   - Views are optimized with proper indexing
   - Some views use SCHEMABINDING for better performance
   - Views handle NULL values appropriately

4. General Notes:
   - All dates should be in YYYY-MM-DD format
   - Monetary values should be positive numbers
   - Status values should match the predefined codes
   - IDs should reference existing records
   - Persian text is supported in all fields
