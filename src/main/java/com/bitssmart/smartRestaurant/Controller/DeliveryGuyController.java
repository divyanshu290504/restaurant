package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// import com.bitssmart.smartRestaurant.Model.DeliveryGuy;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.User;
// import com.bitssmart.smartRestaurant.Service.DeliveryGuyService;
import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.UserService;

@Controller
public class DeliveryGuyController {

	// @Autowired
	// private DeliveryGuyService deliveryGuyService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	private static List<FoodOrder> foodOrderList = new ArrayList<>();;
	
	@RequestMapping(value={"/loginDeliveryGuy"},method={RequestMethod.GET})    
	public ModelAndView login()  
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("deliveryGuyLogin");
		return model;
	}
	
	//@AuthenticationPrincipal Principal user
//	@RequestMapping(value= {"/deliveryGuy/orders"}, method= {RequestMethod.POST})
//	 public ModelAndView home() {
//	  ModelAndView model = new ModelAndView();
//	  Authentication user = SecurityContextHolder.getContext().getAuthentication();
//	  System.out.println(user.getName());
//	  //System.out.println(auth.getName());
//	  System.out.println("cred" +user.getCredentials());
//	  System.out.println("details" +user.getDetails());
//	  System.out.println("princ" +user.getPrincipal());
//	  System.out.println("autho" +user.getAuthorities());
//	  DeliveryGuy guyExists = deliveryGuyService.findUserByEmail(user.getName());
//	  System.out.println("guy  " +guyExists);
//		 if(null != guyExists) model.addObject("userName", guyExists.getName()); 
//		 else
//		  model.addObject("userName", "abc");
//		 
//	  List<FoodOrder> orderList = orderService.getAllFoodOrders();
//	  model.addObject("orderList",orderList); 
//	  model.setViewName("viewOrders");
//	  return model;
//	 }
	
//@ModelAttribute("orderIds") List<Long> orderIds
	@RequestMapping(value={"/refreshOrders"},method={RequestMethod.POST})    
	public ModelAndView selectOrders(HttpServletRequest request)  
	{
		ModelAndView model = new ModelAndView();
		@SuppressWarnings("unchecked")
        Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		String email = String.valueOf(messages.get("email"));
    	User user = userService.findUserByEmail(email);
		model.addObject("userName", user.getName());
		List<FoodOrder> orderList = orderService.getAllFoodOrders();
		System.out.println(orderList);
		model.addObject("orderList",orderList); 
		model.setViewName("viewOrders");
		return model;
	}
	
	@RequestMapping(value={"/selectOrders"},method={RequestMethod.POST})    
	public ModelAndView selectOrders( @RequestParam(value = "orderIds" , required = false) long[] orderIds, HttpServletRequest request)  
	{
		@SuppressWarnings("unchecked")
        Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		String email = String.valueOf(messages.get("email"));
		User user = userService.findUserByEmail(email);
		//List<FoodOrder> foodOrderList = new ArrayList<>();
		foodOrderList.clear();
		if(null != orderIds) {
			for(long a : orderIds) {
				FoodOrder fo = orderService.getFoodOrder(a);
				fo.setUserId(user);
				fo.setOrderStatus(OrderStatus.PICKED_UP);
				fo = orderService.saveFoodOrder(fo);
				foodOrderList.add(fo);
			}
		}
		ModelAndView model = new ModelAndView();
		model.addObject("userName", user.getName());
		model.addObject("foodOrderList", foodOrderList);
//		model.addObject("orderIds", orderIds);
		model.addObject("points", user.getDeliveryGuy().getPoints());
		model.setViewName("updateOrderDetails");
		return model;
	}
	
	@RequestMapping(value={"/orderDelivered"},method={RequestMethod.POST})    
	public ModelAndView selectOrders( @RequestParam("orderid") long orderid , @RequestParam("otp") String otp, HttpServletRequest request )  
	{
		@SuppressWarnings("unchecked")
        Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		String email = String.valueOf(messages.get("email"));
		User user = userService.findUserByEmail(email);
		FoodOrder fo1 = orderService.getFoodOrder(orderid);
		System.out.println(fo1);
		System.out.println("::::::::::::::::::::::::::: "+foodOrderList);
		ModelAndView model = new ModelAndView();
		if(null!= otp && fo1.getOtp().equalsIgnoreCase(otp.trim())) {
			for(int i=0; i< foodOrderList.size(); i++) {
				if(foodOrderList.get(i).getId().equals(orderid)) {
					foodOrderList.remove(i);
				}
			}
			fo1.setOrderStatus(OrderStatus.DELIVERED);
			if(fo1.getTotalPrice() >= 0 && fo1.getTotalPrice() <= 200) {
				fo1.getUserId().getDeliveryGuy().setPoints(fo1.getUserId().getDeliveryGuy().getPoints()+5);
			}else if(fo1.getTotalPrice() > 200 && fo1.getTotalPrice() <= 500) {
				fo1.getUserId().getDeliveryGuy().setPoints(fo1.getUserId().getDeliveryGuy().getPoints()+10);
			}else {
				fo1.getUserId().getDeliveryGuy().setPoints(fo1.getUserId().getDeliveryGuy().getPoints()+15);
			}
			
			fo1 = orderService.saveFoodOrder(fo1);
			System.out.println(":::::delivery status"+ fo1.getOrderStatus());
			model.addObject("msg", "Delivery Successful!");
			//foodOrderList.add(fo1);
		}else {
			model.addObject("msg", "Wrong OTP!");
		}
		//List<FoodOrder> foodOrderList = new ArrayList<>();
//		if(null != orderIds) {
//			for(long a : orderIds) {
//				FoodOrder fo = orderService.getFoodOrder(a);
//				foodOrderList.add(fo);
//			}
//		}
		model.addObject("userName", user.getName());
		model.addObject("foodOrderList", foodOrderList);
//		model.addObject("orderIds", orderIds);
		model.addObject("points", user.getDeliveryGuy().getPoints());
		model.setViewName("updateOrderDetails");
		return model;
	}
	
	@RequestMapping(value={"/viewDeliveredOrders"},method=RequestMethod.GET)    
	public ModelAndView delRegister(HttpServletRequest request)  
	{
		@SuppressWarnings("unchecked")
		Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		Long id = Long.parseLong(messages.get("id"));
		List<FoodOrder> orders = orderService.getAllDeliveredOrders(id);
		
		ModelAndView model = new ModelAndView();
	   
		model.addObject("orders", orders);
		model.setViewName("deliveredOrders");
		return model;
	}
}
