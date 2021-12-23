# RestaurantApplication


### Development guidelines
- Each microservice uses the same MongoDB server, but uses its own documents and can only refer to objects outside their bounded context using their id. It should be possible to separate these servers by just changing the IP.
- Utilize adapters to communicate with the com.restaurant.OrderService.core logic of the services. Use outgoing adapters for getting/setting the objects outside the bounded context.
- While we use REST endpoints, the com.restaurant.OrderService.core(s) within services are communicated with using commands and queries following CQRS.


### Testing guidelines
- Each domain object is tested using domain tests.
- Each service is tested individually using service tests.
- All of the endpoints are tested using multiple Postman scripts.

###Postman Collection 
For each microservice there is a postman collection for the testing in `docs/postman`

### Endpoints
#### Menu Service
path: http://localhost:8080 

GET /menu/:restaurantId/ 
- Gets a restaurant menu from a certain restaurant 

POST /menu/:restaurantId/ 

- Creates a new menu at a restaurant

PATCH /menu/:menuId/

- Allows you to change the menu using the menu id 

GET /menu/:menuId/dish/:id/

- Gets a restaurant dish with a specific id and see ingredient availability

POST /menu/:menuId/dish/

- Creates a new dish on a menu

DELETE /menu/:menuId/dish/:id
- Deletes a dish from a menu

####Order Service
path: http://localhost:8081

GET /order/:restaurantId/ 

- Gets queue from a certain restaurant

POST /order/ 

- Creates order in queue at a certain restaurant

GET /order/:id/

- Gets information and status from an order

PATCH /order/:id/

- Allows you to update status or order details from an order

DELETE /order/:id/

- Delete order at a certain restaurant

####Table Service
path: http://localhost:8082

POST /table/

- Creates table at a certain restaurant

POST /table/reserve 

- Used when walk-in users claim/reserve a table

GET /table/

- Gets information from a table

PATCH /table/:id/

- Allows you to update details from a table

DELETE /table/:id/

- Delete table at a certain restaurant


####User Service
path: http://localhost:8083 

GET /user/:restaurantId/ (only for staff)
Gets all users from a certain restaurant
GET /user/:id/
Gets information of a user/guest/tableguest
PATCH /user/:id/
Allows you to update the user information, like roles etc.
POST /user/
Creates a user
DELETE /user/:id/
Delete a user


####Restaurant Service
path: http://localhost:8085 

