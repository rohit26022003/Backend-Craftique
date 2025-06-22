# 🛠️ Craftique Backend - Spring Boot API

This is the **backend server** for **Craftique**, a full-stack e-commerce platform built for showcasing and selling handmade craft products.

It provides a robust RESTful API using **Spring Boot**, **MySQL**, and **Spring Security**, and handles all core business logic and data persistence for the platform.

---

## 🚀 Features

- ✅ User registration & JWT-based authentication (Login/Signup)
- ✅ Role-based access: Admin & User
- ✅ Product Management (Add, Edit, Delete, List)
- ✅ Order Management and Cart System
- ✅ Contact Us Message Handling
- ✅ Review System
- ✅ Admin Dashboard APIs (user count, revenue, total orders, etc.)
- ✅ Email OTP Verification (for Forgot Password)
- ✅ Fully connected to a React frontend

---

## 📁 Technologies Used

- **Spring Boot 3.x**
- **Spring Security + JWT**
- **MySQL (Database)**
- **JPA / Hibernate**
- **Lombok**
- **Maven**
- **Java Mail Sender (for OTP email)**

---

## 📦 Project Structure (Backend)
src/
├── config/ # Security configurations
├── controller/ # REST API controllers
├── dto/ # Data transfer objects
├── entity/ # JPA Entities
├── repository/ # Spring Data JPA Repos
├── service/ # Business logic
└── CraftiqueApp.java # Main Application


---

## 🔐 Authentication

- Authentication is handled via **JWT Tokens**.
- On successful login, the API returns a token that must be passed in the `Authorization` header for protected routes.


---

## 🔗 API Endpoints

| Functionality     | Endpoint                      | Method |
|-------------------|-------------------------------|--------|
| Register          | `/api/auth/register`          | POST   |
| Login             | `/api/auth/login`             | POST   |
| Add Product       | `/api/products`               | POST   |
| Place Order       | `/api/order`                  | POST   |
| View Cart         | `/api/cart/{userId}`          | GET    |
| Send Message      | `/api/contact`                | POST   |
| Admin             | `/api/admin`                  | GET    |

> More endpoints are available in the source code.

---

## 🧪 Running Locally

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/rohit26022003/Backend-Craftique.git
   cd Backend-Craftique

Configure MySQL DB
Update application.properties with your database credentials.

Run the Project
You can run it via your IDE (VS Code) or command line:
mvn spring-boot:run

📧 Contact
If you have any questions or suggestions, feel free to contact:

Rohit Shrivastava
📧 shrivastavarohit701@gmail.com

🌐 Frontend Repository
You can find the React frontend for this project here:
👉 Frontend-Craftique (https://github.com/rohit26022003/Craftique.git)
