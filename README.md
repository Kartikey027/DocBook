# 🩺 Doctor Appointment Booking System

A full-stack **Doctor Appointment Booking System** built using **Spring Boot**, **Spring Security**, **Thymeleaf**, and **Bootstrap**. This application allows **patients to register and book appointments** with available doctors, while doctors can **manage and confirm appointments** through their personalized dashboard.

---

## 📌 Features

### 👤 Authentication & Roles
- Patient and Doctor registration with role-based access
- Secure login using **Spring Security**
- Redirects to dashboards based on user roles

### 🧑‍⚕️ Doctor Features
- View personal profile (name, specialization, qualification)
- Manage appointments (confirm or cancel)
- Calendar-like appointment viewing (basic implementation)

### 🧑‍💻 Patient Features
- Register and login securely
- Search doctors by specialization
- Book appointments (only 24 hours or more in advance)
- View upcoming appointments

---

## ⚙️ Tech Stack

| Layer            | Technology                     |
|------------------|-------------------------------|
| Language         | Java 17                        |
| Backend          | Spring Boot, Spring MVC, Spring Security |
| Frontend         | Thymeleaf, HTML, Bootstrap     |
| ORM              | Spring Data JPA, Hibernate     |
| Database         | MySQL / H2 (Dev Mode)          |
| Build Tool       | Maven                          |

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/kartikey/docbook/
│   │   ├── model/               # Entity classes
│   │   ├── controller/          # MVC Controllers
│   │   ├── service/             # Business logic
│   │   ├── repository/          # Spring Data JPA repositories
│   │   ├── config/              # Security configuration
│   └── resources/
│       ├── templates/           # Thymeleaf views
│       │   ├── patient/
│       │   ├── doctor/
│       ├── static/              # CSS/JS if any
│       └── application.properties
```

---

## 🚀 How to Run

1. **Clone the repository**  
   ```bash
   git clone https://github.com/yourusername/docbook.git
   cd docbook
   ```

2. **Set up MySQL** (or use H2 for quick test)

3. **Configure `application.properties`**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/docbook
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access in browser**
   ```
   http://localhost:8080/login
   ```


---

## 🛡️ Security Details
- Passwords stored using **BCrypt**
- Role-based access: `/doctor/**` and `/patient/**` are protected
- Registration allowed for both roles

---

## 🧠 Future Improvements

- Add doctor calendar view with full scheduling UI
- Email confirmations for appointments
- Admin role for managing doctors and patients
- Pagination and filters on appointment views
- Responsive design with more advanced UI

---

## 👨‍💻 Author

**Kartikey Saxena**  
