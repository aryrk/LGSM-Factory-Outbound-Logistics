# Factory Outbound Logistics - Weather Risk

Case study submission for a dispatch team app. It lets users create shipments and assigns a weather-based risk tag (LOW/MEDIUM/HIGH/UNKNOWN) based on the destination's forecast.

## Stack

- Backend: Java 17 + Spring Boot
- Frontend: React (Vite)
- Database: Postgres.(I used Postgres instead of the suggested Oracle because it is faster to set up locally with Docker. The schema matches the original DDL requirements, adapted for Postgres syntax and using UUIDs instead of identity numbers.)
- External API: Open-Meteo (`https://api.open-meteo.com/v1/forecast`).

## Architecture

I kept this as a single Spring Boot monolith to fit the time budget. I separated the code into distinct packages. The com.factory.logistics.weather package handles the API calls, caching, and risk calculation. The com.factory.logistics.shipment package handles the core logic.

If this needed to be a microservice, the weather package could be extracted into its own service exposing a `GET /risk?lat=&lng=&date=` endpoint.

## How to run

**Docker Compose (Recommended)**

Run `docker-compose up --build -d` in the root folder.

- Backend: http://localhost:8082
- Frontend: http://localhost:5173
- Swagger: http://localhost:8082/swagger-ui.html

**Manual Setup**

For the backend, copy `.env.example` to `.env` and fill in your DB credentials. Run `./mvnw spring-boot:run`. It expects a local Postgres instance running.
For the frontend, navigate to `logistics-FE`, copy `.env.example` to `.env`, run `npm install`, and then `npm run dev`.

## API Endpoints

- **POST** `/api/shipments`- Create shipment and compute risk
- **GET** `/api/shipments` - List all shipments
- **GET** `/api/shipments/{id}` - Get specific shipment (returns 404 if missing)
- **DELETE** `/api/shipments/{id}` - Delete specific shipment

Example payload:

```json
{
  "productCode": "SK-01",
  "quantity": 10,
  "destinationCity": "BANDUNG",
  "destinationLatitude": 52.52,
  "destinationLongitude": 13.41,
  "dispatchDate": "2026-09-01"
}
```
