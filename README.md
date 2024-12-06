# Job Advertising App - Backend

This is the backend of a Job Advertising Application built using Java Spring Boot. The system allows users to register, verify their email addresses, store encrypted credit card information, and authenticate via token-based login.

## Features

- **User Registration**: Users can create accounts by providing their email and password.
- **Email Verification**: After registration, users will receive a verification email to confirm their account.
- **Credit Card Management**: Users can securely add their credit card details, which are encrypted and stored in the database.
- **Token-Based Authentication**: After registration and email verification, users can log in using a JWT (JSON Web Token).
- **Encrypted Data Storage**: Sensitive data like credit card information is encrypted in the database to ensure security and privacy.

## Technologies Used

- **Spring Boot**: Main framework for building the RESTful API.
- **Spring Security**: For authentication and authorization using JWT tokens.
- **JPA/Hibernate**: For ORM-based database interaction.
- **PostgreSQL**: Relational database (can be swapped as per configuration).
- **Java 17+**: The programming language.
- **Maven**: For dependency management and build automation.
- **Spring Mail**: For sending email verification links.

## Prerequisites

Before you run the application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6+ (for building and managing dependencies)
- PostgreSQL database
- An IDE like IntelliJ IDEA or Eclipse (optional but recommended)
