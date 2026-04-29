# Deployment Guide

This project is configured for a **one-click deployment** on Render using the provided `render.yaml` file.

## Render Deployment Steps

1. **Push to GitHub:** Ensure this reconstructed code is pushed to your GitHub repository.
2. **Connect to Render:**
   - Log in to your [Render Dashboard](https://dashboard.render.com/).
   - Click on **New +** and select **Blueprint**.
   - Connect your GitHub repository.
3. **Automatic Setup:**
   - Render will detect the `render.yaml` file.
   - It will create a single **Java Web Service**.
   - It will attach a persistent disk at `/opt/render/project/data`.
   - The app will use an embedded H2 database by default, so no extra database service is required.

## Configuration Details
- **Port:** The application uses Render's `PORT` env var automatically.
- **Database:** By default the app stores data in `jdbc:h2:file:/opt/render/project/data/ngo-db`.
- **Persistence:** The persistent disk keeps volunteer data across restarts and redeploys.
- **Optional secrets:** Twilio and admin credentials can be set with env vars in Render if needed.

## Why This Works Better on Render
- Render Blueprints do not provision MySQL in the way the old file expected.
- A single-service deploy is faster to understand, easier to maintain, and closer to real one-click setup.
- First deploy works without requiring external secrets.

## Optional Production Overrides
If you want to use MySQL later, set:

```text
DATABASE_URL
DATABASE_USERNAME
DATABASE_PASSWORD
DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect
```

## Manual Docker Deployment
If you wish to deploy manually using Docker:
```bash
docker build -t ngo-volunteer-app .
docker run -p 9090:9090 ngo-volunteer-app
```
