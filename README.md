
---

# User Management Web Application

This is a simple **User Management System** built with **Java EE (Servlets and JSP)** and backed by **SQL Server**. It allows users to perform basic **CRUD** operations: Create, Read, Update, and Delete.

## 📌 Features

* View all users in a table format
* Add a new user
* Edit existing user details
* Delete a user
* JSP-based forms and Bootstrap for styling
* Follows MVC (Model-View-Controller) design pattern

## 🛠️ Technologies Used

* Java Servlet API
* JSP (JavaServer Pages)
* JDBC (Java Database Connectivity)
* SQL Server
* HTML/CSS (Bootstrap)
* Apache Tomcat (or any Java EE compatible server)

## 📁 Project Structure

```
com.xadmin.usermanagement
├── dao
│   └── USerDAO.java        # Handles all database operations
├── model
│   └── User.java           # JavaBean representing a User
├── web
│   └── UserServlet.java    # Main controller servlet
```

## 🧩 Database Setup

1. Create a SQL Server database named `userMangment`.
2. Create a table using the following SQL:

```sql
CREATE TABLE users (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(100),
    email VARCHAR(100),
    country VARCHAR(100)
);
```

3. Update your DB credentials in `USerDAO.java`:

```java
private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=userMangment;encrypt=false";
private String jdbcUsername = "YOUR_USERNAME";
private String jdbcPassword = "YOUR_PASSWORD";
```

## 🚀 Running the Application

1. Clone the repository.
2. Open it in an IDE like **Eclipse**, **IntelliJ IDEA**, or **NetBeans**.
3. Configure your servlet container (e.g., Tomcat).
4. Deploy the project and access it via `http://localhost:8080/UserManagementApp/` or similar based on your context path.

## 🧑‍💻 Author

**Mohamed Sayed**

---

