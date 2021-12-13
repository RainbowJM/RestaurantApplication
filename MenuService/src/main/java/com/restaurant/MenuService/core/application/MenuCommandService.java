package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuCommandService {
	private final MenuRepository menuRepository;

	public MenuCommandService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
}