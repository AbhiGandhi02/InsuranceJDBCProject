# Insurance Policy Management System (JDBC + MySQL)

A console-based insurance policy management system built using Java, JDBC, and MySQL.  
This project is built as part of a DBMS project and enables key operations like managing customers, policies, claims, payments, and staff interactions.

---

## 🎯 Features

- Customer Registration and Management  
- Insurance Policy Creation and Tracking  
- Claim Filing and Status Updates  
- Premium Payment Management  
- Staff Management  
- Policy Approval and Expiry Handling  
- Optional: Reports for Active Policies, Claim History, and Premium Dues

---

## 🛠 Tech Stack

- Java 17+  
- JDBC (MySQL Connector J 9.4.0)  
- MySQL 8+  
- Console-based Java Application  
- SQL (`schema.sql`) for DB setup  

---

## 🚀 How to Run This Project

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/AbhiGandhi02/InsuranceJDBCProject
cd InsuranceJDBCProject
2️⃣ Import the Database Schema
In MySQL Workbench or MySQL CLI, run:

sql
Copy
Edit
SOURCE schema.sql;
⚙️ Configure Database Credentials
Update the credentials in your Main.java file:

java
Copy
Edit
String url = "jdbc:mysql://localhost:3306/insurance_db";
String user = "your_mysql_username";
String password = "your_mysql_password";
3️⃣ Compile the Java Code
Ensure you're in the project root and compile with:

bash
Copy
Edit
javac -cp ".;lib/mysql-connector-j-9.4.0.jar" -d bin src/model/*.java src/dao/*.java src/Main.java
Linux/macOS users: Replace ; with : in the classpath.

4️⃣ Create the Executable .jar File
Ensure your manifest.txt contains:

vbnet
Copy
Edit
Main-Class: Main
Class-Path: lib/mysql-connector-j-9.4.0.jar
Then build the JAR:

bash
Copy
Edit
jar cfm insurance_project.jar manifest.txt -C bin .
5️⃣ Run the Application
bash
Copy
Edit
java -jar insurance_project.jar
You should now see a terminal-based menu to manage customers, policies, claims, and more.

📁 Project Structure
graphql
Copy
Edit
insurance-jdbc-project/
├── src/
│   ├── model/              # Java POJOs like Customer.java, Policy.java
│   ├── dao/                # DAO classes like CustomerDAO.java, PolicyDAO.java
│   └── Main.java           # Entry point with console-based menu
├── lib/
│   └── mysql-connector-j-9.4.0.jar
├── bin/                    # Compiled class files (excluded from GitHub)
├── schema.sql              # SQL file with schema + dummy data
├── insurance_project.jar   # Final runnable JAR file
├── manifest.txt            # Used to define JAR metadata
├── .gitignore              # To ignore build/system files
└── README.md               # This file