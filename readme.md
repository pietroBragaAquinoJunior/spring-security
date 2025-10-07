# 🛡️ Spring Security API: JWT, RSA & Clean Architecture

<img width="938" height="592" alt="image" src="https://github.com/user-attachments/assets/e6a94cab-3e92-473a-9150-bb26bca0ddd5" />

> **💬 Quick Overview:**
> Production-ready **Spring Boot API** template focusing on **JWT authentication**, **RSA key security**, and **Clean Architecture**. Ensures **business logic isolation**, robust **validation**, **global error handling**, **MapStruct mapping**, and **optimized JPA** with H2 + Flyway. Perfect for scalable and secure backend services.

---

## 🧩 Key Features & Highlights

* 🔒 **JWT Authentication:** Full support for token creation, validation, and refresh.
* 🗝️ **RSA Security + Spring Security 6.5.5:** Secure JWT signing and verification using RSA key pairs.
* 🏗️ **Clean Architecture:** Ports & Adapters (Hexagonal) keeping core logic decoupled.
* ✅ **Robust Validation:** Spring Validator ensures request data integrity.
* 🛠️ **Global Exception Handling:** Centralized with `@RestControllerAdvice` for consistent API responses.
* 🔄 **Data Mapping:** MapStruct for clean and efficient DTO / Domain / Entity conversion.
* 🗄️ **Database & Migrations:** JPA + H2 Memory Database + Flyway for reliable versioning.

---

## ⚙️ Tech Stack & Practices

| Category           | Technologies / Practices                         |
| :----------------- | :----------------------------------------------- |
| **Core**           | Spring Boot (Java), JPA (Hibernate)              |
| **Architecture**   | Ports & Adapters (Hexagonal), Clean Code         |
| **Security**       | JWT, RSA Keys, Spring Security                   |
| **Database**       | H2 Memory Database, Flyway (Migrations)          |
| **Utilities**      | MapStruct (Data Mapping), Spring Validation      |
| **Error Handling** | Global Exception Handler (@RestControllerAdvice) |

---

## ⏳ Live Status and Deployment

| Status       | Link |
| :----------- | :--- |
| ✅ finished (no deploy) | -    |


---

## 🛠 Local Installation and Setup

1. **Clone the repository:**
   git clone [https://github.com/pietroBragaAquinoJunior/spring-security](https://github.com/pietroBragaAquinoJunior/spring-security)

2. **Navigate to the directory:**
   cd spring-security

3. **Generate RSA keys** (used for signing and verifying JWTs):

   ```bash
   # Create the private key (used to sign JWTs)
   openssl genrsa -out src/main/resources/private.pem 2048

   # Create the public key (used to verify JWTs)
   openssl rsa -in src/main/resources/private.pem -pubout -out src/main/resources/public.pem
   ```

4. **Build and Run:** Use your preferred IDE or Maven/Gradle:

   * Maven: ./mvnw spring-boot:run
   * Gradle: ./gradlew bootRun


