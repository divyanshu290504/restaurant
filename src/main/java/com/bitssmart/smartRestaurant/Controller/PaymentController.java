package com.bitssmart.smartRestaurant.Controller;

// import java.util.Map;
// import java.util.Map.Entry;
import java.util.Random;
// import java.util.TreeMap;

// import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.Payment;

// import com.bitssmart.smartRestaurant.Service.EmailConfigService;
import com.bitssmart.smartRestaurant.Service.OrderService;
// import org.springframework.core.env.Environment;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/success", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView paymentSuccess(@RequestParam(name="orderid",defaultValue = "") Long orderid,HttpServletRequest request ) {
		FoodOrder foodOrder = orderService.getFoodOrder(orderid);
		if(foodOrder!= null){
		foodOrder.setPayment(new Payment());
		foodOrder.getPayment().setFoodOrder(foodOrder);
		foodOrder.getPayment().setFoodOrder(foodOrder);
		foodOrder.getPayment().setAmount(foodOrder.getTotalPrice());
		foodOrder.setIsPaid(true);
		if(foodOrder.getOrderType().equals(OrderType.HOME_DELIVERY)) {
			Random random = new Random();
			foodOrder.setOtp(String.format("%04d", random.nextInt(10000)));
		}
	}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderid", orderid);
		modelAndView.setViewName("paymentSuccess");

		return modelAndView;
	}

	@RequestMapping(value="/trackOrder", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView trackOrder(@RequestParam(name = "orderid") long orderid ) {
		System.out.println(orderid);
		FoodOrder foodOrder = orderService.getFoodOrder(orderid);
		ModelAndView modelAndView = new ModelAndView();
		if(foodOrder.getOrderStatus().equals(OrderStatus.IS_COOKING))
			modelAndView.addObject("msg", "Restaurant is preparing your order!");
		else if(foodOrder.getOrderStatus().equals(OrderStatus.PICKED_UP))
			modelAndView.addObject("msg", "Your Order has been Picked up!");
		else
			modelAndView.addObject("msg", "Your order is "+foodOrder.getOrderStatus());

		if(foodOrder.getOrderType().equals(OrderType.HOME_DELIVERY)) {
			modelAndView.addObject("otp", null != foodOrder.getOtp()? foodOrder.getOtp(): "0000");
		}
		modelAndView.addObject("orderid", orderid);
		modelAndView.setViewName("trackOrder");
		return modelAndView;
	}

	
}