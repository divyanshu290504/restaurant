package com.bitssmart.smartRestaurant.Service;

import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.DeliveryGuy;

@Service
public interface DeliveryGuyService {
	public DeliveryGuy findUserByEmail(String email);

	public void saveUser(DeliveryGuy guy);

}
