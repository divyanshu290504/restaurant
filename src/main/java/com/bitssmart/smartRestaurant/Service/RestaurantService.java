package com.bitssmart.smartRestaurant.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> getAllRestaurants(){
		List<Restaurant> restaurants = new ArrayList<>();
		restaurantRepository.findAll().forEach(restaurants::add);
		logger.info("All restaurants: {}", restaurants);
		return restaurants;
	}
	
	public Restaurant getRestaurant(Long id) {
		return restaurantRepository.findById(id).orElse(null);
	}
}
