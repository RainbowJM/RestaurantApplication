# RestaurantApplication


### Development guidelines
- Each microservice uses the same MongoDB server, but uses its own documents and can only refer to objects outside their bounded context using their id. It should be possible to separate these servers by just changing the IP.
- Utilize adapters to communicate with the com.restaurant.OrderService.core logic of the services. Use outgoing adapters for getting/setting the objects outside the bounded context.
- While we use REST endpoints, the com.restaurant.OrderService.core(s) within services are communicated with using commands and queries following CQRS.


### Testing guidelines
- Each domain object is tested using domain tests.
- Each service is tested individually using service tests.
- All of the endpoints are tested using multiple Postman scripts.

### Endpoints
menu-path: http://localhost:8080 \
order-path: http://localhost:8081 \
table-path: http://localhost:8082 \
user-path: http://localhost:8083 \
restaurant-path: http://localhost:8085 