Banking Microservices Application
A robust, event-driven banking microservices application built with Spring Boot. This system handles user accounts and financial transactions using a mix of synchronous communication (OpenFeign) and asynchronous event streaming (Apache Kafka).

🏗️ Architecture & Services
This project consists of several interacting microservices:

Eureka Server: Service registry for dynamic discovery.

API Gateway: Central entry point for routing client requests to the appropriate microservices.

Account Service: Manages user bank accounts, balances, and handles direct database updates (Credit/Debit).

Transaction Service: Processes transaction requests, communicates synchronously with the Account Service via OpenFeign, logs the transaction history, and publishes async events to Kafka.

🛠️ Tech Stack
Java & Spring Boot (Web, Data JPA, Cloud Netflix Eureka, OpenFeign)

PostgreSQL (Relational Database)

Apache Kafka (Event Broker - KRaft Mode)

Lombok (Boilerplate reduction)

🚀 Getting Started
1. Prerequisites
Java 17+ installed.

PostgreSQL running locally on port 5432.

Apache Kafka (v4.x+) downloaded and extracted (e.g., to D:\kafka).

2. Database Setup
Ensure you have created the necessary PostgreSQL databases before starting the services. For example:

SQL
CREATE DATABASE transactionDB;
-- Create additional databases for account-service if they are separated
Update your application.yaml files with your local Postgres credentials (username/password).

3. Start Apache Kafka (Windows KRaft Mode)
This application relies on Kafka for event-driven architecture. Since Kafka 4.x operates without ZooKeeper, follow these exact steps to start your local KRaft cluster.

Open a Command Prompt, navigate to your Kafka installation directory (e.g., cd D:\kafka), and run these commands in order:

Generate a Cluster UUID:

DOS
bin\windows\kafka-storage.bat random-uuid
(Copy the output string, e.g., d5_8Su2XRl6OwwOh0DvpsA)

Format the Storage Directory (Replace <YOUR_UUID>):

DOS
bin\windows\kafka-storage.bat format -t <YOUR_UUID> -c config\server.properties --standalone
Start the Kafka Broker:

DOS
bin\windows\kafka-server-start.bat config\server.properties
Optional: Start a Console Consumer (To watch events live)
Open a new Command Prompt window and run:

DOS
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic transaction_event --from-beginning
4. Start the Microservices
Start the Spring Boot applications in the following order to ensure proper registration and discovery:

Eureka Server (@EnableEurekaServer)

API Gateway

Account Service

Transaction Service

🔌 Core API Endpoints
Once the Gateway is running, you can test the flow using Postman or cURL. (Assuming Gateway runs on port 8080).

1. Create a New Account

HTTP
POST http://localhost:8080/api/accounts
Body: { "name": "John Doe", "money": 1000 }
2. Credit Money (Via Transaction Service)

HTTP
PUT http://localhost:8080/api/transactions/credit
Flow: Transaction Service -> Calls Account Service via Feign -> Saves Transaction to DB -> Publishes TransactionEvent to Kafka.

3. Debit Money (Via Transaction Service)

HTTP
PUT http://localhost:8080/api/transactions/debit
