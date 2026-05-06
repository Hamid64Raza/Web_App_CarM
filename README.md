# Web_App_CarM

A full-stack Spring Boot based used car marketplace platform inspired by modern second-hand car selling systems.

This project demonstrates real-world backend development concepts including:

* JWT Authentication & Authorization
* Spring Boot REST APIs
* AWS S3 Integration
* Twilio OTP Verification
* File Upload & Excel Processing
* CRM Module
* Car Listing & Search System
* Secure Externalized Configuration

---

# 🚀 Features

## 🔐 Authentication & Security

* User Registration & Login
* JWT-based Authentication
* Role-based Authorization
* Secure Password Handling
* Externalized Secret Configuration

## 🚗 Car Management

* Add Car Listings
* Update Car Details
* Delete Car Listings
* Upload Car Images
* Search Cars by:

  * Brand
  * Model
  * Fuel Type
  * Transmission
  * Year

## ☁️ AWS S3 Integration

* Upload car images to AWS S3
* Store image URLs in database
* Cloud-based file management

## 📱 Twilio OTP Integration

* OTP-based mobile verification
* SMS authentication flow

## 📊 CRM Module

* Manage customer leads
* Schedule customer visits
* Track sales activities
* Lead follow-up management

## 📂 Excel Upload Support

* Bulk upload functionality using Excel files
* Data import processing

---

# 🛠️ Tech Stack

## Backend

* Java
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* Maven
* JUnit 5
* Mockito

## Database

* MySQL

## Cloud & Third-Party Services

* AWS S3
* Twilio API

## Tools

* IntelliJ IDEA
* Git & GitHub
* Postman
* Maven

---

# 📁 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.app
│   │       ├── config
│   │       ├── controller
│   │       ├── entity
│   │       ├── payload
│   │       ├── repository
│   │       ├── service
│   │       └── AppApplication
│   │
│   └── resources
│       ├── application.properties
│       └── application-secret.properties
│
├── test
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

# ⚙️ Configuration Setup

This project uses externalized configuration for security.

Create:

```text
src/main/resources/application-secret.properties
```

Example:

```properties
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

jwt.key=YOUR_JWT_SECRET

aws.accessKeyId=YOUR_AWS_ACCESS_KEY
aws.secretKey=YOUR_AWS_SECRET_KEY
aws.s3.bucketName=YOUR_BUCKET_NAME

twilio.account.sid=YOUR_TWILIO_SID
twilio.auth.token=YOUR_TWILIO_AUTH_TOKEN
twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER
```

Main application.properties imports the secret file:

```properties
spring.config.import=application-secret.properties
```

---

# ▶️ Running the Project

## Clone Repository

```bash
git clone https://github.com/Hamid64Raza/Web_App_CarM.git
```

## Navigate to Project

```bash
cd Web_App_CarM
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

---

# 🔒 Security Best Practices Used

* Externalized secret management
* Git ignored sensitive files
* JWT token authentication
* Secure API architecture
* Layered backend architecture

---

# 📚 Learning Outcomes

This project helped in understanding:

* Spring Boot Architecture
* REST API Design
* Authentication & Authorization
* Cloud File Upload
* Real-world Backend Development
* Secure Configuration Management
* Git & GitHub Best Practices

---

# 👨‍💻 Author

Hamid Raza

GitHub: [https://github.com/Hamid64Raza](https://github.com/Hamid64Raza)
