# 🏦 Banking Microservices Application

A robust, **event-driven banking system** built with **Spring Boot**. Handles user accounts and financial transactions using **synchronous OpenFeign** communication and **asynchronous Kafka event streaming**.

[![Java](https://img.shields.io/badge/Java-17%2B-brightgreen)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-orange)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Apache%20Kafka-4.x-blue)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-brightpurple)](https://www.postgresql.org/)


**Key Services:**
- **Eureka Server**: Service registry for dynamic discovery
- **API Gateway**: Single entry point for all client requests
- **Account Service**: Manages accounts, balances (Credit/Debit operations)
- **Transaction Service**: Processes transactions via Feign → Account Service, publishes Kafka events

## 🛠️ Tech Stack

| Category | Technologies |
|----------|--------------|
| **Backend** | Java 17+, Spring Boot, Spring Data JPA, Spring Cloud Netflix, OpenFeign |
| **Database** | PostgreSQL |
| **Messaging** | Apache Kafka (KRaft Mode) |
| **Utilities** | Lombok, Docker-ready |

## 🚀 Quick Start Guide

### 1. Prerequisites
- ☕ **Java 17+**
- 🐘 **PostgreSQL** (port 5432)
- 📦 **Kafka 4.x+** (extracted to `D:\kafka`)

### 2. Database Setup
```sql
CREATE DATABASE transactionDB;
-- Create accountDB if using separate databases
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/transactionDB
    username: your_username
    password: your_password
```
# Navigate to Kafka directory
```
cd D:\kafka

# 1. Generate Cluster UUID
bin\windows\kafka-storage.bat random-uuid
# Copy output: e.g., "d5_8Su2XRl6OwwOh0DvpsA"

# 2. Format storage (replace <YOUR_UUID>)
bin\windows\kafka-storage.bat format -t <YOUR_UUID> -c config\server.properties --standalone

# 3. Start Kafka broker
bin\windows\kafka-server-start.bat config\server.properties
```
# Start microservice
1. Eureka Server (port 8761)    → mvn spring-boot:run
2. API Gateway (port 8080)      → mvn spring-boot:run  
3. Account Service (port 8081)  → mvn spring-boot:run
4. Transaction Service (port 8082) → mvn spring-boot:run



