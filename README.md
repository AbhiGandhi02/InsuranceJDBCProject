# 🛡️ Insurance policy Management System (JDBC + MySQL)

A robust console-based insurance policy management system built using **Java**, **JDBC**, and **MySQL**. This DBMS project provides core functionalities like managing customers, policies, claims, payments, and more.

---

##  Features

- 🧾 Customer Registration and Management  
- 📄 Insurance Policy Creation and Tracking  
- 🧑‍⚖️ Claim Filing and Status Updates  
- 💳 Premium Payment Management  
- 👨‍💼 Staff and Admin Interaction Modules  
- ✅ Policy Approval and Expiry Handling  
- 📊 Reports (optional): Active Policies, Claim History, Premium Dues

---

## 🛠 Tech Stack

- Java 17+
- JDBC (MySQL Connector J 9.4.0)
- MySQL 8+
- Console-based Java Application
- SQL (`schema.sql`) for DB setup

---

## How to Run This Project

### 1 Clone the Repository

```bash
git clone https://github.com/AbhiGandhi02/InsuranceJDBCProject
cd InsuranceJDBCProject
```

### 2 Import the Database Schema
In MySQL Workbench or MySQL CLI, run:

```bash
SOURCE schema.sql;
```

### ⚙️ Configure Database Credentials

Before running the application, update the `Main.java` file:

```java
DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
DB_USER = "your_mysql_username";
DB_PASSWORD = "your_mysql_password";
```

### 3 Compile the Java Code
Ensure you're in the project root, then run:

```bash
javac -cp "lib/mysql-connector-j-9.4.0.jar" -d bin src/util/DBConnection.java src/Main.java src/dao/*.java src/model/*.java
```
For Linux/macOS, use : instead of ;
Make sure the .jar is inside the lib/ folder.

### 4 Create the Executable .jar File
Ensure your manifest.txt contains:

```bash
Main-Class: Main
Class-Path: lib/mysql-connector-j-9.4.0.jar
and an extra empty line
```

Then run:
```bash
jar cfm InsuranceApp.jar manifest.txt -C bin .
```
This generates the final hospital_project.jar file.

### 5 Run the Application
Run the application from terminal or command prompt:

```bash
java -jar InsuranceApp.jar
```
You should see a console menu to interact with the system (add/view customer, policy, payment, etc.).


## Project Structure

```text
hospital-jdbc-project/
├── src/
│   ├── model/              # Java POJOs like Customer.java, Customer.java
│   ├── dao/                # DAO classes like CustomerDAO.java, CustomerDAO.java
│   └── Main.java           # Entry point with console-based menu
├── lib/
│   └── mysql-connector-j-9.4.0.jar
├── bin/                    # Compiled class files (excluded from GitHub)
├── schema.sql              # SQL file with schema + dummy data
├── InsuranceApp.jar    # Final runnable JAR file
├── manifest.txt            # Used to define JAR metadata
├── .gitignore              # To ignore build files & system files
└── README.md               # This file
```