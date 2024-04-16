package com.bitssmart.smartRestaurant.Repository;
import com.bitssmart.smartRestaurant.Model.MenuItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItems, Long> {
    
}
