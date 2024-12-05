## CRUD Pegawai

This is a simple CRUD (Create, Read, Update, Delete) application for managing employee (Pegawai) data. 
The project is built using the following technologies:
## 🔧 Technologies Used
1. Frontend: React.js (running in a Docker container)
2. Backend: Spring Boot (running outside Docker)
3. Database: Microsoft SQL Server (running in a Docker container)

## ⚙️ Project Structure

```
CRUDPegawai/
│
├── BE/         # Backend project (Spring Boot)
├── FE/         # Frontend project (React.js)
└── README.md   # Project documentation
 ```

## 🚀  How to Run the Project
# Prerequisites
Docker and Docker Compose installed
Java (JDK 17 or later) installed
Node.js and npm/yarn installed

# 1. Run the Database (SQL Server) in Docker
Start the SQL Server container with the following command:

```
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=YourStrongPassword!' \
-p 1433:1433 --name sqlserver -d mcr.microsoft.com/mssql/server:2022-latest
```

Verify the container is running:
```
docker ps
```

# 2. Configure the Backend (Spring Boot)

1. Go to the BE folder:
```
cd BE
```

2. Update the application.properties (or application.yml) file to match the SQL Server connection:
```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=your_database_name
spring.datasource.username=sa
spring.datasource.password=YourStrongPassword!
```

3. Build and run the backend:
```
./mvnw spring-boot:run
```

# 3. Run the Frontend (React.js)

1. Navigate to the FE folder:
```
cd FE
```

2. Build and run the React app inside Docker:
```
docker build -t reactjs-app .
docker run -p 3000:3000 reactjs-app
```

3. Open the app in your browser:
```
http://localhost:3000
```

## 🛠 Features
Create: Add a new employee.
Read: View the list of employees.
Update: Edit employee details.
Delete: Remove employees from the system.


## 📂 Endpoints (API)
Here are the main API endpoints provided by the backend:

GET /api/employees - Get all employees
POST /api/employees - Create a new employee
PUT /api/employees/{id} - Update employee by ID
DELETE /api/employees/{id} - Delete employee by ID

📜 License

This project is licensed under the MIT License.


