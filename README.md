# NGO Volunteer Management System

This is a Spring Boot application designed to manage NGO volunteers, set up for a simple one-click deployment on **Render**.

## Quick Links
- [Deployment Guide](docs/DEPLOYMENT.md)
- [Project Architecture](docs/ARCHITECTURE.md)
- [API Documentation](docs/API.md)

## Features
- Volunteer Registration and Management
- Admin Dashboard
- Twilio Integration for WhatsApp Notifications
- Persistent embedded database for zero-config hosting
- Optional MySQL and Twilio env var overrides

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.2.5
- **Database:** H2 by default, MySQL optional
- **Frontend:** Thymeleaf
- **Deployment:** Render Blueprint, Docker optional

## How to Run Locally
1. Ensure you have Java 17 and Maven installed.
2. Run:
   ```bash
   mvn spring-boot:run
   ```
3. Or build and run the jar:
   ```bash
   mvn clean package
   java -jar target/ngoApp-0.0.1-SNAPSHOT.jar
   ```

## Deploy to Render
1. Push this repo to GitHub.
2. In Render, click `New +` -> `Blueprint`.
3. Select this repo and Render will use [render.yaml](render.yaml).
4. The app will deploy as a single web service and keep its H2 database on a persistent disk.

## Optional Environment Variables
- `TWILIO_ACCOUNT_SID`
- `TWILIO_AUTH_TOKEN`
- `TWILIO_WHATSAPP_FROM`
- `ADMIN_EMAIL`
- `ADMIN_PASSWORD`
- `APP_BASE_URL`
- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `DATABASE_DRIVER`
- `HIBERNATE_DIALECT`

## Docker
If you prefer Docker, you can still build the image:
   ```bash
   docker build -t ngo-app .
   docker run -p 9090:9090 ngo-app
   ```
