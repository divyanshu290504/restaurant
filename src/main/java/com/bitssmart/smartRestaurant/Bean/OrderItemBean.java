package com.bitssmart.smartRestaurant.Bean;


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
public class OrderItemBean {
	
	private Long count;
	private String menuItem;
	private float price;
}
