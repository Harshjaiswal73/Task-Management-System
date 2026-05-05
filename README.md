# Task Management System

A REST API built with Spring Boot for managing tasks
with secure authentication and role-based access.

## Features
- JWT Authentication & Authorization
- Role-Based Access Control (Admin/User)
- Task CRUD Operations
- Task Status Tracking (Pending/In Progress/Done)
- Due Date Management
- File Upload via Cloudinary
- Secured with Spring Security

## Tech Stack
Java | Spring Boot | MySQL | JWT | Spring Security |
Spring Data JPA | Cloudinary | Maven | Postman

## API Endpoints

### Auth
- POST /api/auth/register — Register new user
- POST /api/auth/login    — Login & get JWT token

### Tasks
- GET    /api/tasks       — Get all tasks
- POST   /api/tasks       — Create new task
- PUT    /api/tasks/{id}  — Update task
- DELETE /api/tasks/{id}  — Delete task
- PATCH  /api/tasks/{id}/status — Update task status
