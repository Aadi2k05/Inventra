# Inventra Backend API Test Order

Base URL: `http://localhost:8081/api`

All endpoints except `/auth/register`, `/auth/login`, Swagger and health require:
`Authorization: Bearer <JWT>`

## 1. Authentication

### Register
POST `/auth/register`
```json
{"name":"Inventra User","email":"user@example.com","password":"password123"}
```

### Login
POST `/auth/login`
```json
{"email":"user@example.com","password":"password123"}
```
Copy `token` from the response.

## 2. Product
POST `/products`
```json
{"sku":"LAP-001","name":"Dell Laptop","category":"Electronics","price":65000,"stockQuantity":0,"reorderLevel":10,"leadTimeDays":7}
```
GET `/products`
PUT `/products/{productId}`
DELETE `/products/{productId}`
GET `/products/low-stock`

## 3. Warehouse + inventory
POST `/warehouses`
```json
{"code":"WH-MAIN","name":"Main Warehouse","location":"Indore"}
```
POST `/warehouses/{warehouseId}/inventory/adjust`
```json
{"productId":"<productId>","quantity":50,"reason":"Opening stock"}
```
GET `/warehouses/{warehouseId}/inventory`
GET `/warehouses/{warehouseId}/inventory/{productId}`

Transfer:
POST `/warehouses/{warehouseId}/inventory/transfer`
```json
{"productId":"<productId>","toWarehouseId":"<secondWarehouseId>","quantity":5,"reason":"Warehouse balancing"}
```

## 4. Orders + reservations
POST `/orders`
```json
{"warehouseId":"<warehouseId>","customerName":"ABC Institute","items":[{"productId":"<productId>","quantity":3}]}
```
GET `/orders`
GET `/reservations/orders/{orderId}`
POST `/orders/{orderId}/fulfill`
POST `/orders/{orderId}/cancel`

## 5. Purchase orders
POST `/purchase-orders`
```json
{"warehouseId":"<warehouseId>","supplierName":"ABC Supplier","items":[{"productId":"<productId>","quantity":20,"unitCost":50000}]}
```
GET `/purchase-orders`
POST `/purchase-orders/{poId}/receive`

## 6. Returns
After an order is fulfilled:
POST `/returns`
```json
{"orderId":"<orderId>","warehouseId":"<warehouseId>","productId":"<productId>","quantity":1,"reason":"Damaged packaging"}
```
GET `/returns`
POST `/returns/{returnId}/accept`
POST `/returns/{returnId}/reject`

## 7. Alerts and history
POST `/alerts/refresh`
GET `/alerts`
POST `/alerts/{alertId}/acknowledge`
GET `/stock-movements`
GET `/stock-movements/products/{productId}`
GET `/stock-movements/warehouses/{warehouseId}`

## 8. Existing analytics / AI
GET `/analytics/products/{productId}/sales`
GET `/analytics/products/{productId}/daily-sales?from=2026-08-01&to=2026-08-31`
GET `/forecast/products/{productId}?days=30`
GET `/reorder/products/{productId}`
GET `/anomalies`
GET `/recommendations/products/{productId}`
POST `/assistant/ask`
```json
{"question":"Which products are low in stock?"}
```
