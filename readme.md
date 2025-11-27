# Task Manager - Java Spring Boot deploy on Ec2

A simple Task Manager application built with Java Spring Boot and a clean HTML/CSS/JS user interface.

This project requires no database. All data is stored in memory. It’s perfect for learning backend development, building a small UI, and deploying on AWS EC2 with Nginx.

---

## Features

- Add new tasks  
- Toggle tasks as done or open  
- Delete tasks  
- View all tasks  
- Simple and responsive UI  
- REST API backend  
- No database required  
- Deployable on EC2 behind Nginx  

---

## Project Structure
```
task-manager/
├── pom.xml
├── src/
│ └── main/
│ ├── java/com/satish/taskmanager/
│ │ ├── TaskManagerApplication.java
│ │ ├── controller/TaskController.java
│ │ ├── service/TaskService.java
│ │ └── model/Task.java
│ └── resources/
│ ├── application.properties
│ └── static/index.html
└── target/task-manager-1.0.0.jar
```

---

## Technologies Used

- Java 17 (Amazon Corretto)
- Spring Boot 3.2
- HTML, CSS, JavaScript
- Maven
- Amazon Linux 2023
- Nginx

---

## API Endpoints

| Method | Endpoint         | Description            |
|--------|------------------|------------------------|
| GET    | /api/tasks       | Get all tasks          |
| POST   | /api/tasks       | Create a new task      |
| PUT    | /api/tasks/{id}  | Toggle completed state |
| DELETE | /api/tasks/{id}  | Delete a task          |

---

## UI

The frontend is a single HTML file located at:
java-app\src\main\resources\static\index.html

Spring Boot automatically serves this UI at: http://<ec2-public-ip>:8080/


---

# Deployment on AWS EC2 (Full Guide)

Follow these steps to deploy this application on an EC2 instance with Nginx.

---

## 1. Launch an EC2 Instance

- Amazon Linux 2023  
- t2.micro  
- Open ports: 22 (SSH), 80 (HTTP)

---

## 2. Connect to EC2

```sh
ssh -i yourkey.pem ec2-user@your-ec2-ip
```
## 3. Install Java 17 and Maven
```
sudo dnf install java-17-amazon-corretto-devel -y
sudo dnf install maven -y
```

## 4. Clone or upload your project
```
git clone https://github.com/satishpathade/java-app.git
cd java-app
```
## 5. Build the project
```
mvn clean package
```
You will get: 
target/task-manager-1.0.0.jar

## 6. Test the application
backgroud run :
```
java -jar target/task-manager-1.0.0.jar
```
open in browser : http://your-ec2-ip:8080/
stop: Press CTRL+C to stop.


## 7. Run in background
```
cd target
nohup java -jar task-manager-1.0.0.jar > app.log 2>&1 &
```
Check running: 
```
ps aux | grep java
```

## 8. Configure reverse proxy
```
sudo nano /etc/nginx/nginx.conf/
```
server block :
```
server {
    listen 80;
    server_name _;

    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```
## 9. Restart Nginx
```
sudo service nginx restart
```

## Author
Deployed by Satish Pathade