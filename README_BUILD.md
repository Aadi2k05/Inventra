# Inventra Backend Build

This build keeps the existing authentication, products, inventory transactions, analytics, forecasting and reorder modules and adds the remaining managerial inventory workflows.

## Added feature groups
- Multiple warehouses
- Per-warehouse real-time inventory
- Warehouse-to-warehouse stock transfer / synchronization
- Order management
- Automatic stock reservation during order creation
- Order cancellation and fulfillment
- Returns
- Purchase orders and receiving
- Low-stock / out-of-stock alerts
- Stock movement history
- Inventory anomaly detection
- Product recommendations
- Rule-based AI inventory assistant

## Important
The uploaded source did not include the security classes even though the existing project uses JWT authentication, so this build includes `security/JwtService`, `security/JwtAuthenticationFilter`, `security/SecurityConfig`, and `service/AuthService`.

The included `pom.xml` adds `spring-boot-starter-security`, which is required by those classes.

Before starting:
1. Keep MongoDB running on `127.0.0.1:27017`.
2. If your existing project already has `application.properties`, merge the JWT properties rather than blindly replacing your file.
3. Run `mvn clean spring-boot:run` from the backend root.
4. Login again and use the fresh JWT in Postman.
5. Follow `API_TEST_GUIDE.md` in order.

I could not execute Maven in this environment because Maven is not installed here, so the source has not been runtime-compiled in this environment. Test compilation with your project's Maven installation before replacing the currently running backend.
