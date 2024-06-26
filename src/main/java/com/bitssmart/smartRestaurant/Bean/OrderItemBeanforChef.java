package com.bitssmart.smartRestaurant.Bean;


import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class OrderItemBeanforChef {
	
	private Long id;
	private FoodOrder orderId;
	private OrderStatus orderStatus;
	private String note;
	private int quantity;
	private String name;
}

