package com.restaurant.MenuService.port;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public class MenyRepository extends MongoRepository<User, String> {

}
