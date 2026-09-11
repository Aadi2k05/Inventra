# Inventra - Intelligent Inventory Management System

A full-stack inventory management platform with real-time warehouse operations, AI-powered forecasting, and order fulfillment automation.

## Project Structure

This is a **monorepo** with two main services:

```
inventra-backend/      Spring Boot REST API with MongoDB
inventra-frontend/     React 19 + Vite responsive UI
```

## Technology Stack

### Backend
- **Runtime:** Java 21 + Spring Boot 4.0.7
- **Database:** MongoDB
- **Authentication:** JWT (Spring Security)
- **API Docs:** Swagger/OpenAPI via Springdoc
- **Key Libraries:** Lombok, Spring Data MongoDB, Spring Validation, Spring Actuator

### Frontend
- **Framework:** React 19 + Vite
- **HTTP Client:** Axios
- **Design:** Responsive (desktop/tablet/mobile)

## Quick Start

### Backend
```bash
cd inventra-backend
mvn clean spring-boot:run
```
**Requirements:** 
- MongoDB running on `localhost:27017`
- Java 21+

**Runs on:** `http://localhost:8081`

### Frontend
```bash
cd inventra-frontend
npm install
npm run dev
```
**Connects to:** `http://localhost:8081/api` (configurable via `VITE_API_URL`)

## Features

- ✅ JWT authentication (register/login/logout)
- ✅ Multi-warehouse inventory network
- ✅ Real-time per-warehouse stock tracking
- ✅ Warehouse-to-warehouse transfers
- ✅ Order management with auto-reservation
- ✅ Purchase order & receiving workflows
- ✅ Returns processing
- ✅ Low-stock & out-of-stock alerts
- ✅ Stock movement history
- ✅ Inventory anomaly detection
- ✅ AI demand forecasting & smart replenishment
- ✅ Product recommendations
- ✅ Rule-based inventory assistant
- ✅ Sales analytics & daily reporting

## API Testing

Complete API reference: `inventra-backend/API_TEST_GUIDE.md`

**Quick start:**
1. Register/login at `POST /auth/register` and `POST /auth/login`
2. Copy JWT token from response
3. Test endpoints in order (products → warehouse → orders → etc.)
4. View interactive docs at `http://localhost:8081/swagger-ui.html`

## Configuration

Backend settings in `inventra-backend/src/main/resources/application.properties`:
- `server.port=8081` — HTTP port
- `inventra.jwt.secret` — Set via `INVENTRA_JWT_SECRET` env var
- `inventra.jwt.expiry-seconds` — Token TTL (default 24 hours)

## Build Notes

- Backend uses Maven with wrapper (`./mvnw`)
- Frontend uses Vite + npm
- See individual subdirectories for their respective READMEs
