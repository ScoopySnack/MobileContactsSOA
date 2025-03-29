# MobileContactsSOA

## Overview
MobileContactsSOA is a Service-Oriented Architecture (SOA) project designed to manage mobile contacts efficiently,  
and an exercise for learning and practicing Service-Oriented Architecture principles.
This application follows a modular architecture to ensure separation of concerns and scalability. 
The project is structured using a multi-layered approach, including controllers, services, data access, and validation.

## Features
- **Manage mobile contacts**: Create, update, delete, and retrieve contacts.
- **Service-Oriented Design**: Decoupled components following SOA principles.
- **Layered Architecture**: Ensures maintainability and scalability.

## Project Structure
The project follows a well-defined package structure:

```
MobileContactsSOA
│── src/
│   ├── gr/cf/java/app/
│   │   ├── controller/
│   │   ├── core/
│   │   ├── dao/
│   │   ├── dto/
│   │   ├── exceptions/
│   │   ├── mapper/
│   │   ├── model/
│   │   ├── service/
│   │   ├── validation/
│   │   ├── Main.java

```

## Package Breakdown
- **controller/**: Handles incoming HTTP requests and delegates logic to the service layer.
- **core/**: May contain core business logic and utilities.
- **dao/**: Data Access Objects for database operations.
- **dto/**: Data Transfer Objects for exchanging data between layers.
- **exceptions/**: Custom exception handling for error management.
- **mapper/**: Converts between DTOs and entities.
- **model/**: Defines the data models representing contact entities.
- **service/**: Business logic layer that communicates with the DAO layer.
- **validation/**: Contains validation logic to enforce data integrity.
- **Main.java**: Entry point for the application.



