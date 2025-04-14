# ✅ Validata - Optimized Username Registration with Custom Bloom Filter

**Validata** is a Spring Boot application designed to handle **fast and memory-efficient username availability checks** using a **Custom Bloom Filter** along with a relational database. It prevents duplicate registrations with high performance and accuracy.

---

## 🚀 Features

- ⚡ **In-Memory Username Lookup** using a **Custom Bloom Filter**
- 🔒 **Duplicate Prevention** via database validation
- 🧠 **False Positive Handling** to ensure consistency
- 🔧 Integrated with **Spring Boot**, **Spring Data JPA**, and **MySQL**

---

## 📦 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- MySQL
- 🌸 Custom Bloom Filter (no external library used!)

---

## 📁 API Endpoints

### 🔍 Check Username Availability  
**GET** `/api/users/check`

#### Request Body:
```json
{
  "username": "adhish"
}
``` 

#### Response:
- Username is Taken ✅ (from Bloom Filter)
- OR
- Username is Taken (Confirmed By DB)
- OR
- Username is available ✅ (false positive from Bloom Filter)
