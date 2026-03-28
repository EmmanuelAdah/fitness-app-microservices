# 🧠 AI-Powered Fitness Recommendation System

## 📌 Overview

This project is a **Spring Boot microservices-based fitness platform** that generates **AI-driven recommendations** based on user fitness activities. It leverages modern distributed system patterns, messaging queues, and GenAI to provide intelligent insights that help users improve their health and workout routines.

The system is designed with scalability, resilience, and modularity in mind using a **microservices architecture**.

---

## 🏗️ Architecture

The application is composed of the following services:

```
ai_powered_fitness_app/
│
├── configService        # Centralized configuration management
├── eurekaServer         # Service discovery
├── gateway              # API Gateway (entry point)
├── userService          # User management (PostgreSQL)
├── activityService      # Activity tracking (MongoDB)
├── aiservice            # AI recommendation engine (GenAI + MongoDB)
```

---

## ⚙️ Tech Stack

* **Backend:** Spring Boot, Spring Cloud
* **Service Discovery:** Eureka Server
* **API Gateway:** Spring Cloud Gateway
* **Messaging:** RabbitMQ
* **Databases:**

  * PostgreSQL (User Service)
  * MongoDB (Activity & AI Services)
* **AI Integration:** Gemini (GenAI)
* **Configuration Management:** Spring Cloud Config Server
* **Build Tool:** Maven/Gradle
* **Containerization (optional):** Docker

---

## 🔁 System Flow

1. User registers via **User Service**
2. User logs activity via **Activity Service**
3. Activity is:

   * Stored in MongoDB
   * Published to RabbitMQ
4. **AI Service** consumes activity events
5. AI Service:

   * Processes activity data
   * Calls GenAI (Gemini API)
   * Generates personalized recommendations
6. Recommendations are stored and made accessible via API Gateway

---

## 📦 Core Domain Models

### 🏃 Activity (MongoDB)

* Tracks user fitness activities
* Includes:

  * Duration
  * Calories burned
  * Activity type
  * Additional metrics

### 🤖 Recommendation (MongoDB)

* AI-generated insights:

  * Improvements
  * Suggestions
  * Safety measures
  * Natural language recommendation

### 👤 User (PostgreSQL)

* Stores user profile:

  * Name
  * Email
  * Password (should be encrypted)

---

## 📡 Messaging with RabbitMQ

* Activities are published using:

  ```java
  rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
  ```
* Enables **asynchronous communication** between services
* Decouples Activity Service from AI Service

---

## 🔐 Configuration (Config Server)

All services fetch configurations from a centralized **Config Server**, including:

* Database credentials
* RabbitMQ configs
* API keys (Gemini)
* Service-specific properties

---

## 🤖 AI Integration (Gemini)

AI Service integrates with Gemini API using:

```java
@ConfigurationProperties(prefix = "gemini.api")
```

Used to:

* Analyze user activity
* Generate contextual fitness advice
* Provide safety-aware recommendations

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/EmmanuelAdah/fitness-app-microservices.git
cd ai-fitness-app
```

### 2. Start Infrastructure

Ensure the following are running:

* MongoDB
* PostgreSQL
* RabbitMQ

(Optional via Docker)

### 3. Run Services in Order

1. configService
2. eurekaServer
3. gateway
4. userService
5. activityService
6. aiservice

---

## 🌐 API Access

All services are exposed through the **API Gateway**:

```
http://localhost:8080/api/...
```

---

## 🧪 Example Workflow

1. Create a user
2. Log an activity:

   ```json
   {
     "userId": "123",
     "activityType": "RUNNING",
     "duration": 30,
     "caloriesBurned": 250
   }
   ```
3. AI Service generates:

   * Suggestions to improve endurance
   * Safety recommendations
   * Personalized fitness advice

---

## 📊 Key Features

* ✅ Microservices architecture
* ✅ Event-driven communication (RabbitMQ)
* ✅ AI-powered recommendations
* ✅ Multi-database support
* ✅ Centralized configuration
* ✅ Scalable and extensible design

---

## 🔒 Future Improvements

* JWT Authentication & Authorization
* Rate limiting at API Gateway
* Circuit breakers (Resilience4j)
* Observability (Prometheus + Grafana)
* Model fine-tuning for better recommendations

## 💡 Notes

* Ensure sensitive configs (API keys, DB passwords) are stored securely.
* Use environment variables for production deployments.
* Add validation and encryption for user credentials.

---
