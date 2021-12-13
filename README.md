# RestaurantApplication


### Development guidelines
- Each microservice uses the same MongoDB server, but uses its own documents and can only refer to objects outside their bounded context using their id. It should be possible to separate these servers by just changing the IP.
- Utilize adapters to communicate with the core logic of the services. Use outgoing adapters for getting/setting the objects outside the bounded context.
- While we use REST endpoints, the core(s) within services are communicated with using commands and queries following CQRS.