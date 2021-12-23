package com.restaurant.MenuService.core.application.command;

public record AddDishToMenuCommand(String menuId,String dishId, String name, Double price, String ingredients, int calories){}
