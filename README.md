This project implements JWT-based authentication in a Spring Boot application using Spring Security.

🚀 Features

User authentication via JWT (JSON Web Token)

Secure API endpoints using JWT-based authentication

Custom JWT Filter for request validation

Spring Security Configuration

In-Memory User Store (for testing)

🛠 Tech Stack

Spring Boot

Spring Security

JWT (Java JWT - JJWT)

Maven

Java 11+

📌 Setup Instructions

1️⃣ Clone the Repository

git clone https://github.com/sommyajit/scaler.git
cd scaler-project

2️⃣ Configure Dependencies

Ensure you have the required dependencies in pom.xml:

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

3️⃣ Run the Application

mvn spring-boot:run

🔑 Authentication Flow

1️⃣ Generate a JWT Token

Send a POST request to /auth/login with a username and password.

{
"username": "admin",
"password": "password"
}

✅ Response:

{
"token": "your-jwt-token-here"
}

2️⃣ Access Secured API

Send a GET request to /products, adding the JWT token in the Authorization Header:

Authorization: Bearer your-jwt-token-here

✅ If valid, you get the data.
❌ If invalid, you get 403 Forbidden.

🏗 Project Structure

scaler-project/
├── src/main/java/scalerproject/
│   ├── config/SecurityConfig.java    # Spring Security Config
│   ├── controller/AuthController.java # Login API
│   ├── filter/JwtAuthenticationFilter.java # JWT Filter
│   ├── service/JwtUtil.java # JWT Utility Class
│   ├── dto/AuthRequest.java  # Login Request DTO
│   ├── dto/AuthResponse.java # Login Response DTO

⚙️ Configuration

Change the secret key in JwtUtil.java:

private static final String SECRET_KEY = "your-strong-secret-key-here";

You can also modify the expiration time:

private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

🛠 Future Improvements

Use a database for user authentication

Implement Role-Based Access Control (RBAC)

Refresh token support

📜 License

This project is licensed under the MIT License.

