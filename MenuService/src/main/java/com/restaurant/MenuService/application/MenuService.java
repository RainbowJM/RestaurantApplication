package com.restaurant.MenuService.application;

import com.restaurant.MenuService.data.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuService {

    public MenuService() {
    }

    public List<MenuDTO> getMenuByRestaurantId(long restaurantId){
        //repository text
    }

}
